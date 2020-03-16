public class populations extends properties {
    int skip_amount = 1;
    String property = "\"Population\"";
    public populations(String[] args) throws Exception {
        getProperties(args[0], values, 1, property, property);
    }
}
