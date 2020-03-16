public class area_land extends properties{
    String property = "\"Area\"";
    public area_land(String[] args) throws Exception {
        getProperties(args[0], values, 5, property, property);
    }
}
