import java.util.Iterator;

public class Array implements Iterable {

    private int[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    Array() {
        arr = new int[DEFAULT_CAPACITY];
    }

    Array(int capacity) {
        arr = new int[capacity];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(int number) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int number) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == number) {
                return i;
            }
        }
        return -1;
    }

    public int get(int index) {
        if (index < size) {
            return arr[index];
        }
        return -1;
    }

    public int set(int index, int number) {
        int oldValue = -1;
        if (index < size) {
            oldValue = arr[index];
            arr[index] = number;
        }
        return oldValue;
    }

    public boolean isIndexCorrect(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        return true;
    }

    public boolean add(int number) {
        ensureCapacity(1);
        arr[size] = number;
        size++;
        return true;
    }

    public boolean addAtPosition(int index, int number) {
        if (!isIndexCorrect(index)) {
            return false;
        }
        ensureCapacity(1);
        int[] oldArr = copyArray(size);
        size++;
        arr[index] = number;
        for (int i = index + 1; i < size; i++) {
            arr[i] = oldArr[i - 1];
        }
        return true;
    }

    public boolean addAll(int[] arrToAdd) {
        ensureCapacity(arrToAdd.length);
        int index = size;
        size += arrToAdd.length;
        for (int i = 0, j = index; j < size; i++, j++) {
            arr[j] = arrToAdd[i];
        }
        return true;
    }

    public boolean addAll(int index, int[] arrToAdd) {
        if (index < 0 || index > size) {
            return false;
        }
        if (index == (size)) {
            addAll(arrToAdd);
        } else {
            ensureCapacity(arrToAdd.length);
            int[] oldArr = copyArray(size);
            size += arrToAdd.length;
            for (int i = 0, j = index; i < arrToAdd.length; i++, j++) {
                arr[j] = arrToAdd[i];
            }
            for (int i = index, j = index + arrToAdd.length; i < oldArr.length; i++, j++) {
                arr[j] = oldArr[i];
            }
        }
        return true;
    }

    private void ensureCapacity(int lengthToAdd) {
        int oldCapacity = arr.length;
        int newCapacity;
        if ((size + lengthToAdd) >= oldCapacity) {
            newCapacity = oldCapacity + (oldCapacity * 2);
            if ((newCapacity - lengthToAdd) < 0) {
                newCapacity = lengthToAdd;
            }
            arr = copyArray(newCapacity);
        }
    }

    private int[] copyArray(int newSize) {
        int[] newArr = new int[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public int remove(int number) {
        int foundIndex = indexOf(number);
        if (foundIndex == -1) {
            return -1;
        }
        return removeByIndex(foundIndex);
    }

    public int removeByIndex(int index) {
        if (!isIndexCorrect(index)) {
            return -1;
        }
        int[] oldArr = copyArray(size);
        int oldValue = arr[index];
        size--;
        for (int i = index; i < size; i++) {
            arr[i] = oldArr[i + 1];
        }
        return oldValue;
    }

    public boolean removeRange(int fromIndex, int toIndex) {
        if (!isIndexCorrect(fromIndex) || fromIndex > toIndex) {
            return false;
        }
        if (toIndex > size) {
            toIndex = size;
        }
        int[] oldArr = copyArray(size);
        int remSize = toIndex - fromIndex;
        size -= (toIndex - fromIndex);
        for (int i = fromIndex; i < size; i++) {
            arr[i] = oldArr[i + remSize];
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]" + arr[i] + "\t");
        }
        System.out.println();
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(arr, size);
    }
}