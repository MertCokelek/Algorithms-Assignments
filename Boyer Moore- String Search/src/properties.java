import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class properties {
    List<Double> values = new ArrayList<>();
    String property;

    public void getProperties(String path, List<Double> values, int p, String property, String property_type) throws Exception{
        File[] f = new File(path).listFiles();
        if(f != null)
            for(File files: f)
                if (files.isDirectory()) {
                    File[] Files = files.listFiles();
                    if (Files != null)
                        get_infos(Files, values, property, property_type, p);
                }
    }
    private void get_infos(File[]F, List<Double> values, String property, String pt, int p) throws Exception{

        for(File file: F){
            boolean found = false;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            BoyerMoore Property = new BoyerMoore(property);
            BoyerMoore million = new BoyerMoore("million");
            while(line != null) {
                if(Property.search(line) != -1){
                    found = true;
                    for(int a = 0; a < p; a++)
                        line = reader.readLine();
                    if(property.toCharArray()[1] == 'A' || property.toCharArray()[1] == 'P')
                        if(million.search(line) != -1)
                            values.add(get_int_values(line) * 1000000);
                        else
                            values.add(get_int_values(line));
                    else
                        if(million.search(line) != -1)
                            values.add(get_info(line, pt) * 1000000);
                        else
                            values.add(get_info(line, pt));
                } else {
                    line = reader.readLine();
                }
            }
            reader.close();
            if(!found) values.add(-1.0);
        }
    }
    public String get_country_name(String str){
        BoyerMoore bm1 = new BoyerMoore("\"text\"");
        int index = bm1.search(str) + 9;
        String result = "";
        for(int i = index; i < str.length()-1 ; i++)
            result += str.toCharArray()[i];
        return result;
    }
    public double get_int_values(String str){
        BoyerMoore boyerMoore = new BoyerMoore("      \"text");
        int pop_start_index = boyerMoore.search(str) + 15;

        String temp = "";
        for(int i = pop_start_index ; i < str.length(); i++)
            temp += str.toCharArray()[i];

        BoyerMoore boyerMoore1 = new BoyerMoore(" ");
        int space_index = boyerMoore1.search(temp);
        BoyerMoore boyerMoore2 = new BoyerMoore("\"");
        int tirnak_index = boyerMoore2.search(temp);
        String result = "";
        for(int i = 0; i < tirnak_index; i++)
            result += temp.toCharArray()[i];
        if(space_index != -1) {
            result = "";
            for (int i = 0; i < space_index; i++)
                result += temp.toCharArray()[i];
        }
        String newres = "";
        for(int i = 0; i < result.length(); i++){
            if(result.charAt(i)!=',')
                newres += result.toCharArray()[i];
        }
        double res = Double.parseDouble(newres);
        return res;
    }
    public double get_info(String str, String path){
        BoyerMoore bm1 = new BoyerMoore("      \"text\"");
        int index = bm1.search(str) + 15;
        BoyerMoore bm2 = new BoyerMoore(path);
        int index2 = bm2.search(str);
        String newres = "";
        for(int i = index; i < index2; i++)
               newres += str.toCharArray()[i];
        double res = Double.parseDouble(newres);
        return res;
    }
}
