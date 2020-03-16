public class area_water extends properties {
    String property = "\"Area\"";

    public area_water(String[]args) throws Exception {
        getProperties(args[0], values, 8, property, property);
    }
}
