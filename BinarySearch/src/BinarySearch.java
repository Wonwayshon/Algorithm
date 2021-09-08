import java.util.Scanner;

public class BinarySearch {
    private BinarySearch(){}

    public static <E extends Comparable<E>> int search(E[] arr,E target){
        int l = 0, r = arr.length - 1;

        while(l<=r){
            int mid=l+(r-l)/2;
            if(arr[mid].compareTo(target)==0){
                return mid;
            }
            if(arr[mid].compareTo(target)>0){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }

        return -1;
    }

    public static <E extends Comparable<E>> int searchR(E[] arr,E target){
        return searchR(arr,0,arr.length-1,target);
    }

    private static <E extends Comparable<E>> int searchR(E[] arr,int l,int r,E target){
        if(l>r){
            return -1;
        }

        int mid = l + (r - l) / 2;

        if(arr[mid].compareTo(target)==0){
            return mid;
        }
        if(arr[mid].compareTo(target)<0){
            return searchR(arr,mid+1,r,target);
        }
        return searchR(arr, l, mid - 1, target);
    }

    //用求解>=target的最小索引的思路实现search
    public static <E extends Comparable<E>> int search2(E[] arr,E target){
        int l=0,r=arr.length;
        while(l<r){
            int mid=l+(r-l)/2;
            if(arr[mid].compareTo(target)>0){
                l=mid+1;
            }else{
                r=mid;
            }
        }
        if(l<arr.length&&arr[l].compareTo(target)==0){
            return l;
        }else{
            return -1;
        }
    }

    //找出>target的第一个元素
    public static <E extends Comparable<E>> int upper(E[] arr,E target){
        int l=0,r=arr.length;
        while(l<r){
            int mid=l+(r-l)/2;
            if(arr[mid].compareTo(target)<=0){
                l=mid+1;
            }else{
                r=mid;
            }
        }
        return l;
    }

    // ==target 返回等于target的最大索引 <target 返回大于target值的最小索引
    public static <E extends Comparable<E>> int ceil(E[] arr,E target){
        int result=upper(arr,target);
        if(result>=1&&arr[result-1].compareTo(target)==0){
            return result-1;
        }
        return result;
    }

    //返回>=target的最小索引
    public static <E extends Comparable<E>> int lowerCeil(E[] arr,E target){
        int l=0,r=arr.length;
        while(l<r){
            int mid=l+(r-l)/2;
            if(arr[mid].compareTo(target)<0){
                l=mid+1;
            }else{
                r=mid;
            }
        }
        return l;
    }

    //返回<target的最大索引
    public static <E extends Comparable<E>> int lower(E[] arr,E target){
        int l=-1,r=arr.length-1;
        while(l<r){
            int mid =1+ l + (r - l ) / 2;
            if(arr[mid].compareTo(target)>=0){
                r=mid-1;
            }else{
                l=mid;
            }
        }
        return l;
    }

    //<target返回最大值索引 ==target返回最小值索引
    private static <E extends Comparable<E>> int lowerFloor(E[] arr,E target){
        int index = lower(arr, target);
        if(index+1< arr.length && arr[index+1].compareTo(target)==0){
            return index+1;
        }
        return index;
    }

    //返回<=target的最大值索引
    private static <E extends Comparable<E>> int upperFloor(E[] arr,E target){
        int l=-1,r=arr.length-1;
        while(l<r){
            int mid =1+ l + (r - l ) / 2;
            if(arr[mid].compareTo(target)>0){
                r=mid-1;
            }else{
                l=mid;
            }
        }
        return l;
    }

//    public static void main(String[] args) {
//        Integer[] arr={1,1,3,3,5,5};
//        for(int i=0;i<=6;i++){
//            System.out.print(BinarySearch.lower(arr, i)+" ");
//        }
//        System.out.println();
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str=sc.next();
        Character[] c = new Character[str.length()];
        for(int i=0;i<str.length();i++){
            c[i] = str.charAt(i);
        }
        System.out.println("请输入要查找的字符:");
        String target = sc.next();
        Character t = target.charAt(0);
        System.out.print(BinarySearch.search(c, t));
    }

}
