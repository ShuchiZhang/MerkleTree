import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String [] args) {
        List<String> transactionList = new ArrayList<String>();
        transactionList.add("transA");
        transactionList.add("transB");
        transactionList.add("transC");
        transactionList.add("transD");
        transactionList.add("transE");

        MerkleTree merkleTree1 = new MerkleTree(transactionList);
        merkleTree1.constructTree();
        System.out.println("initial root value: " + merkleTree1.getRoot());

        transactionList.add(3,"transF");
        MerkleTree merkleTree2 = new MerkleTree(transactionList);
        merkleTree2.constructTree();
        System.out.println("current root value: " + merkleTree2.getRoot());
    }
}
