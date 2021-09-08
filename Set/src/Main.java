import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BSTSet<String> bstSet=new BSTSet<>();
        double time1=setTest(bstSet,"A-Tale-of-Two-Cities.txt");
        System.out.println("BSTSet time:"+time1+"s");
        System.out.println();
        LinkedListSet<String> linkedListSet=new LinkedListSet<>();
        double time2=setTest(linkedListSet,"A-Tale-of-Two-Cities.txt");
        System.out.println("BSTSet time:"+time2+"s");
    }

    private static double setTest(Set<String> set,String filename){
        ArrayList<String> list = new ArrayList<>();
        FileOperation.readFile(filename, list);
        System.out.println(filename);
        System.out.println("Total:"+list.size());
        long startTime=System.nanoTime();
        for(String word:list){
            set.add(word);
        }
        System.out.println("Total defferent size:"+set.getSize());
        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }
}
