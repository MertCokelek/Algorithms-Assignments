import java.io.PrintWriter;
import java.util.Locale;

public class Assignment1 {


    private static void print_selection(PrintWriter pw, double[] selection_worst, double[] selection_best, double[] selection_avg){
        int i = 1;
        pw.println("begin worst selection");
        for(Double d: selection_worst) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");
        i = 1;
        pw.println("begin avr selection");
        for(Double d: selection_avg) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        i = 1;
        pw.println("end\n");
        pw.println("begin best selection");
        for(Double d: selection_best) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");

    }
    private static void print_insertion(PrintWriter pw,double[] insertion_worst, double[] insertion_best, double[] insertion_avg){
        int i = 1;
        pw.println("begin worst insertion");
        for(Double d: insertion_worst) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");
        i = 1;
        pw.println("begin avr insertion");
        for(Double d: insertion_avg) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        i = 1;
        pw.println("end\n");
        pw.println("begin best insertion");
        for(Double d: insertion_best) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");

    }
    private static void print_merge(PrintWriter pw,double[] merge_worst, double[] merge_best, double[] merge_avg){
        int i = 1;
        pw.println("begin worst merge");
        for(Double d: merge_worst) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");
        i = 1;
        pw.println("begin avr merge");
        for(Double d: merge_avg) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        i = 1;
        pw.println("end\n");
        pw.println("begin best merge");
        for(Double d: merge_best) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");

    }
    private static void print_radix(PrintWriter pw,double[] radix_worst, double[] radix_best, double[] radix_avg){
        int i = 1;
        pw.println("begin worst radix");
        for(Double d: radix_worst) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");
        i = 1;
        pw.println("begin avr radix");
        for(Double d: radix_avg) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        i = 1;
        pw.println("end\n");
        pw.println("begin best radix");
        for(Double d: radix_best) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i * 1000, s);
            i++;
        }
        pw.println("end\n");

    }
    private static void print_search(PrintWriter pw, double[] search_worst, double[] search_best, double[] search_avg){
        int i = 1;
        pw.println("begin worst binary");
        for(Double d: search_worst) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i++*100 , s);
        }
        pw.println("end\n");
        i = 1;
        pw.println("begin avr binary");
        for(Double d: search_avg) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i++ *100, s);
        }
        i = 1;
        pw.println("end\n");
        pw.println("begin best binary");
        for(Double d: search_best) {
            String s = String.format(Locale.US, "%.5f", d/10);
            pw.printf("%d,%s\n", i++ *100, s);
        }
        pw.print("end");
    }
    public static void main(String[] args) throws Exception{

        PrintWriter printWriter = new PrintWriter("output.txt");

        /* Creating objects for calling corresponding sorting methods.  */
        Selection selection = new Selection();
        Insertion insertion = new Insertion();
        Merge        merge  = new Merge();
        Radix         radix = new Radix();
        Binary_Search binary_search = new Binary_Search();
        /* ------------------------------------------------------------ */

        /* Arrays are holding the execution times for 10 different sizes.
        * Every algorithm has its own arrays for three cases: 'best, worst, average'. */

        double[]  selection_best = new double[10];
        double[] selection_worst = new double[10];
        double[]  selection_avg = new double[10];
        selection.results(selection_worst, selection_avg, selection_best);

        double[]  insertion_best = new double[10];
        double[] insertion_worst = new double[10];
        double[]  insertion_avg = new double[10];
        insertion.results(insertion_worst, insertion_avg, insertion_best);

        double[]  merge_best = new double[10];
        double[] merge_worst = new double[10];
        double[]   merge_avg = new double[10];
        merge.results(merge_worst, merge_avg, merge_best);

        double[]  radix_best = new double[10];
        double[] radix_worst = new double[10];
        double[]   radix_avg = new double[10];
        radix.results(radix_worst, radix_avg, radix_best);

        double[] search_best = new double[100];
        double[] search_worst = new double[100];
        double[] search_avg = new double[100];
        binary_search.results(search_worst, search_avg, search_best);
        /* -------------------------------------------------------------- */

        /* Sorts are being done and arrays are getting filled. */

        /* Output is written. */
        print_radix(printWriter, radix_worst, radix_best, radix_avg);
	print_selection(printWriter, selection_worst, selection_best, selection_avg);
        print_insertion(printWriter, insertion_worst, insertion_best, insertion_avg);
	print_merge(printWriter, merge_worst, merge_best, merge_avg);
        print_search(printWriter, search_worst, search_best, search_avg);

        printWriter.close();
    }
}
