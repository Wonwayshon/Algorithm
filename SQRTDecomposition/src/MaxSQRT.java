import java.util.Arrays;

public class MaxSQRT {
    private int[] data,blocks;
    private int length;
    private int blockSize;
    private int blockNum;

    public MaxSQRT(int[] nums){
        length = nums.length;
        if(length==0){
            return;
        }
        data = Arrays.copyOf(nums, length);
        blockSize = (int)Math.sqrt(length);
        blockNum = length / blockSize + (length % blockSize > 0 ? 1 : 0);
        blocks = new int[blockNum];
        Arrays.fill(blocks,Integer.MIN_VALUE);
        for(int i=0;i<length;i++){
            if(data[i]>blocks[i/blockSize]){
                blocks[i / blockSize] = data[i];
            }
        }
    }

    public int maxRange(int l,int r){
        if(l<0 || l>=length || r<0 || r>=length || l>r){
            throw new IllegalArgumentException("Illegal l and r!");
        }

        int startBlock = l / blockSize;
        int endBlock = r / blockSize;
        int result=data[l];
        if(startBlock==endBlock){
            for(int i=l+1;i<=r;i++){
                if(data[i]>result){
                    result = data[i];
                }
            }
            return result;
        }
        for(int i=l;i<(startBlock+1)*blockSize;i++){
            if(data[i]>result){
                result = data[i];
            }
        }
        for(int j=startBlock+1;j<endBlock;j++){
            if(blocks[j]>result){
                result = blocks[j];
            }
        }
        for(int i=endBlock*blockSize;i<=r;i++){
            if(data[i]>result){
                result = data[i];
            }
        }
        return result;
    }

    public void update(int index,int val){
        int blockIndex = index / blockSize;
        data[index] = val;
        if(val>=blocks[blockIndex]){
            blocks[blockIndex]=val;
        }else{
            int max=val;
            for(int i=blockIndex*blockSize;i<Math.min((blockIndex+1)*blockSize,length);i++){
                if(data[i]>max){
                    max = data[i];
                }
            }
            blocks[blockIndex] = max;
        }
    }
}
