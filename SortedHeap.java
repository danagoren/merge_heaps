import java.util.LinkedList;

public class SortedHeap{
    private List heap;

    public void makeHeap(){
        heap = new List();
    }

    public List getHeap() {
        return heap;
    }
//min is always first element because the list is sorted
    public int minimum(){
        return heap.getHead().getValue();
    }

    public int extractMin(){
        //if heap is not empty
        if(heap != null) {
            //save the min value in temp
            int temp = heap.getHead().getValue();
            //deletes the min element
            heap.setHead(heap.getHead().getNext());
            return temp;
        }
        return 0;
    }

    public int insert(int x){
        Node node = null;
        //temp points to the current node being checked prev to the one previous to temp
        Node temp = heap.getHead();
        Node prev = heap.getHead();
        //if the heap is empty insert x
        if(temp == null){
            heap.setHead(new Node(x));
            return 1;
        }
        //x is smaller the current node insert before current node
        else if(temp.getValue() > x){
            node = new Node(x);
            node.setNext(temp);
            heap.setHead(node);
        }
        //the value already exists in the list return 0 and dont insert
        else if(temp.getValue() == x){
            return 0;
        }

        else {
            temp = temp.getNext();
            while (temp != null){
                //x is smaller the current node insert before current node
                if( x < temp.getValue()){
                   node = new Node(x);
                    node.setNext(temp);
                    prev.setNext(node);
                    return 1;
                }
                //the value already exists in the list return 0 and dont insert

                else if(x == temp.getValue()){
                    return 0;
                }
                //x is bigger then the current node val , move forward
                else{
                    temp = temp.getNext();
                    prev =prev.getNext();
                }
            }
            //weve reached the end of the list. insert x at the end
            if(node == null){
                prev.setNext(new Node(x));
                return 1;
            }
        }
        return 0;
    }


    public SortedHeap union(SortedHeap otherHeap){
        Node temp= heap.getHead();
        Node tempOther = otherHeap.getHeap().getHead();
        SortedHeap newHeap = new SortedHeap();
        newHeap.makeHeap();
        //insert the smaller value first and advance pointer of the list in which the value came from
        if(temp.getValue() < tempOther.getValue()){
            newHeap.getHeap().setHead(temp);
            temp = temp.getNext();
        }
        //equal values, just insert one of them and move forward on both lists
        else if(temp.getValue() == tempOther.getValue()){
            newHeap.getHeap().setHead(temp);
            temp = temp.getNext();
            tempOther = tempOther.getNext();
        }
        else{
            newHeap.getHeap().setHead(tempOther);
            newHeap.getHeap().setHead(tempOther);
            tempOther = tempOther.getNext();
        }
        Node head = newHeap.getHeap().getHead();
        //continue iterate through the entire lists
        while (temp != null && tempOther != null){
            if(temp.getValue() < tempOther.getValue()){
                head.setNext(temp);
                head = head.getNext();
                temp = temp.getNext();
            }
            else if(temp.getValue() == tempOther.getValue()){
                head.setNext(temp);
                head = head.getNext();
                temp = temp.getNext();
                tempOther = tempOther.getNext();
            }
            else{
                head.setNext(tempOther);
                head = head.getNext();
                tempOther = tempOther.getNext();
            }
        }
        //temp still has some values.. insert them in order at the end
        if(temp!= null){
            while(temp != null){
                head.setNext(temp);
                head = head.getNext();
                temp = temp.getNext();
            }
        }
        //tempOther still has some values.. insert them in order at the end

        else if(tempOther!= null){
            while(tempOther != null){
                head.setNext(tempOther);
                head = head.getNext();
                tempOther = tempOther.getNext();
            }
        }

        head.setNext(null);
        return newHeap;
    }

    @Override
    public String toString() {
        String str = "";
        Node temp = getHeap().getHead();
        while(temp != null){
            str +=(temp.getValue()+"-> ");
            temp = temp.getNext();
        }
        return str;
    }

}

