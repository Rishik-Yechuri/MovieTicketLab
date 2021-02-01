import java.util.Arrays;

class MyQueue{
    int sizeOfList = 0;
    int locationOfLastElement = -1;
    int[] elements = new int[200];
    public MyQueue(int size){
    }


    public void add(int x) {
        locationOfLastElement++;
        elements[locationOfLastElement] = x;
        sizeOfList++;
    }

    public int remove() {
        int itemRemoved = elements[0];
        for(int x=0;x<100;x++){
            if(x<sizeOfList-1) {
                elements[x] = elements[x + 1];
                elements[x + 1] = 0;
            }
        }
        locationOfLastElement--;
        sizeOfList--;
        return itemRemoved;
    }

    public boolean isEmpty() {
        return locationOfLastElement<=0;
    }

    public int getSize() {
        if(locationOfLastElement<0){return 0;}
        return locationOfLastElement+1;
    }
    public String toString(){
        int posInArray = 0;
        int[] arrayToReturn = new int[sizeOfList];
        for(int x =0;x<100;x++){
            if(elements[x] != 0){
                arrayToReturn[posInArray] = elements[x];
                posInArray++;
            }
        }
        return Arrays.toString(arrayToReturn);
    }
}