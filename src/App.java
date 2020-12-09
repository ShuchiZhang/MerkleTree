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

        transactionList.add("transG");
        transactionList.add("transH");
        transactionList.add("transI");
        transactionList.add("transJ");
        transactionList.add("transK");
        transactionList.add("transL");
        MultiNodeMerkleTree mnmTree3 = new MultiNodeMerkleTree(3,transactionList);
        mnmTree3.constructTree();
        System.out.println("3-nodes root value: " + mnmTree3.getRoot());

        MultiNodeMerkleTree mnmTree5 = new MultiNodeMerkleTree(5,transactionList);
        mnmTree5.constructTree();
        System.out.println("5-nodes root value: " + mnmTree5.getRoot());
    }
}
