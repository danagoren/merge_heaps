public class List {

    private Node head;

    public List(Node head) {
        this.head = head;
    }

    public List(){
        setHead(null);
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setNext(Node node){
        head.setNext(node);
    }

    public Node getNext(){
        return getHead().getNext();
    }

    @Override
    public String toString() {
        String str = "";
        Node temp = head;
        while(temp != null){
            str +=(temp.getValue()+"-> ");
            temp = temp.getNext();
        }
        return str;
    }
}
