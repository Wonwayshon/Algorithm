import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //测试ArrayQueue
//        ArrayQueue<Integer> queue=new ArrayQueue<>();
//        for(int i=0;i<10;i++){
//            queue.enqueue(i);
//            System.out.println(queue);
//            if(i%3==2){
//                queue.dequeue();
//                System.out.println(queue);
//            }
//        }

        //测试LoopQueue
//        LoopQueue<Integer> queue=new LoopQueue<>(5);
//        for(int i=0;i<10;i++){
//            queue.enqueue(i);
//            System.out.println(queue);
//            if(i%3==2){
//                queue.dequeue();
//                System.out.println(queue);
//            }
//        }

        //测试ArrayQueue和LoopQueue的性能
        int opCount=100000;

        ArrayQueue<Integer> arrayQueue=new ArrayQueue<>();
        double arrayQueueTime=queueTester(arrayQueue,opCount);
        System.out.println("ArrayQueueTime:"+arrayQueueTime);

        LoopQueue<Integer> loopQueue=new LoopQueue<>();
        double loopQueueTime=queueTester(loopQueue,opCount);
        System.out.println("LoopQueueTime:"+loopQueueTime);
    }

    private static double queueTester(Queue<Integer> queue, int opCount){
        Long startTime=System.nanoTime();

        Random random=new Random();
        for(int i=0;i<opCount;i++){
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i=0;i<opCount;i++){
            queue.dequeue();
        }

        Long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }
}

