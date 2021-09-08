public class SubstringMatchingHelper {
    private SubstringMatchingHelper(){}

    public static void matchTest(String name,String s,String t){
        int pos = -1;

        long startTime = System.nanoTime();

        if(name.equals("BruteForce")){
            pos=SubstringMatch.bruteForce(s, t);
        }else if(name.equals("rk")){
            pos=SubstringMatch.rabinKarp(s, t);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;

        //检查结果是否正确
        if(s.indexOf(t)!=pos){
            throw new RuntimeException("Incorrect result!");
        }

        System.out.println(String.format("%s : res= %d, time= %f s", name, pos,time));
    }
}
