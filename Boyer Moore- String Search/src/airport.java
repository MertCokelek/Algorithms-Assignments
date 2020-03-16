public class airport extends properties {
    String property = "\"Airports\"";
    public airport(String[] args) throws Exception{
        getProperties(args[0], values, 1, property, property);
    }
}
