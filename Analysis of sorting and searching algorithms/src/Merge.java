public class Merge extends Sort {
    private int[] merge(int[] array, int[] left, int[] right){
        int s1 = left.length;
        int s2 = right.length;

        int i = 0 , j = 0;
        int x = 0;
        while(i < s1 && j < s2)
            if(left[i] < right[j])
                array[x++] = left[i++];
            else
                array[x++] = right[j++];
        if(i < s1)
            while(i < s1)
                array[x++] = left[i++];
        else if (j < s2)
            while(j < s2)
                array[x++] = right[j++];
        return array;

    }
    private int[] sort(int[] array){
        int n = array.length;
        if(n == 1)
            return array;
        int s1 = n/2;
        int s2 = n - s1;
        int[] left = new int[s1];
        int[] right = new int[s2];

        for(int i = 0; i < s1; i++)
            left[i] = array[i];
        for(int i = 0; i < s2; i++)
            right[i] = array[s1 + i];

        left = sort(left);
        right = sort(right);
        return merge(array, left, right);
    }

    private void calculate_worst_case(int[] arr, double[] worst, int i){
        long st = System.nanoTime();
        sort(arr);
        long et = System.nanoTime();
        worst[i] += (et - st) / 10e6;

    }
    private void calculate_average_case(int[] arr, double[] avg, int i){
        long st = System.nanoTime();
        sort(arr);
        long et = System.nanoTime();
        avg[i] += (et - st) / 10e6;

    }
    private void calculate_best_case(int[] arr, double[] best, int i){
        long st = System.nanoTime();
        sort(arr);
        long et = System.nanoTime();
        best[i] += (et - st) / 10e6;

    }

    void results(double[] worst, double[] avg, double[] best){
        for(int Time = 0; Time < 10; Time++){
            for(int i = 0; i < 10; i++) {
                int[] arr = create_ordered_array((i+1) * 100000);
                calculate_worst_case(arr, worst, i);
                calculate_average_case(arr, avg, i);
                calculate_best_case(arr, best, i);
            }
        }
    }
}
