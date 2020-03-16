import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.CollationKey;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Exp4 {
    public static class count{
        int c = -1;
    }

    public static void main(String[] args) throws Exception {
        read_input(args);
    }

    public static void read_input(String[] args) throws Exception{
        File[] files;

        List<String> names = new ArrayList<>();
        /* 'names' array will be sorted using tree sort. so tree object is created for names. */

        area_total area_total = new area_total(args, names);
        airport airport = new airport(args);
        area_land area_land = new area_land(args);
        area_water area_water = new area_water(args);
        birth_rate birth_rate = new birth_rate(args);
        death_rate death_rate = new death_rate(args);
        literacy literacy = new literacy(args);
        median_age_female median_age_female = new median_age_female(args);
        median_age_male median_age_male = new median_age_male(args);
        populations populations = new populations(args);

        BufferedReader reader = new BufferedReader(new FileReader(new File(args[1])));

        String Line = reader.readLine();

        PrintWriter writer = new PrintWriter(new File(args[2]));

        int index1, index2; // the print amount starting and ending indices ( top,3)
        int i = 0;

        List<String> queries = new ArrayList<>();
        while(Line != null) {
            queries.add(Line);
            Line = reader.readLine();
        }
        for(int k = 0; k < queries.size(); k++){
            String line = queries.get(k);

            BoyerMoore BoyerMoore1 = new BoyerMoore("+");
            /* BoyerMoore1 is for '+' char in queries.
            *  and later, for general purpose, according to its search parameters. */

            List<String> commands = BoyerMoore1.ayir(line, "+");
            List<String> result_countries = new ArrayList<>();
            for(String command: commands) {
                String arr_type = BoyerMoore1.ayir(command, ",").get(0);
                String top_last = BoyerMoore1.ayir(command, ",").get(1);
                int amount = Integer.parseInt(BoyerMoore1.ayir(command, ",").get(2));

                /* 'ayir' method corresponds to 'Split', but written w/BoyerMoore algorithm.  */
                if (BoyerMoore1.search(arr_type, "area-total") != -1)
                    create_result_tree(result_countries, names, area_total.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "area-land") != -1)
                    create_result_tree(result_countries, names, area_land.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "area-water") != -1)
                    create_result_tree(result_countries, names, area_water.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "population") != -1)
                    create_result_tree(result_countries, names, populations.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "median_age-female") != -1)
                    create_result_tree(result_countries, names, median_age_female.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "median_age-male") != -1)
                    create_result_tree(result_countries, names, median_age_male.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "airports") != -1)
                    create_result_tree(result_countries, names, airport.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "birth_rate") != -1)
                    create_result_tree(result_countries, names, birth_rate.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "death_rate") != -1)
                    create_result_tree(result_countries, names, death_rate.values, top_last, amount);
                 else if (BoyerMoore1.search(arr_type, "literacy") != -1)
                    create_result_tree(result_countries, names, literacy.values, top_last, amount);
                i++;
                if (i == commands.size()) {
                    BST country_tree = new BST();
                    for(String country: result_countries)
                        country_tree.insert_countries_ordered(country);
                    List<String> countries_ordered = new ArrayList<>();
                    BoyerMoore boyerMoore = new BoyerMoore("");
                    count count = new count();
                    country_tree.sort(boyerMoore, countries_ordered, country_tree.root, result_countries.size(), count);
                    writer.print(countries_ordered);
                    if(k != queries.size() -1) writer.println("");
		    countries_ordered.clear();
                    result_countries.clear();
                    count.c = -1;
                    i = 0;
                }
            }
            i = 0;
        }
        writer.close();
    }
    public static void create_result_tree(List<String> countries, List<String> names, List<Double> values, String type, int number){
        BST tree = new BST();
        BoyerMoore top = new BoyerMoore("top");
        /* 'top' object searches for top string in given path */
        if(top.search(type) != -1) {
            for (int i = 0; i < names.size(); i++)
                if(values.get(i) >= 0)
                    tree.insert_ordered_top(names.get(i), values.get(i));
        }else{
            for (int i = 0; i < names.size(); i++)
                if(values.get(i) >= 0)
                    tree.insert_ordered_last(names.get(i), values.get(i));
        }
        count count = new count();
        BoyerMoore boyerMoore2 = new BoyerMoore("");
        tree.sort(boyerMoore2, countries, tree.root, number, count);
    }
}
