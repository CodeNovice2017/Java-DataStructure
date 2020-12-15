package sort;

public class HeapSort {


    public static void heapSort(int arr[]){

        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length; // 最初整个数组都是堆范围

        swap(arr, 0, --heapSize); // 数组的头尾节点交换,并且堆的范围减1

        while(heapSize > 0){

            heapify(arr,0,heapSize);

            swap(arr, 0, --heapSize);
        }

    }

    private static void heapInsert(int arr[],int index){

        while(arr[index] > arr[(index-1)/2]){ // 不用担心第一次index=0的时候,通过测试System.out.println((-1/2));输出为0

            swap(arr,(index-1)/2,index);

            index = (index-1)/2;
        }
    }

    // 卡壳的地方
    private static void heapify(int arr[],int index,int heapSize){

        int left = 2 * index + 1;

        while(left < heapSize){ // 也可以写成left <= heapSize -1 因为heapSize表示的是此时堆中元素个数,按照数组下标来说left应该不超过heapSize -1,但可以取到heapSize -1

            int largestIndex = (left+1 < heapSize) && (arr[left+1] > arr[left]) ? left+1 : left; // 在右孩子不越界的情况下 获取左右孩子之中较大孩子的下标
            
            largestIndex = arr[index] > arr[largestIndex] ? index : largestIndex;

            if(largestIndex == index){
                break;
            }

            swap(arr, largestIndex, index);

            index = largestIndex;

            left = index * 2 + 1;
        }

    }
    
    private static void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
