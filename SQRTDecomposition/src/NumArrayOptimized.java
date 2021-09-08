import java.util.Arrays;

class NumArrayOptimized {
    private int[] data,blocks;
    private int length;
    private int blocksNum;
    private int blockSize;


    public NumArrayOptimized(int[] nums) {
        length = nums.length;
        if(length==0){
            return;
        }
        data = Arrays.copyOf(nums, length);
        blockSize = (int) Math.sqrt(length);
        blocksNum = length / blockSize + (length % blockSize > 0 ? 1 : 0);
        blocks = new int[blocksNum];
        for(int i=0;i<length;i++){
            blocks[i / blockSize] += data[i];
        }
    }

    public int sumRange(int left, int right) {
        if(left<0||left>=length||right<0||right>=length||left>right){
            return 0;
        }
        int startBlock = left / blockSize;
        int endBlock = right / blockSize;
        int result=0;
        if(startBlock==endBlock){
            for(int i=left;i<=right;i++){
                result += data[i];
            }
            return result;
        }
        if(left==startBlock*blockSize){
            result += blocks[startBlock];
        }else{
            for (int i = left; i < (startBlock + 1) * blockSize; i++) {
                result += data[i];
            }
        }
        for(int j=startBlock+1;j<endBlock;j++){
            result += blocks[j];
        }
        if(right==(endBlock+1)*blockSize-1 || right==length-1){
            result += blocks[endBlock];
        }else {
            for (int i = endBlock * blockSize; i <= right; i++) {
                result += data[i];
            }
        }
        return result;
    }

    public void update(int index, int val) {
        int blockIndex = index / blockSize;
        blocks[blockIndex] -= data[index];
        blocks[blockIndex] += val;
        data[index] = val;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
