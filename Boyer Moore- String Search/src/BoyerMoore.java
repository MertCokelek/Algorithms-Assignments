import java.util.ArrayList;
import java.util.List;

public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private String pat;      // or as a string

    public BoyerMoore(String pat) {
        this.R = 100000;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.toCharArray().length; j++)
            right[pat.toCharArray()[j]] = j;
    }
    public int search(String txt) {
        int m = pat.toCharArray().length;
        int n = txt.toCharArray().length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.toCharArray()[j] != txt.toCharArray()[i+j]) {
                    skip = Math.max(1, j - right[txt.toCharArray()[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return -1;                       // not found
    }
    public int search(String txt, String pattern) {
        int n = txt.toCharArray().length;
        int m = pattern.toCharArray().length;
        int skip;
        for (int a = 0; a <= n - m; a += skip) {
            skip = 0;
            for (int b = m-1; b >= 0; b--) {
                if (pattern.toCharArray()[b] != txt.toCharArray()[a+b]) {
                    skip = Math.max(1, b - right[txt.toCharArray()[a+b]]);
                    break;
                }
            }
            if (skip == 0) return a;    // found
        }
        return -1;                       // not found
    }

    public void preprocess_strong_suffix(int[]shift, int[] bpos, String pat, int m){
        int i = m;
        int j = m+1;
        bpos[i] = j;
        while(i > 0){
            while(j <= m && pat.toCharArray()[i-1] != pat.toCharArray()[j-1]){
                if(shift[j] == 0) shift[j] = j-i;
                j = bpos[j];
            }
            i--; j--;
            bpos[i] = j;
        }
    }
    public void preprocess_case2(int[] shift, int[] bpos, int m){
        int i, j;
        j = bpos[0];
        for(i = 0; i <=m; i++){
            if(shift[i] == 0)
                shift[i] = j;
            if(i == j)
                j = bpos[j];
        }
    }
    public List<Integer> Search(String txt){
        List<Integer> indices = new ArrayList<>();
        int s = 0;
        int j;
        int m = pat.toCharArray().length;
        int n = txt.toCharArray().length;
        int[] bpos = new int[m+1];
        int[] shift= new int[m+1];

        for(int i = 0; i < m+1; i++)
            shift[i] = 0;
        preprocess_strong_suffix(shift, bpos, pat, m);
        preprocess_case2(shift, bpos, m);

        while(s <= n-m){
            j = m-1;
            while(j >= 0 && pat.toCharArray()[j] == txt.toCharArray()[s+j]) j--;
            if(j < 0){
                indices.add(s);
                s += shift[0];
            }else
                s += shift[j+1];
        }
        return indices;
    }

    public List<String> ayir(String text, String patt){
        this.pat = patt;
        List<Integer> indices = Search(text);
        List<String> splitted = new ArrayList<>();
        int N = indices.size();
        if(N == 0) {
            splitted.add(text);
            return splitted;
        }else if(N == 1){
            String s1 = "";
            String s2 = "";
            for(int i = 0; i < indices.get(0); i++){
                s1 += text.toCharArray()[i];
            }
            for(int i = indices.get(0)+1; i < text.toCharArray().length; i++){
                s2 += text.toCharArray()[i];
            }
            splitted.add(s1);
            splitted.add(s2);
            return splitted;
        }else{
            String s1 = "";
            String s2 = "";
            String s3 = "";
            for(int i = 0; i < indices.get(0); i++){
                s1 += text.toCharArray()[i];
            }
            for(int i = indices.get(0)+1; i < indices.get(1); i++){
                s2 += text.toCharArray()[i];
            }
            for(int i = indices.get(1)+1; i < text.toCharArray().length; i++){
                s3 += text.toCharArray()[i];
            }
            splitted.add(s1);
            splitted.add(s2);
            splitted.add(s3);
            return splitted;
        }
    }

    public boolean list_does_not_have_that_element(List<String> list, String pat){
        boolean flag = true;
        for(String s: list)
            if(search(s, pat) != -1)
                flag = false;
        return flag;
    }
}