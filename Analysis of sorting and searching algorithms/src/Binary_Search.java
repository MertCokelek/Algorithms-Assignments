import java.util.Random;
public class Binary_Search {
    private static int binary_search(int[] arr, int key){
        int lo = 0, hi = arr.length -1;
        int mid;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2 ;
            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                lo = mid + 1;
            else
                hi = mid-1;
        }
        return -1;
    }
    void results(double[] worst, double[] avg, double[] best){
        long et = 0, st = 0;
        for(int Time = 0; Time < 10; Time++){
            for(int i = 0; i < 100; i++) {
                int size = (i+1) * 10000 + 1;
                int[] arr = new int[size];
                for(int j = 0; j < size; j++)
                    arr[j] = j;

                /* Best Case: Searching for the middle element */
                st = System.nanoTime();
                binary_search(arr,size / 2);
                et = System.nanoTime();
                best[i] += (et - st);

                /* Average Case: Searching for random element */
                Random random = new Random();
                int key = random.nextInt(size+1);
                st = System.nanoTime();
                binary_search(arr, key);
                et = System.nanoTime();
                avg[i] += (et - st) ;


                /* Worst Case: Searching for element that does not exist */
                System.nanoTime();
                binary_search(arr,-956);
                et = System.nanoTime();
                worst[i] += (et - st) ;
            }
        }
    }
}
