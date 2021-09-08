import java.util.Arrays;
import java.util.Random;

public class ShellSort {
    private ShellSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            for (int i =h; i < data.length; i ++) {
                int j;
                E temp = data[i];
                for (j = i; j - h >= 0 && data[j - h].compareTo(temp) > 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = temp;
            }
            h = h / 2;
        }
    }

    public static <E extends Comparable<E>> void sortBasic(E[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            for (int start = 0; start < h; start++) {
                for (int i = start + h; i < data.length; i += h) {
                    int j;
                    E temp = data[i];
                    for (j = i; j - h >= 0 && data[j - h].compareTo(temp) > 0; j -= h) {
                        data[j] = data[j - h];
                    }
                    data[j] = temp;
                }
            }
            h = h / 2;
        }
    }

    public static <E extends Comparable<E>> void sortOptimized(E[] data) {
        int h = 0;
        while(h*3+1<data.length){
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i =h; i < data.length; i ++) {
                int j;
                E temp = data[i];
                for (j = i; j - h >= 0 && data[j - h].compareTo(temp) > 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = temp;
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        int n = 100;
        Integer[] testArray=new Integer[n];
        for(int i=0;i<n;i++){
            testArray[i]=(rnd.nextInt(100));
        }
        ShellSort.sortOptimized(testArray);
        System.out.println(Arrays.toString(testArray));
        for(int i=1;i<testArray.length;i++){
            if(testArray[i-1]>testArray[i]){
                throw new IllegalArgumentException("ERROR!");
            }
        }
    }
}
