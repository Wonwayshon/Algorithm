public class LinearSearch {
    private LinearSearch() {

    }

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        Integer[] intArray={1,2,3,4,5,6,7,8};
//        int result=LinearSearch.<Integer>search(intArray,5);
//        System.out.println(result);
//        int result2=LinearSearch.search(intArray,10);
//        System.out.println(result2);

        int data[] = {10000, 100000, 1000000};
        for (int n : data) {
//        int n=1000000;
            int idx = 0;
            Integer[] array = ArrayGenerator.generateOrderedArray(n);
            long startTime = System.nanoTime();

            for (int i = 0; i < 1000; i++) {
                LinearSearch.search(array, n);
                idx = i;
            }

            long endTime = System.nanoTime();
            long timeSpend = endTime - startTime;
            double time = timeSpend / 1000000000.0;
            System.out.println("n=" + n + ", " + (idx + 1) + " runs:" + time + " s");
        }
    }

}