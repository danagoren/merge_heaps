import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static File myObj;
    static Scanner myReader;
    static String fileName;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the heap implementation mode\n" +
                "type: 1 for sorted heap\n" +
                "type: 2 for unsorted heap\n" +
                "type: 3 for disjointed unsorted heap");
        int runConfiguration = scanner.nextInt();
        fileName = args[0];

        switch (runConfiguration){
            case 1:
                HandleSortedHeap();
                break;
            case 2:
                HandleUnSortedHeap();
                break;
            case 3:
                HandleDisjointedUnsorted();
                break;
        }
    }

    public static void HandleSortedHeap(){
        //creating heaps array to store all heaps generated
        SortedHeap[] sortedHeapArray = new SortedHeap[100];
        int index = -1;
        try {
            //opening the commands file and creating a scanner to access it
            myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            //reading the file word by word
            while (myReader.hasNext()) {
                String data = myReader.next();
                //taking actions according to the command
                switch(data){
                    case "MakeHeap":
                        sortedHeapArray[++index] = new SortedHeap();
                        sortedHeapArray[index].makeHeap();
                        break;
                    case "Insert":
                        data = myReader.next();
                        int value = Integer.parseInt(data);
                        if (sortedHeapArray[index].insert(value) == 0) {
                            System.out.println("after insertion, the heap is: " + sortedHeapArray[index].toString());
                        }
                        break;
                    case "ExtractMin":
                        System.out.println("minimum: "+sortedHeapArray[index].extractMin());
                        break;
                    case "Union":
                        sortedHeapArray[1] = sortedHeapArray[0].union(sortedHeapArray[1]);
                        sortedHeapArray[0] = null;
                        for(int i = 2; i <=index; i++){
                            sortedHeapArray[i] = sortedHeapArray[i-1].union(sortedHeapArray[i]);
                            sortedHeapArray[i-1] = null;
                        }
                        sortedHeapArray[0] = sortedHeapArray[index];
                        sortedHeapArray[index] = null;
                        index = 0;
                        System.out.println("after union, the heap is: " + sortedHeapArray[0].toString());
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void HandleUnSortedHeap(){
        //creating heaps array to store all heaps generated
        UnsortedHeap[] unsortedHeapArray = new UnsortedHeap[100];
        int index = -1;
        try {
            //opening the commands file and creating a scanner to access it
            myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            //reading the file word by word
            while (myReader.hasNext()) {
                String data = myReader.next();
                //taking actions according to the command
                switch(data){
                    case "MakeHeap":
                        unsortedHeapArray[++index] = new UnsortedHeap();
                        unsortedHeapArray[index].makeHeap();
                        break;
                    case "Insert":
                        data = myReader.next();
                        int value = Integer.parseInt(data);
                        if (unsortedHeapArray[index].insert(value) == 0) {
                            System.out.println("after insertion, the heap is: " + unsortedHeapArray[index].toString());
                        }
                        break;
                    case "ExtractMin":
                        System.out.println("minimum: " + unsortedHeapArray[index].extractMin());
                        break;
                    case "Union":
                        unsortedHeapArray[1] = unsortedHeapArray[0].union(unsortedHeapArray[1]);
                        unsortedHeapArray[0] = null;
                        for(int i = 2; i <=index; i++){
                            unsortedHeapArray[i] = unsortedHeapArray[i-1].union(unsortedHeapArray[i]);
                            unsortedHeapArray[i-1] = null;
                        }
                        unsortedHeapArray[0] = unsortedHeapArray[index];
                        unsortedHeapArray[index] = null;
                        index = 0;
                        System.out.println("after union, the heap is: "+unsortedHeapArray[0].toString());
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void HandleDisjointedUnsorted(){
        //creating heaps array to store all heaps generated
        DisjointUnsortedHeap[] array = new DisjointUnsortedHeap[100];
        int index = -1;
        try {
            //opening the commands file and creating a scanner to access it
            myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            //reading the file word by word
            while (myReader.hasNext()) {
                String data = myReader.next();
                //taking actions according to the command
                switch(data){
                    case "MakeHeap":
                        array[++index] = new DisjointUnsortedHeap();
                        array[index].makeHeap();
                        break;
                    case "Insert":
                        data = myReader.next();
                        int value = Integer.parseInt(data);
                        int heapIndex = 0;
                        int alreadyInsertedFlag = 0;
                        while (heapIndex<100) { //makes sure the value doesn't already exist in one of the heaps
                            if (array[heapIndex] == null){ //if finished checking all heaps
                                break;
                            }
                            if (array[heapIndex].alreadyExist(value)){
                                System.out.println("the value "+value+" wasn't inserted into the heap because it already exists in one of the heaps");
                                alreadyInsertedFlag = 1;
                                break;
                            }
                            heapIndex++;
                        }
                        if (alreadyInsertedFlag == 0) {
                            array[index].insert(value);
                            System.out.println("after insertion, heap is: " + array[index].toString());
                        }
                        break;
                    case "ExtractMin":
                        System.out.println("minimum: " + array[index].extractMin());
                        break;
                    case "Union":
                        array[1] = array[0].union(array[1]);
                        array[0] = null;
                        for(int i = 2; i <=index; i++){
                            array[i] = array[i-1].union(array[i]);
                            array[i-1] = null;
                        }
                        array[0] = array[index];
                        array[index] = null;
                        index = 0;
                        System.out.println("after union, the heap is:"+ array[0].toString());
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
