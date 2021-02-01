import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class MovieTicketLab {
    public static void main(String[] args) {
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
        while (numOfCycle<=150) {
            //Continue to spawn in new people for first 150 cycles
            if (numOfCycle <= 150) {
                //Create a random,used to generate people at random times
                int personSpawned = (int) (Math.random() * 10) + 1;
                Object[] lineObject;
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
                        //Executes if length of current line is the shortest so far
                        if (lineLength < shortestLineLength) {
                            //The value storing the size of the shortest line is updated
                            shortestLineLength = lineLength;
                            //Updates the index of the shortest line to the current line
                            shortestLinePos = x;
                            /*This sets the line which should be added to,as the current one
                            meaning that the next person will enter this line*/
                            queueToAddTo = tempQueue;
                        }
                    }
                    //Adds a person to the shortest line
                    queueToAddTo.add(1);
                    //Prints out that someone has joined a line
                    System.out.println("Person added to line " + (shortestLinePos+1) + " (" + queueToAddTo.getSize() + ")");
                }
                //Increments the cycle number
                numOfCycle++;
            }
            //Goes through all the lines
            for(int x=0;x<lineData.size();x++){
                //Creates a random number between 1 and 10(inclusive),to be used later
                int personRemoved = (int) (Math.random() * 10) + 1;
                //There is a 20% chance that this executes
                if(personRemoved<=2){
                    //Retrieves the queue for the current line
                    Object[] lineObject = (Object[]) lineData.get(x);
                    MyQueue lineQueue = (MyQueue) lineObject[0];
                    //If there's at least one person in it,remove someone
                    if(lineQueue.getSize() > 0) {
                        lineQueue.remove();
                        //Print out that someone has left the line
                        System.out.println("Person removed from line " + (x + 1) + " (" + lineQueue.getSize() + ")");
                    }
                }
            }
        }
    }

}
