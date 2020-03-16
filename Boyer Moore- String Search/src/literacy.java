public class literacy extends properties {
    String property = "\"Literacy\"";
    private String property_type = "%";

    public literacy(String[] args) throws Exception{
        getProperties(args[0], values, 11, property, property_type);
    }
}
