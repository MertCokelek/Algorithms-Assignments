import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class area_total extends properties  {
    String property = "\"Area\"";
    public area_total(String[]args, List<String> Names) throws Exception {
        getProperties(args[0], Names, values, 2, property, property);
    }
    public void getProperties(String path, List<String> names, List<Double> values, int p, String property, String property_type) throws Exception{

        File[] f = new File(path).listFiles();
        if(f != null)
            for(File files: f)
                if (files.isDirectory()) {
                    File[] Files = files.listFiles();
                    if (Files != null)
                        get_infos(Files, names, values, property, property_type, p);
                }
    }
    private void get_infos(File[]F, List<String> names, List<Double>values, String property, String pt,  int p) throws Exception{
        for(File file: F){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            BoyerMoore Property = new BoyerMoore(property);
            BoyerMoore name = new BoyerMoore("\"Country name\"");
            BoyerMoore million = new BoyerMoore("million");
            while(line != null) {
                if(name.search(line) != -1){
                    for(int a = 0; a < 5; a++) line = reader.readLine();
                    names.add(get_country_name(line));

                }else if(Property.search(line) != -1){
                    for(int a = 0; a < p; a++) line = reader.readLine();
                    if(property.toCharArray()[1] == 'A' || property.toCharArray()[1] == 'P')
                        if(million.search(line) != -1)
                            values.add(get_int_values(line) * 1000000);
                        else
                            values.add(get_int_values(line));
                    else
                        if(million.search(line) != -1)
                            values.add(get_info(line, pt));
                        else
                            values.add(get_info(line, pt) * 1000000);

                } else
                    line = reader.readLine();
            }
            reader.close();
        }
    }
}