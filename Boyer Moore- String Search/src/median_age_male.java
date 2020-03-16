public class median_age_male extends properties{
    int skip_amount = 5;
    String property = "\"Median age\"";
    String property_type = " years";

    public median_age_male(String[] args) throws Exception{
        getProperties(args[0], values, 5, property, property_type);
    }
}
