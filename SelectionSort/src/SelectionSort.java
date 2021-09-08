public class SelectionSort {

    private SelectionSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
       //arr[0,i)已排序 arr[i,arr.length)未排序
        for(int i=0;i<arr.length;i++){
            int minIndex=i;
            //在arr[i,arr.length)中寻找最小值的索引
            for(int j=minIndex;j<arr.length;j++){
                if(arr[minIndex].compareTo(arr[j])>0){
                    minIndex=j;
                }
            }
            //完成数组两元素的交换
            swap(arr,i,minIndex);
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        //arr[0,i)未排序 arr[i,arr.length)已排序
        for(int i=arr.length-1;i>-1;i--){
            int maxIndex=i;
            for(int j=maxIndex;j>-1;j--){
                if(arr[maxIndex].compareTo(arr[j])<0){
                    maxIndex=j;
                }
            }
            swap(arr,i,maxIndex);
        }
    }

    private static <E> void swap(E[] arr,int i,int minIndex){
        E temp=arr[i];
        arr[i]=arr[minIndex];
        arr[minIndex]=temp;
    }

    public static void main(String[] args) {
//        Integer[] arr={6,5,5,2,1,4,3};
//        SelectionSort.sort(arr);
//        for(int i:arr){
//            System.out.print(i+" ");
//        }
//        System.out.println();

//        Student[] students={new Student("A",100),
//                new Student("B",80),
//                new Student("C",90),
//                new Student("D",70)};
//        SelectionSort.sort(students);
//        for(Student stu:students){
//            System.out.print(stu+" ");
//        }
//        System.out.println();

//        int[] dataSize={1000,10000,100000};
//        for(int n:dataSize) {
//            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
//            SortingHelper.sortTest("SelectionSort", arr);
//        }

        Integer[] arr={6,5,5,2,1,4,3};
        SelectionSort.sort2(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
