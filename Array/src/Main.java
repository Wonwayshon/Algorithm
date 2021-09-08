import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        Array<Integer> arr=new Array<>();
//        for(int i=0;i<20;i++){
//            arr.addLast(i);
//        }
//        System.out.println(arr);
//        arr.addFirst(-1);
//        arr.removeLast();
//        System.out.println(arr);
//        arr.removeLast();
//        System.out.println(arr);
//        arr.removeLast();
//        System.out.println(arr);
//    }

    public static void main(String[] args) {
        Array<Integer> array = null;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("          1…建立顺序表\n" +
                    "          2…查找表内元素\n" +
                    "          3…输出顺序表\n" +
                    "          4…表中元素是否对称\n"+
                    "          0…结束\n");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    array=createArray();
                    break;
                case 2:
                    if(array==null){
                        throw new RuntimeException("顺序表不存在！");
                    }
                    System.out.println("请输入要查找的内容：");
                    int e= sc.nextInt();
                    System.out.println(findInArray(array,e));
                    break;
                case 3:
                    if(array==null){
                        throw new RuntimeException("顺序表不存在！");
                    }
                    showArray(array);
                    break;
                case 4:
                    if(array==null){
                        throw new RuntimeException("顺序表不存在！");
                    }
                    System.out.println(isSymmetric(array));
                    break;
                case 0:
                    return;
            }
        }
    }

    //建立顺序表
    public static Array<Integer> createArray(){
        Array<Integer> array=new Array<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入创建的链表长度:");
        int input = sc.nextInt();
        for(int i=0;i<input;i++){
            System.out.println("请输入第"+(i+1)+"个结点的值：");
            array.addLast(sc.nextInt());
        }
        return array;
    }

    //查找表内元素
    public static int findInArray(Array<Integer> array,int e){
        return array.find(e);
    }

    //输出表内容
    public static void showArray(Array<Integer> array){
        System.out.println(array.toString());
    }

    //判断该顺序表中元素是否对称
    public static int isSymmetric(Array<Integer> array){
        for(int i=0,j=array.getSize()-1;i<j;i++,j--){
            if(array.get(i)!=array.get(j)){
                return 0;
            }
        }
        return 1;
    }
}
