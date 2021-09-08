import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    private BubbleSort(){}

    public static <E extends Comparable<E>> void sort(E[] data){
        for(int i=0;i<data.length-1;i++){
            for(int j=0;j<data.length-i-1;j++){
                if(data[j].compareTo(data[j+1])>0){
                    swap(data,j,j+1);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] data){
        for(int i=0;i<data.length-1;i++){
            boolean isSwapped=false;
            for(int j=0;j<data.length-i-1;j++){
                if(data[j].compareTo(data[j+1])>0){
                    swap(data,j,j+1);
                    isSwapped = true;
                }
            }
            if(!isSwapped){
                break;
            }
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] data){
        for(int i=0;i<data.length-1;){
            int lastSwappedIndex=0;
            for(int j=0;j<data.length-i-1;j++){
                if(data[j].compareTo(data[j+1])>0){
                    swap(data,j,j+1);
                    lastSwappedIndex = j+1;
                }
            }
            i=data.length-lastSwappedIndex;
        }
    }

    public static <E extends Comparable<E>> void sortReverse(E[] data){
        for(int i=0;i<data.length-1;){
            int lastSwappedIndex = data.length-1;
            for (int j=data.length-1;j>i;j--){
                if(data[j].compareTo(data[j-1])<0){
                    swap(data, j, j - 1);
                    lastSwappedIndex = j - 1;
                }
            }
            i=lastSwappedIndex+1;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] arr,int i,int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n=10000;
        Random rnd = new Random();
        Integer[] test = new Integer[n];
        for(int i=0;i<n;i++){
            test[i] = rnd.nextInt(n);
        }
        BubbleSort.sortReverse(test);
        System.out.println(Arrays.toString(test));
    }
}
