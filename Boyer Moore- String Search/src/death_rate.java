public class death_rate extends properties {
    int skip_amount = 1;
    String property = "\"Death rate\"";
    String property_type = " deaths";

    public death_rate(String[] args) throws Exception{
        getProperties(args[0], values, 1, property, property_type);
    }
}
