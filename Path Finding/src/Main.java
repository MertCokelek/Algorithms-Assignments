import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Main {
    private static List<String> Cities;
    private static List<List<Cell>> matrix;
    public static void main(String [] args) throws Exception{
        Cities = get_cities(args[0]);
        matrix = read_transportation(args[0], Cities);
        read_query(args[1], args[2]);

    }
    private static List<String> get_cities(String file) throws Exception{
        List<String> cities = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            if(line.length() > 0)
                if (!(line.contains("Airway") || line.contains("Highway") || line.contains("Railway")))
                    if (!cities.contains(line.split(" ")[0]))
                        cities.add(line.split(" ")[0]);
            line = reader.readLine();
        }
        reader.close();
        return cities;
    }

    private static List<List<Cell>> read_transportation(String file, List<String> cities) throws Exception{
        List<List<Cell>> matrix = new ArrayList<>();

        String Source, Dest;
        char transport_type = 0;
        boolean edge;

        int city_counter = 0;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while (line != null) {
            if(line.length() > 0) {
                if (line.contains("Airway") || line.contains("Highway") || line.contains("Railway")) {               /* Airway, Highway, Railway. */
                    transport_type = line.charAt(0);
                    city_counter = 0;                              /* Reset City Counter (0, 1, 2...) */

                } else { /* Matrices */
                    if (matrix.isEmpty() || city_counter >= matrix.size()) {  /* (For the first transport type */
                        matrix.add(new ArrayList<>());
                        Source = cities.get(city_counter);
                        for (int i = 0; i < cities.size(); i++) {         /* Every single row of the matrix */
                            edge = (Integer.parseInt(line.split(" ")[1].substring(i, i + 1)) != 0);
                            Dest = cities.get(i);
                            matrix.get(city_counter).add(new Cell(Source, Dest, transport_type, edge));
                        }
                    } else {  /* Second and Third types of transportation. */
                        for (int i = 0; i < cities.size(); i++) {
                            edge = (Integer.parseInt(line.split(" ")[1].substring(i, i + 1)) != 0);
                            matrix.get(city_counter).get(i).map.put(transport_type, edge);
                        }
                    }
                    city_counter++;
                }
            }
            line = reader.readLine();
        }
        reader.close();
        return matrix;
    }

    private static void find_all_paths(List<String> all_paths,String source, String destination, String result){
        int source_index, destination_index;
        source_index = Cities.indexOf(source);
        destination_index = Cities.indexOf(destination);
        int n = matrix.size();

        if(source_index == destination_index){      /* Base Case */
            result += (", " + destination);
            all_paths.add(result);
        }
        else
            for(int i = 0; i < n; i++)             /* i = column counter */
                for(char key: matrix.get(source_index).get(i).map.keySet())
                    if(matrix.get(source_index).get(i).map.get(key).equals(true)){  /* Check if an edge exists. */
                        String res;
                        if(result.equals("")) res = result + source + ", " + key;
                        else                  res = result + ", " + source + ", " + key;
                        String new_source = Cities.get(i);
                        find_all_paths(all_paths, new_source, destination, res);

                    }

    }

    private static boolean query_requirements(String path, char transportation_type, int scalar){

        int counter = 0;
        String key = (new Character(transportation_type)).toString();
        String[] splitted = path.split(", ");
        for(String s: splitted) if(s.equals(key))  counter++;
        return counter >= scalar;

    }
    private static void Q1(PrintWriter result, String source, String destination, char transportation_type, int scalar){
        List<String> all_paths = new ArrayList<>();
        find_all_paths(all_paths, source, destination, "");
        if(all_paths.isEmpty())
            result.println("There is no way!");
        for(String query: all_paths)
            if(query_requirements(query, transportation_type, scalar))
                result.println(query);

    }

    private static boolean query_requirements(String path, String inter){
        List<String> splitted = Arrays.asList(path.split(", "));
        return splitted.contains(inter);
    }
    private static void Q2(PrintWriter result, String source, String destination, String inter){
        List<String> all_paths = new ArrayList<>();
        find_all_paths(all_paths, source, destination, "");
        if(all_paths.isEmpty())
            result.println("There is no way!");
        for(String query: all_paths)
            if(query_requirements(query, inter))
                result.println(query);
    }

    private static boolean query_requirements(String path, char transportation_type){
        String key = (new Character(transportation_type)).toString();
        String[] splitted = path.split(", ");
        List<String>types = new ArrayList<>();
        types.add("A"); types.add("R"); types.add("H"); types.remove(key);
        for(String s: splitted)
            if(types.contains(s)) return false;
        return true;

    }
    private static void Q3(PrintWriter result, String source, String destination, char type){
        List<String> all_paths = new ArrayList<>();
        find_all_paths(all_paths, source, destination, "");
        if(all_paths.isEmpty())
            result.println("There is no way!");
        for(String query: all_paths)
            if(query_requirements(query, type))
                result.println(query);
    }

    private static boolean query_requirements(String path, char t1, int t1c, char t2, int t2c, char t3, int t3c){
        List<String> splitted = Arrays.asList(path.split(", "));
        int t1counter = 0;
        int t2counter = 0;
        int t3counter = 0;
        String k1 = (new Character(t1)).toString();
        String k2 = (new Character(t2)).toString();
        String k3 = (new Character(t3)).toString();

        for(String s: splitted){
            if(s.equals(k1)) t1counter++;
            if(s.equals(k2)) t2counter++;
            if(s.equals(k3)) t3counter++;
        }
        return (t1c == t1counter && t2c == t2counter && t3c == t3counter);
    }
    private static void Q4(PrintWriter result, String source, String destination, char t1, int t1c, char t2, int t2c, char t3, int t3c){
        List<String> all_paths = new ArrayList<>();
        find_all_paths(all_paths, source, destination, "");
        if(all_paths.isEmpty())
            result.println("There is no way!");

        for(String query: all_paths)
            if(query_requirements(query, t1, t1c, t2, t2c, t3, t3c))
                result.println(query);
    }

    private static void PrintGraph(PrintWriter result){
        int n = matrix.size();
        for(int i = 0; i < n; i++){
            result.print(matrix.get(i).get(0).source + " --> ");
            for(int j = 0; j < n; j++){
                if(matrix.get(i).get(j).map.values().contains(true))
                    result.print(matrix.get(0).get(j).destination + " ");
            }
            result.println();
        }
    }

    private static void read_query(String query, String result) throws Exception{

        BufferedReader reader = new BufferedReader(new FileReader(query));
        PrintWriter Result = new PrintWriter(new FileWriter(result));
        String line = reader.readLine();
        while (line != null){
            line = line.replace("\n", "").replace("\r", "");
            String[] Line = line.split(" ");
            String command = Line[0];
            switch(command){
                case ("Q1"):
                    String source = Line[1];
                    String destin = Line[2];
                    int scalar = Integer.parseInt(Line[3]);
                    char type = Line[4].charAt(0);
                    line = line.replace(" ", ", ");
                    Result.println(line);
                    Q1(Result, source, destin, type, scalar); break;
                case ("Q2"):
                    source = Line[1];
                    destin = Line[2];
                    String inter_city = Line[3];
                    line = line.replace(" ", ", ");
                    Result.println(line);
                    Q2(Result, source, destin, inter_city); break;
                case ("Q3"):
                    source = Line[1];
                    destin = Line[2];
                    type = Line[3].charAt(0);
                    line = line.replace(" ", ", ");
                    Result.println(line);
                    Q3(Result, source, destin, type); break;
                case ("Q4"):
                    source = Line[1];
                    destin = Line[2];
                    char t1 = Line[3].charAt(0);
                    char t2 = Line[4].charAt(0);
                    char t3 = Line[5].charAt(0);
                    int c1 = Integer.parseInt(Character.toString(Line[3].charAt(1)));
                    int c2 = Integer.parseInt(Character.toString(Line[4].charAt(1)));
                    int c3 = Integer.parseInt(Character.toString(Line[5].charAt(1)));
                    line = line.replace(" ", ", ");
                    Result.println(line);
                    Q4(Result, source, destin, t1, c1, t2, c2, t3, c3); break;
                case("PRINTGRAPH"):
                    Result.println(line);
                    PrintGraph(Result); break;
                default:
                    Result.println(line + "\n" + "Invalid Command.");
            }
            line = reader.readLine();
        }
        Result.close();
        reader.close();
    }
}
