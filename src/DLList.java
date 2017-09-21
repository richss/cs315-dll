/**
 * @author Richard S. Stansbury
 * @version 1.0, 2017-09-20
 *
 *
 * Implementation of a doubly linked list node with a head, but no tail.
 */
public class DLList<t> {

    //Class Variables
    protected DLLNode<t> head;
    protected DLLNode<t> tail;
    int size;

    /**
     * Default constructor for the list.
     */
    public DLList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Inserts an item into the list at the list's end.
     * @param value - value to be stored in node.
     */
    public void addToTail(t value) {
        //Empty List Case
        if (head == null) {
            head = tail = new DLLNode<>(value);
        }
        else {
            tail.next = new DLLNode<>(value, tail, null);
            tail = tail.next;
        }
        size++;
    }


    /**
     * Adds an item to the list at a location.  If the location is out of bounds, it will add it to the
     * front if less than zero or back if beyond the current size.
     *
     * If a value is already at that location, it is shifted to the right to make room for the new node.
     *
     * @param value
     * @param location
     */
    public void addToLocation(t value, int location) {


        //Check if empty
        if (head == null) {
            head = tail = new DLLNode<>(value);
            size++;
            return;
        }

        //Add to head
        if (location <= 0) {
            head = new DLLNode<>(value, null, head);
            (head.next).prev = head;
            size++;
            return;
        }

        //Add to end (past end of list)
        if (location >= size) {
            tail = new DLLNode<>(value, tail, null);
            (tail.prev).next = tail;
            size++;
            return;
        }

        //Otherwise, add to the middle
        DLLNode<t> cur = head;
        int i = 0;
        while (i != location) {
            cur = cur.next;
            i++;
        }

        //Insert shifts the node currently at the location to the right
        (cur.prev).next = new DLLNode<>(value, cur.prev, cur);
        cur.prev = (cur.prev).next;
        size++;
        return;
    }


    /**
     * Returns value of the ith node in the list
     * @param i - index (zero-indexed) of target node
     * @return value of target node.
     */
    public t getValueAt(int i) {

        //Special Case - empty list
        if (head == null) return null;

        //Special Case - index less than zero
        if (i < 0) return null;

        //Special Case - first item
        if (i==0) {
            return head.info;
        }

        //Traverse to the ith indexed item (zero indexed)
        int j = 0;
        DLLNode<t> cur = head;
        while (j != i) {
            cur = cur.next;
            j++;

            //Special Case - index is out of bounds;
            if (cur == null) {
                return null;
            }
        }

        //Normal Case - return ith value
        return cur.info;
    }

    /**
     * Deletes the ith node in the list and returns its value
     * @param i - index (zero indexed) of the node to delete
     * @return value stored in the deleted node.
     */
    public t deleteValueAt(int i) {

        t tmp;

        //Special cases - empty list or bad index.
        if ((head == null) || (i < 0)) return null;

        //Special case - delete head item (i = 0)
        if (i==0) {
            tmp = head.info;

            //Special case - deleting last item
            if (head == tail) {
                head = tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return tmp;
        }

        //Special case - deleting tail
        if (i==(size-1)) {

            tmp = tail.info;
            tail = tail.prev;
            tail.next = null;
            size--;
            return tmp;

        }

        //Traverse to the ith indexed item (zero indexed)
        int j = 0;
        DLLNode<t> cur = head;
        while (j != i) {
            cur = cur.next;
            j++;

            //Special Case - index is out of bounds;
            if (cur == null) {
                return null;
            }
        }


        //Normal Case - delete ith node and return its value.
        (cur.prev).next = cur.next;
        (cur.next).prev = cur.prev;
        size--;
        tmp = cur.info;
        return tmp;
    }

    /**
     * Prints all elements in the list.
     */
    public void printAll()  {

        DLLNode<t> cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    /**
     * Prints all elements in the list in reverse!
     */
    public void printAllRev()  {

        DLLNode<t> cur = tail;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.prev;
        }
    }

    /**
     * @return size of linked list (number of nodes)
     */
    public int getSize() {
        return size;
    }


    /**
     * @return true if empty; false otherwise.
     */
    public boolean isEmpty() {
        return (size==0); //Compare size == 0 to see if empty
    }

    public static void main(String [] args) {

        DLList<String> list = new DLList<>();

        //Test Case - Empty List
        System.out.println("\nEmpty List Cases");
        System.out.println("\nForward:");
        list.printAll();
        System.out.println("\nReverse:");
        list.printAllRev();

        System.out.println(list.deleteValueAt(0)); //Should print null
        System.out.println(list.getValueAt(0)); //Should print null

        //Add Values
        System.out.println("\nTest Add");
        list.addToTail("Tom");
        list.addToTail("Dick");
        list.addToTail("Gary");
        System.out.println("\nForward:");
        list.printAll();
        System.out.println("\nReverse:");
        list.printAllRev();


        //Delete First Item
        System.out.println("\nDelete First Item");
        System.out.println(list.deleteValueAt(0)); //Should print null
        System.out.println("\nForward:");
        list.printAll();
        System.out.println("\nReverse:");
        list.printAllRev();


        //Test - Add after delete
        System.out.println("\nAdd after delete tests");
        list.addToTail("Larry");
        System.out.println("\nForward:");
        list.printAll();
        System.out.println("\nReverse:");
        list.printAllRev();

        //Test - get values at locations
        System.out.println("\nGet Tests");
        System.out.println(list.getValueAt(-1)); //Should print null
        System.out.println(list.getValueAt(3)); //Should print null
        System.out.println(list.getValueAt(0)); //Dick
        System.out.println(list.getValueAt(1)); //Gary
        System.out.println(list.getValueAt(2)); //Larry

        //Delete First Item
        System.out.println("\nDelete Last Item");
        System.out.println(list.deleteValueAt(2)); //Should print Larry
        System.out.println("\nForward:");
        list.printAll();
        System.out.println("\nReverse:");
        list.printAllRev();


        //Test - delete at locations
        System.out.println("\nDelete Tests");
        System.out.println(list.deleteValueAt(-1)); //Should print null
        System.out.println(list.deleteValueAt(2)); //Should print null
        System.out.println(list.deleteValueAt(1)); //Gary
        System.out.println(list.deleteValueAt(0)); //Dick
        System.out.println("\nForward:");
        list.printAll();
        System.out.println("\nReverse:");
        list.printAllRev();

        System.out.println("Test Adding at Locations");
        list.addToLocation("Joe",-1); //Should add Joe to the tail of the list
        System.out.println("Printing Lists for Add at Location - 0");
        list.printAll();
        list.printAllRev();
        list.addToLocation("Bob", 2); //Should add bob to tail of list.
        System.out.println("Printing Lists for Add at Location - 1");
        list.printAll();
        list.printAllRev();
        list.addToLocation("Sam", 0); //Should add Sam to head of list.
        System.out.println("Printing Lists for Add at Location - 2");
        list.printAll();
        list.printAllRev();
        list.addToLocation("Ron", 1); //Should add Ron between Sam and Joe
        System.out.println("Printing Lists for Add at Location - 3");
        list.printAll();
        list.printAllRev();
        System.out.println("Size of List: " + list.getSize()); //Should be 4



    }


}
