import java.util.Arrays;

public class SQRTDecomposition <E>{
    private Merger<E> merger;
    private E[] data, blocks;
    private int length;
    private int blockSize;
    private int blockNum;

    public SQRTDecomposition(E[] arr,Merger<E> merger){
        this.merger = merger;
        length = arr.length;
        if(length==0){
            return;
        }
        blockSize = (int) Math.sqrt(length);
        blockNum = length / blockSize + (length % blockSize > 0 ? 1 : 0);
        data = (E[]) new Object[length];
        for(int i=0;i<length;i++){
            data[i] = arr[i];
        }
        blocks = (E[]) new Object[blockNum];
        for(int i=0;i<length;i++){
            if(i%blockSize==0){
                blocks[i / blockSize] = data[i];
            }else{
                blocks[i / blockSize] = merger.merge(blocks[i / blockSize], data[i]);
            }
        }
    }

    public E queryRange(int l,int r){
        if(l<0 || l>=length || r<0 || r>=length || l>r){
            return null;
        }

        int startBlock = l / blockSize;
        int endBlock = r / blockSize;
        E result = data[l];
        if(startBlock==endBlock){
            for(int i=l+1;i<=r;i++){
                result = merger.merge(result, data[i]);
            }
            return result;
        }
        for(int i=l+1;i<(startBlock+1)*blockSize;i++){
            result = merger.merge(result, data[i]);
        }
        for(int j=startBlock+1;j<endBlock;j++){
            result = merger.merge(result, blocks[j]);
        }
        for(int i=endBlock*blockSize;i<=r;i++){
            result = merger.merge(result, data[i]);
        }
        return result;
    }

    public void update(int index,E val){
        if(index<0 || index>=length){
            return;
        }
        data[index] = val;
        int blockIndex = index / blockSize;
        E result = data[blockIndex * blockSize];
        for(int i=blockIndex*blockSize+1;i<Math.min((blockIndex+1),length);i++){
            result = merger.merge(result, data[i]);
        }
        blocks[blockIndex] = result;
    }
}
