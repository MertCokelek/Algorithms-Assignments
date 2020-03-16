class Insertion extends Sort{

    private void sort(int[] array){
      int n = array.length;
      int key, j;
      for(int i = 0; i < n; i++){
          key = array[i];
          j = i - 1;
          while(j >= 0 && array[j] > key){
              array[j+1] = array[j];
              j-=1;
          }
          array[j+1] = key;
      }
    }
    void results(double[] worst, double[] avg, double[] best){
        long et = 0, st = 0;
        for(int Time = 0; Time < 10; Time++){
            for(int i = 0; i < 10; i++) {
                int[] arr = create_random_array((i+1) * 1000);

                st = System.nanoTime();
                sort(arr);
                et = System.nanoTime();
                avg[i] += (et - st) / 10e6;

                st = System.nanoTime();
                sort(arr);
                et = System.nanoTime();
                best[i] += (et - st) / 10e6;

                arr = reverse_array(arr);
                st = System.nanoTime();
                sort(arr);
                et = System.nanoTime();
                worst[i] += (et - st) / 10e6;
            }
        }
    }

}
