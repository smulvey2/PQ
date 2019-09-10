import java.util.Arrays;

public class TestMaxPQ {
    private static MaxPQ<Integer> testPQ = new MaxPQ<Integer>();

    public static void main(String[] args) {
        System.out.println(test1_isEmpty());
        System.out.println(test2_getMax_Exception());
        System.out.println(test3_removeMax_Exception());
        
        testPQ.insert(5);
        testPQ.insert(2);
        testPQ.insert(3);
        testPQ.insert(4);
        testPQ.insert(1);
        System.out.println(testPQ.print());
        
        System.out.println(test4_insert_remove_many());

    }
    
    public static String test1_isEmpty() {
        if (testPQ.isEmpty())
            return "test1_isEmpty passed: PQ was empty so isEmpty() returned true.";
        else
            return "test1_isEmpty failed: PQ was empty but isEmpty() returned false.";
    }
    
    public static String test2_getMax_Exception() {
        try {
            testPQ.getMax();
        } catch (EmptyQueueException e) {
            return "test2_getMax_Exception passed: PQ was empty so getMax() threw an exception.";
        }
        return "test2_getMax_Exception failed: PQ was empty but getMax() did not throw an exception.";
    }
    
    public static String test3_removeMax_Exception() {
        try {
            testPQ.removeMax();
        } catch (EmptyQueueException e) {
            return "test3_removeMax_Exception passed: PQ was empty so removeMax() threw an exception.";
        }
        return "test3_removeMax_Exception failed: PQ was empty but removeMax() did not throw an exception.";
    }
    
    public static String test4_insert_remove_many() {
        int [] arr = {1,2,3,4,5};
        for (int i = arr.length-1; i >= 0; i--) {
            Integer expected = arr[i];
            Integer actual = testPQ.removeMax();
            if (!expected.equals(actual)) {
                return "test4_insert_remove_many failed: " + expected + " " + actual;
            }
        }
        return "test4_insert_remove_many passed: ";
    }
}