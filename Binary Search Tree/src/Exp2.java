import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Exp2 {
    public static void main(String[] args) throws Exception {
        BST tree = new BST();

        List<String> list = new ArrayList<String>();
        File input = new File(args[0]);
        list = Files.readAllLines(input.toPath(), Charset.defaultCharset());
        FileWriter fw = new FileWriter(args[1]);
        for(String line: list){
            String Command = line.split(" ")[0];
            switch (Command){
                case("CreateBST"):
                    String[] keys = line.split(" ")[1].split(",");
                    int[] Keys = new int[keys.length];

                    for(int i = 0; i < keys.length; i++)
                        Keys[i] = Integer.parseInt(keys[i]);
                    tree.CreateBST(fw, Keys);
                    break;
                case("FindHeight"):
                    tree.Height(fw); break;
                case("FindWidth"):
                    tree.Width(fw);
                    break;
                case("Preorder"):
                    tree.Preorder(fw); break;
                case("LeavesAsc"):
                    tree.LeavesAsc(fw); break;
                case("DelRoot"):
                    tree.DelRoot(fw); break;
                case("DelRootLc"):
                    tree.DelRootLc(fw); break;
                case("DelRootRc"):
                    tree.DelRootRc(fw); break;
                case("CreateBSTH"):
                    int h = Integer.parseInt(line.split(" ")[1]);
                    tree.CreateBSTH(fw, h); break;
                default: break;
            }
        }
        fw.close();
    }
}