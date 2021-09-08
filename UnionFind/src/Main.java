import java.util.Random;

public class Main {
    private static double testUF(UF uf,int m){
        int size=uf.getSize();
        long startTime=System.nanoTime();
        Random rnd=new Random();
        for(int i=0;i<m;i++){
            uf.unionElements(rnd.nextInt(size),rnd.nextInt(size));
        }
        for(int i=0;i<m;i++){
            uf.isConnected(rnd.nextInt(size),rnd.nextInt(size));
        }
        long endTime=System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int size=100000,m=100000;

//        UF uf1=new UnionFind1(size);
//        System.out.println("UniFind1:"+testUF(uf1,m)+" s");
//
//        UF uf2=new UnionFind2(size);
//        System.out.println("UniFind2:"+testUF(uf2,m)+" s");

        UF uf3=new UnionFind3(size);
        System.out.println("UniFind3:"+testUF(uf3,m)+" s");

        UF uf4=new UnionFind4(size);
        System.out.println("UniFind4:"+testUF(uf4,m)+" s");

        UF uf5=new UnionFind5(size);
        System.out.println("UniFind5:"+testUF(uf5,m)+" s");

        UF ufwr=new UnionFindWithoutRank(size);
        System.out.println("UniFindWithoutRank:"+testUF(ufwr,m)+" s");

        UF uf6=new UnionFind6(size);
        System.out.println("UniFind6:"+testUF(uf5,m)+" s");
    }
}
