import java.util.Iterator;

public class app {
    public static void main(String[] args) {
        Array array1 = new Array();
        array1.add(3);
        array1.add(4);
        array1.add(8);
        array1.add(9);
        array1.add(5);
        array1.print();

        int[] array2 = {9, 4, 8, 7};
        array1.addAll(array2);
        array1.print();

        array1.addAll(2, new int[]{5, 5, 5});
        array1.print();

        array1.removeRange(2, 6);
        array1.print();

        array1.removeByIndex(4);
        array1.print();

        Iterator iterator = array1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}