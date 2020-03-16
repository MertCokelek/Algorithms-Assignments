public class birth_rate extends properties {
    String property = "\"Birth rate\"";
    String property_type = " births";
    public birth_rate(String[] args) throws Exception{
        getProperties(args[0], values, 1, property, property_type);
    }
}
