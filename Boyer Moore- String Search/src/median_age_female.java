public class median_age_female extends properties{
    int skip_amount = 8;
    String property =  "\"Median age\"";
    String property_type = " years";

    public median_age_female(String[] args) throws Exception{
        getProperties(args[0], values, 8, property, property_type);
    }
}
