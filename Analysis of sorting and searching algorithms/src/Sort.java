import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

abstract class Sort {
    private static int binary_search(int[] arr, int key){
        int lo = 0;
        int hi = arr.length-1;
        while(lo <= hi){
            int mid = lo + (hi-lo) / 2;
            if(arr[mid] < key)
                lo = mid+1;
            else if(arr[mid]> key)
                hi = mid-1;
            else
                return mid;
        }
        return -1;
    }
    static int[] reverse_array(int[] arr){
        int n = arr.length;
        int[]arr2 = new int[n];
        for(int i = 0; i < n; i++)
            arr2[i] = arr[n - i - 1];
        return arr2;
    }
    static int[] create_random_array(int size){
        int[] arr = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            int num = random.nextInt(size * 10);
            while(binary_search(arr, num) != -1)
                num = random.nextInt(size*10);
            arr[i] = num;
        }
        return arr;
    }
    static int[] create_ordered_array(int size){
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = 100+i;
        }
        return arr;
    }
    static int[] shuffle_array(int[] arr){
        ArrayList<Integer> used = new ArrayList<>();
        for(int i = 0;i < arr.length; i++)
            used.add(arr[i]);

        Collections.shuffle(used);
        int[] arr2 = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            arr2[i] = used.get(i);
        return arr2;
    }
    static void swap(int[] array, int min, int j){
        int temp = array[min];
        array[min] = array[j];
        array[j] = temp;
    }
}
