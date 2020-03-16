class Selection extends Sort {

    private void sort(int[] array){
        int n = array.length;
        int min;
        for(int i = 1; i < n-1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min])
                    min = j;
            }
            swap(array, min, i);
        }
    }
    void results(double[] worst, double[] avg, double[] best){
        long et, st;
        for(int Time = 0; Time < 10; Time++){
            for(int i = 0; i < 10; i++) {
                int[] arr = create_ordered_array((i+1) * 1000);

                st = System.nanoTime();
                sort(arr);
                et = System.nanoTime();
                best[i] += (et - st) / 10e6;

                arr = reverse_array(arr);
                st = System.nanoTime();
                sort(arr);
                et = System.nanoTime();
                worst[i] += (et - st) / 10e6;

                arr = shuffle_array(arr);
                st = System.nanoTime();
                sort(arr);
                et = System.nanoTime();
                avg[i] += (et - st) / 10e6;
            }
        }
    }
}
