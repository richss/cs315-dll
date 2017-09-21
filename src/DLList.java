/**
 * @author Richard S. Stansbury
 * @version 1.0, 2017-09-18
 *
 * Implementation of a singly linked list node with a head, but no tail.
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

            //Traverse the list until last node is reached
            DLLNode<t> cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }

            //Add new note after last item on list (i.e. cur.next
            tail = cur.next = new DLLNode<>(value);
            tail.prev = cur;

        }
        size++;
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


        //Special case - deleting the tail
        if (cur == tail) {
            tail = cur.prev;
            tail.next = null;
        }
        //Normal Case - delete ith node and return its value.
        else {
            (cur.prev).next = cur.next;
            (cur.next).prev = cur.prev;
        }

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

    }


}
