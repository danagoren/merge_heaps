
public class UnsortedHeap {
    private List heap;

    public UnsortedHeap(){
        this.heap = null;
    }
    public void makeHeap() {
        heap = new List();
    }

    public List getHeap() {
        return heap;
    }

    public int minimum() {
        return heap.getHead().getValue();
    }

    public void switchNodes(Node x, Node y) {
        if ((x != null) && (y != null)) {
            int temp = x.getValue();
            x.setValue(y.getValue());
            y.setValue(temp);
        }
    }

    public int extractMin() {
        if (heap == null) { //if heap is empty
            return 0;
        }
        int min = minimum();
        Node lastNode = heap.getHead(); //represents the last node
        Node previousNode = heap.getHead(); //represents the one before the last node
        while (lastNode.getNext() != null) { //set lastNode, previousNode
            previousNode = lastNode;
            lastNode = lastNode.getNext();
        }
        switchNodes(heap.getHead(), lastNode); //switch: root, last
        previousNode.setNext(null); //delete last node
        heapifyDown(heap.getHead(), 1); //heapify root
        return min;

    }

    public void heapifyDown(Node current, int index) {
        Node left = current; //left son
        Node right = current; //right son
        int leftIndex = 2 * index; //left son index
        int rightIndex = 2 * index + 1; //right son index
        while ((left.getNext() != null) && (index < leftIndex)) { //set left
            index++;
            left = left.getNext();
        }
        if (index < leftIndex) { //if no left son (=> no right son either)
            return;
        }
        if (left.getNext() != null) { //if right son exists
            right = left.getNext(); // set right son
        } else { //no right son
            if (left.getValue() < current.getValue()) { // if left son < current, switch
                switchNodes(current, left);
            }
            return;
        }
        if (left.getValue() < right.getValue()) { //if left son < right son
            if (left.getValue() < current.getValue()) { //if left son < current, switch and heapify left son
                switchNodes(current, left);
                heapifyDown(left, leftIndex);
            }
        } else { //if right son < left son
            if (right.getValue() < current.getValue()) { //if right son < current, switch and heapify right son
                switchNodes(current, right);
                heapifyDown(right, rightIndex);
            }
        }
    }

    public void heapifyUp(Node current, int index) {
        Node father = heap.getHead();
        int fatherIndex = Math.max((index / 2), 1);
        int i = 1;
        while ((i < fatherIndex)) { // set father
            father = father.getNext(); //this line is not problematic because father will never be null
            i++;
        }
        if (current.getValue() < father.getValue()) { //if current<father, switch and heapify
            switchNodes(current, father);
            heapifyUp(father, fatherIndex);
        }
    }

    public boolean alreadyExist(int value) { //checks if "value" was already inserted into the heap
        Node current = heap.getHead(); //set current
        if (current == null) {
            return false;
        } //if the list is empty, "value" couldn't have been inserted into the heep already
        if (current.getValue() == value) { //if root = value, return true
            return true;
        }
        while (current.getNext() != null) { //if one of the nodes = value, return true
            current = current.getNext();
            if (current.getValue() == value) {
                return true;
            }
        }
        return false; //value wasn't found in the heap
    }

    public int insert(int value) {
        if (alreadyExist(value) == true) {
            return 1;
        } //to prevent duplications
        int length = 1;
        Node lastNode = heap.getHead(); //the last node of the list
        Node current = new Node(value); //creating new node
        if (lastNode == null) { //if the heap is empty, set head's value to be "value"
            heap.setHead(current);
            return 0;
        }
        while (lastNode.getNext() != null) { //set "lastNode" and "length"
            length++;
            lastNode = lastNode.getNext();
        }
        lastNode.setNext(current); //linking the last node to the new node
        length++;
        heapifyUp(current, length); //length is the index of the last node
        return 0;
    }

    public UnsortedHeap union(UnsortedHeap otherHeap) {
        Node firstHead = heap.getHead();
        Node secondHead = otherHeap.getHeap().getHead();
        if (firstHead == null) {
            return otherHeap;
        } //for every heap A: A.union(emptyHeap)=A
        if (secondHead == null) {
            return UnsortedHeap.this;
        }//for every heap A: emptyHeap.union(A)=A
        UnsortedHeap newHeap = new UnsortedHeap(); //creating a new heap
        newHeap.makeHeap();
        newHeap.insert(firstHead.getValue()); //insert first root (we made sure earlier that firstHead != null)
        newHeap.insert(secondHead.getValue()); //insert second root (we made sure earlier that secondHead != null)
        while (firstHead.getNext() != null) { //insert first heap's nodes
            firstHead = firstHead.getNext();
            newHeap.insert(firstHead.getValue());
        }
        while (secondHead.getNext() != null) { //insert second heap's nodes
            secondHead = secondHead.getNext();
            newHeap.insert(secondHead.getValue());
        }
        return newHeap;
    }

    @Override
    public String toString() {
        String str = "";
        Node temp = getHeap().getHead();
        while (temp != null) {
            str += (temp.getValue() + "-> ");
            temp = temp.getNext();
        }
        return str;
    }
}