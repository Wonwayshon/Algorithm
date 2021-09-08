import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        LinkedList<Integer> linkedList=new LinkedList<>();
//        for(int i=1;i<6;i++){
//            linkedList.addFirst(i);
//            System.out.println(linkedList);
//        }
//        linkedList.add(233,3);
//        System.out.println(linkedList);
//    }

    public static void main(String[] args) {
        LinkedList<Integer> temp1=null;
        LinkedList<Integer> temp2=null;
        while(true) {
            System.out.println("          1…建立链表\n" +
                    "          2…连接链表\n" +
                    "          3…输出链表\n" +
                    "          0…结束\n");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    temp2 = temp1;
                    temp1 = CreateLinklist();
                    break;
                case 2:
                    if (temp1 == null && temp2 == null) {
                        throw new RuntimeException("请先创建链表!");
                    }
                    ContLinklist(temp1, temp2);
                    break;
                case 3:
                    if (temp1 == null && temp2 == null) {
                        throw new RuntimeException("请先创建链表!");
                    }
                    OutputLinklist(temp1, temp2);
                    break;
                case 0:
                    return;
            }
        }
    }

    //从键盘输入数据，创建一个单链表
    public static LinkedList<Integer> CreateLinklist(){
        LinkedList<Integer> linkedList=new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入创建的链表长度:");
        int input = sc.nextInt();
        for(int i=0;i<input;i++){
            System.out.println("请输入第"+(i+1)+"个结点的值：");
            linkedList.addLast(sc.nextInt());
        }
        return linkedList;
    }

    //将前面建立的两个单链表首尾相连
    public static void ContLinklist(LinkedList<Integer> temp1,LinkedList<Integer> temp2){
        for (int i = 0; i < temp2.getSize(); i++) {
            temp1.addLast(temp2.get(i));
        }
    }

    //输出显示链表
    public static void OutputLinklist(LinkedList<Integer> temp1,LinkedList<Integer> temp2){
        System.out.println("链表1:" + temp1.toString());
        System.out.println("链表2:" + temp2.toString());
    }
}
