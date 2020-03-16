import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    class Node{
        int key;
        Node left, right;

        private Node(int k){
            key = k;
            left = null;
            right = null;
        }
    }
    private Node root;
    public BST(){
        root = null;
    }
    private void insert(int key){
        root = insert(root, key);
    }
    private Node insert(Node root, int key){
        if(root == null)
            root = new Node(key);
        if(root.key < key)
            root.right = insert(root.right, key);
        else if(root.key > key)
            root.left= insert(root.left, key);
        return root;
    }
    private void inOrder(FileWriter fw) throws IOException {
        if(root != null) {
            inOrder(fw, root);
            fw.write("\n");
        }
        else
            fw.write("error\n");
    }
    private void inOrder(FileWriter fw, Node root) throws  IOException{
        if(root != null){
            inOrder(fw, root.left);
            fw.write(root.key + " ");
            inOrder(fw, root.right);
        }
    }
    public void CreateBST(FileWriter fw, int[] arr) throws IOException{
        root = null;
        for(int i: arr)
            insert(i);
        fw.write("BST created with elements:");
        inOrder(fw);
    }
    public void CreateBSTH(FileWriter fw, int h) throws IOException{
        if(h > 0) {
            int n = (int) Math.pow(2, h + 1);
            /* Empty tree */
            root = null;
            root = insert_balanced(1, n - 1);
            fw.write("BST created with elements:");
            inOrder(fw);
        }
        else
            fw.write("error\n");
    }
    private Node insert_balanced(int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        /* First, add the left children until end. */
        node.left = insert_balanced(start, mid-1);
        /* Second, add the right children until end.  */
        node.right = insert_balanced(mid+1, end);
        /* Finally, return the current node (middle) */
        return node;
    }
    public void Height(FileWriter fw) throws  IOException{
        int h = Height(root);
        if(h == -1)
            fw.write("error\n");
        else
            fw.write("Height:" + h + "\n");
    }
    private int Height(Node node){
        if(node == null)
            return -1;
        return Math.max(Height(node.right), Height(node.left)) + 1;
    }
    public void Preorder(FileWriter fw) throws IOException{
        if(root != null) {
            fw.write("Preorder:");
            Preorder(fw, root);
            fw.write("\n");
        }
        else
            fw.write("error\n");
    }
    private void Preorder(FileWriter fw, Node node) throws IOException{
        if(node != null){
            fw.write(node.key + " ");
            Preorder(fw, node.left);
            Preorder(fw, node.right);
        }
    }
    public void LeavesAsc(FileWriter fw) throws IOException{
        if(root != null) {
            fw.write("LeavesAsc:");
            LeavesAsc(fw, root);
            fw.write("\n");
        }
        else
            fw.write("error\n");

    }
    private void LeavesAsc(FileWriter fw, Node node) throws IOException{
        if(node != null){
            LeavesAsc(fw, node.left);
            if(node.left == null && node.right == null)
                fw.write(node.key + " ");
            LeavesAsc(fw, node.right);
        }
    }
    private Node min(Node node){
        Node iter = node;
        while(iter.left != null)
            iter = iter.left;
        return iter;
    }
    public void DelRoot(FileWriter fw) throws IOException{
        if (root != null) {
        fw.write("Root Deleted:" + root.key + "\n");
        root = delete(root, root.key);
    }
    else
        fw.write("error\n");
    }
    public void DelRootLc(FileWriter fw) throws IOException {
        if (root!= null) {
            if(root.left != null) {
                fw.write("Left Child of Root Deleted:" + root.left.key + "\n");
                root.left = delete(root.left, root.left.key);
            }
            else
                fw.write("error\n");
        }
        else
            fw.write("error\n");
    }
    public void DelRootRc(FileWriter fw) throws IOException{
        if(root != null) {
            if (root.right != null) {
                fw.write("Right Child of Root Deleted:" + root.right.key + "\n");
                root.right = delete(root.right, root.right.key);

            }else
                fw.write("error\n");
        }
        else
            fw.write("error\n");
    }
    private Node delete(Node node, int key){
        if(node == null) return node;
        if(node.key < key)
            node.right = delete(node.right, key);
        else if(node.key > key)
            node.left = delete(node.left, key);
        else {
            if(node.left == null) return node.right;
            else if(node.right == null) return node.left;
            else{
                node.key = min(node.right).key;
                node.right = delete(node.right, node.key);
            }
        }
        return node;
    }
    public void Width(FileWriter fw) throws IOException{
        int max = 0;
        if(Width(root, max) != 0)
            fw.write("Width:" + Width(root, max) + "\n") ;
        else
            fw.write("error\n");
    }
    public int Width(Node root, int max){
        /* Counting each node per level, using level order traversal */
        Queue<Node> queue = new LinkedList<Node>();
        int nodes_per_level;
        if(root==null)
            return 0;
        queue.add(root);

        /* Classic Breadth First Search */
        while(!queue.isEmpty()){
            nodes_per_level = queue.size();

            /* If the current nodes in this level are greater than the previous ones, change. */
            if(nodes_per_level > max)
                max = nodes_per_level;

            /* Add all the children of the current level's nodes and remove those nodes. */
            for(int i = 0; i < nodes_per_level; i++){
                Node n = (Node)queue.remove();
                if(n.left!=null) queue.add(n.left);
                if(n.right!=null) queue.add(n.right);
            }
        }
        return max;
    }
}