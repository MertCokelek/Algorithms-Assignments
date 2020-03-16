class Radix extends Sort{

    private static int getMax(int[] arr) {
        int mx = arr[0];
        for(int i: arr)
            if(i > mx)
                mx = i;
        return mx;
    }
    private void sort(int[] arr, int m) {
        int n = arr.length;
        for (int exp = 1; m / exp > 0; exp *= 10) {
            int[] output = new int[n]; // output array
            int i;
            int[] count = new int[10];

            for (i = 0; i < n; i++)
                count[(arr[i] / exp) % 10]++;

            for (i = 1; i < 10; i++)
                count[i] += count[i - 1];

            for (i = n - 1; i >= 0; i--) {
                output[count[(arr[i] / exp) % 10] - 1] = arr[i];
                count[(arr[i] / exp) % 10]--;
            }

            for (i = 0; i < n; i++)
                arr[i] = output[i];
        }
    }

    void results(double[] worst, double[] avg, double[] best){
        long et = 0, st = 0;
        for(int Time = 0; Time < 10; Time++){
            for(int i = 0; i < 10; i++) {
                int[] arr = create_random_array((i+1) * 1000);
                int max = getMax(arr);
                st = System.nanoTime();
                sort(arr, max);
                et = System.nanoTime();
                avg[i] += (et - st) / 10e6;

                st = System.nanoTime();
                sort(arr, max);
                et = System.nanoTime();
                best[i] += (et - st) / 10e6;

                arr = reverse_array(arr);
                st = System.nanoTime();
                sort(arr, max);
                et = System.nanoTime();
                worst[i] += (et - st) / 10e6;
            }
        }
    }

}
