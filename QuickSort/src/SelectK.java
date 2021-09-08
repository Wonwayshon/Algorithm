import java.util.Arrays;
import java.util.Random;

class SelectK {
    public int[] getLeastNumbers(int[] arr, int k) {
        Random rnd=new Random();
        sort(arr,0,arr.length-1,k-1,rnd);
        return Arrays.copyOf(arr, k);
    }

    private void sort(int[] arr,int l,int r,int target,Random rnd){
        if(l>=r){
            return;
        }
        int p=partition(arr,l,r,rnd);
        if(target==p){
            return;
        }else if(target<p){
            sort(arr,l,p-1,target,rnd);
        }else{
            sort(arr,p+1,r,target,rnd);
        }
    }

    private int partition(int[] arr,int l,int r,Random rnd){
        int k=rnd.nextInt(r-l+1)+l;
        swap(arr, l, k);
        int i=l+1,j=r;
        while(true){
            while(i<=j&&arr[l]>arr[i]){
                i++;
            }
            while(i<=j&&arr[l]<arr[j]){
                j--;
            }
            if(i>=j){
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    //非递归方法
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            Random rnd=new Random();
            int target=nums.length-k;
            int l=0,r=nums.length-1;
            while(l<=r){
                int p=partition(nums,l,r,rnd);
                if(p==target){
                    break;
                }
                if(p>target){
                    r=p-1;
                }else{
                    l=p+1;
                }
            }
            return nums[target];
        }

        private int partition(int[] arr,int l,int r,Random rnd){
            int k=rnd.nextInt(r-l+1)+l;
            swap(arr, l, k);
            int i=l+1,j=r;
            while(true){
                while(i<=j&&arr[l]>arr[i]){
                    i++;
                }
                while(i<=j&&arr[l]<arr[j]){
                    j--;
                }
                if(i>=j){
                    break;
                }
                swap(arr, i, j);
                i++;
                j--;
            }
            swap(arr, l, j);
            return j;
        }

        private void swap(int[] arr,int i,int j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
    }
}