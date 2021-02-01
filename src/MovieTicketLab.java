import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class MovieTicketLab {
    public static void main(String[] args) throws InterruptedException {
        //How long to pause between iterations
        int timeToSleep = 100;
        //Keeps track of current iteration
        int numOfCycle = 1;
        //Keeps track of whether program should keep running
        boolean keepRunning = true;
        //Stores data about each line
        ArrayList<Object> lineData = new ArrayList<>();
        //Create each line
        for (int x = 0; x < 4; x++) {
            Object[] holdLineInfo = new Object[2];
            holdLineInfo[0] = new MyQueue(0);
            holdLineInfo[1] = new int[]{0, 0};
            lineData.add(holdLineInfo);
        }
        while (numOfCycle <= 200) {
            //Sleeps for selected amount of time
            Thread.sleep(timeToSleep);
            //Continue to spawn in new people for first 150 cycles
            if (numOfCycle <= 150) {
                //Create a random,used to generate people at random times
                int personSpawned = (int) (Math.random() * 10) + 1;
                Object[] lineObject;
                Object[] lineObjectAddTo = null;
                MyQueue queueToAddTo = null;
                //80% of the time a person is spawned
                if (personSpawned <= 8) {
                    int shortestLineLength = 999999999;
                    int shortestLinePos = 0;
                    //Goes through each line,and checks length
                    for (int x = 0; x < lineData.size(); x++) {
                        //Gets the length of the current line
                        lineObject = (Object[]) lineData.get(x);
                        MyQueue tempQueue = (MyQueue) lineObject[0];
                        int lineLength = tempQueue.getSize();
                        //Adds line length to the "people stats",which keeps track of total number of units spent in line
                        int[] tempPeopleStats = (int[]) lineObject[1];
                        tempPeopleStats[0] += lineLength;
                        //Executes if length of current line is the shortest so far
                        if (lineLength < shortestLineLength) {
                            //The value storing the size of the shortest line is updated
                            shortestLineLength = lineLength;
                            //Updates the index of the shortest line to the current line
                            shortestLinePos = x;
                            lineObjectAddTo = lineObject;
                            /*This sets the line which should be added to,as the current one
                            meaning that the next person will enter this line*/
                            queueToAddTo = tempQueue;
                        }
                    }
                    //Adds a person to the shortest line
                    queueToAddTo.add(1);
                    int[] peopleStats = (int[]) lineObjectAddTo[1];
                    peopleStats[1]++;
                    //Prints out that someone has joined a line
                    System.out.println("Person added to line " + (shortestLinePos + 1) + " (" + queueToAddTo.getSize() + ")");
                }
            }
            //Goes through all the lines
            for (int x = 0; x < lineData.size(); x++) {
                //Creates a random number between 1 and 10(inclusive),to be used later
                int personRemoved = (int) (Math.random() * 10) + 1;
                //There is a 20% chance that this executes
                if (personRemoved <= 2) {
                    //Retrieves the queue for the current line
                    Object[] lineObject = (Object[]) lineData.get(x);
                    MyQueue lineQueue = (MyQueue) lineObject[0];
                    //If there's at least one person in it,remove someone
                    if (lineQueue.getSize() > 0) {
                        lineQueue.remove();
                        //Print out that someone has left the line
                        System.out.println("Person removed from line " + (x + 1) + " (" + lineQueue.getSize() + ")");
                    }
                }
            }
            //Increments the cycle number
            numOfCycle++;
        }
        //Finds the line with the shortest average wait time
        double shortestWaitTime = 9999999;
        int posOfShortestWaitTime = 0;
        for (int x = 0; x < lineData.size(); x++) {
            Object[] lineObject;
            lineObject = (Object[]) lineData.get(x);
            int[] lineStats = (int[]) lineObject[1];
            double averageWaitTime = (double) lineStats[0] / lineStats[1];
            if(averageWaitTime<shortestWaitTime){
                shortestWaitTime = averageWaitTime;
                posOfShortestWaitTime = x;
            }
        }
        //Prints out which line had the shortest wait time
        System.out.println("Line with shortest wait time:" + (posOfShortestWaitTime+1));
    }
}
