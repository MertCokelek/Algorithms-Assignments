import java.util.List;

public class BST {
    class Node{
        String key;
        double val;
        Node left, right;
        public Node(String k, double v){
            key = k;
            val = v;
            left = null;
            right = null;
        }
    }
    Node root;

    public BST(){
        root = null;
    }
    private Node insert_ordered_top(Node root, String key, double val) {
        if (root == null) {
            root = new Node(key, val);
            return root;
        }

        if(root.val <=  val) root.right = insert_ordered_top(root.right, key, val);
        else if(root.val > val) root.left = insert_ordered_top(root.left, key, val);
        return root;
    }
    public void insert_ordered_top(String key, double val){
        root = insert_ordered_top(root, key, val);
    }
    private Node insert_ordered_last(Node root, String key, double val){
        if (root == null) {
            root = new Node(key, val);
            return root;
        }
        if(root.val <=  val) root.left = insert_ordered_last(root.left, key, val);
        else if(root.val > val) root.right= insert_ordered_last(root.right, key, val);
        return root;
    }
    public void insert_ordered_last(String key, double val){
        root = insert_ordered_last(root, key, val);
    }
    public void sort(BoyerMoore boyerMoore, List<String> countries, Node node, int n, Exp4.count C){
        if (node == null || C.c > n) return;
        sort(boyerMoore, countries, node.right,n,C);
        if(++(C.c)<n )
            if(boyerMoore.list_does_not_have_that_element(countries, node.key))
              countries.add(node.key);
        sort(boyerMoore, countries, node.left,n,C);
    }

    private static boolean kucuktur(char[] str1, char[] str2, int i, int n){

        if(i < n)
            if (str1[i] < str2[i]) {
                return true;
            }
            else if (str1[i] > str2[i]) {
                return false;
            }
            else return kucuktur(str1, str2, i + 1, n);

        return false;
    }
    private static boolean esittir(char[]str1, char[] str2){
        boolean esittir = true;
        if(str1.length == str2.length){
            for(int i = 0; i < str1.length; i++) {
                if(str1[i] != str2[i])
                    esittir = false;
            }
        }else
            esittir = false;
        return esittir;
    }
    public static boolean KucukEsittir(String s1, String s2){
        boolean kucuktur = kucuktur(s1.toCharArray(), s2.toCharArray(), 0, Math.min(s1.toCharArray().length, s2.toCharArray().length));
        boolean esittir  = esittir(s1.toCharArray(), s2.toCharArray());
//        if(kucuktur)
//            System.out.println(s1 +  " kucuktur "  + s2);
//        else if(esittir)
//            System.out.println(s1 + " esittir " + s2);
//        else
//            System.out.println(s1 + " buyuktur " + s2);
        return kucuktur || esittir;
    }

    private Node insert_countries_ordered(Node root, String country){
        if(root == null){
            root = new Node(country, 0);
            return root;
        }
        if(KucukEsittir(root.key, country)) root.left = insert_countries_ordered(root.left, country);
        else root.right = insert_countries_ordered(root.right, country);
        return root;
    }
    public void insert_countries_ordered(String country){
        root = insert_countries_ordered(root, country);
    }
}