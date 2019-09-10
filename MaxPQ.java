//p1 pq
//Steven Mulvey
//smulvey2@wisc.edu
//2/5/18
public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E> {
    private E[] points;
    private static final int INITIAL_SIZE = 10;
    private int amountPoints;

    // start of constructor to demonstrate how to 
    // initialize an array for a generic type.
    public MaxPQ() {
        this.points = (E[]) new Comparable[INITIAL_SIZE];
        this.amountPoints = 0;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 1; i < points.length; i++) {
            if (points[i] != null) {
              empty = false;
              break;
            }
        }
        return empty;   
    }
    
    public StringBuilder print() {
        StringBuilder str = new StringBuilder("");
        for (E i : points) {
            str.append(i + " ");
        }
        return str;
    }
    
    private int getParent(int childIndex) {
        // return the parent index of child index
        return childIndex / 2;
    }

    private int getLeftChild(int parentIndex) {
        // return the left child index of parent index
        return parentIndex * 2;
    }

    private int getRightChild(int parentIndex) {
        // return the right child index of parent index
        return parentIndex * 2 + 1;
    }
    
    private void swap(int pointOneIndex, int pointTwoIndex) {
        // swaps the point references at pointOneIndex and pointTwoIndex in the points array
        E temp = points[pointOneIndex];
        points[pointOneIndex] = points[pointTwoIndex];
        points[pointTwoIndex] = temp;
    }

    private void insertHelper(int index) {
        //inserts point at index, propagates from highest priority point
        if (points[getParent(index)] != null && points[index] != null) {
            if (points[getParent(index)].compareTo(points[index]) < 0) {
                swap(index, getParent(index));
                insertHelper(getParent(index));
            }
        }
    }
    
    public void insert(E point) {
        // Add the given point to the points array and perform the necessary
        // actions to maintain the min-heap properties.
        if (amountPoints == points.length) {
            throw new IllegalStateException("WARNING: point not added.  This vending machine is already filled to capacity.");
        }
        points[++amountPoints] = point;
        int current = amountPoints;
        insertHelper(current);
    }

    @Override
    public E getMax() throws EmptyQueueException {
        if (amountPoints == 0)
            throw new EmptyQueueException();
        return points[1];
    }
    
    private void removeHelper(int index) {
       //removes point at index, propagates from highest priority point
        int i = index;
        if (getLeftChild(i) < amountPoints && getRightChild(i) < amountPoints) {
            int bigger = i;
            int left = getLeftChild(i);
            int right = getRightChild(i);
            if (points[left] != null && points[right] != null) {
                if (points[left].compareTo(points[right]) > 0 && points[left].compareTo(points[i]) > 0) {
                    bigger = right;
                }
                if (points[right].compareTo(points[left]) > 0 && points[right].compareTo(points[i]) > 0) {
                    bigger = left;
                }
                swap(i, bigger);
                removeHelper(bigger);
            }
            if (points[left] == null && points[right] == null) {
                swap(i, bigger);
            }
            if (points[left] != null || points[right] != null) {
                if (points[left] != null) {
                    bigger = right;
                }
                if (points[right] != null) {
                    bigger = left;
                }
                swap(i, bigger);
                removeHelper(bigger);
            }
        }
    }

    @Override
    public E removeMax() throws EmptyQueueException {
        if (amountPoints == 0)
            throw new EmptyQueueException();
        E popped = points[1];
        points[1] = points[amountPoints--];
        removeHelper(1);
        return popped;
    }

    @Override
    public int size() {
        return amountPoints + 1;
    }
}
