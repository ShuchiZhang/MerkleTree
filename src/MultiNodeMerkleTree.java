import java.security.MessageDigest;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class MultiNodeMerkleTree {

    int numberOfChildren;
    List<String> transactionRecords;
    String root;

    // Define the number of children for each node
    public MultiNodeMerkleTree(int numberOfChildren, List<String> txList) {
        this.numberOfChildren = numberOfChildren;
        this.transactionRecords = txList;
        root = "";
    }

    // Construct the tree using recursive function till only one root node
    public void constructTree() {

        List<String> hashValueList = calculateHashValue(transactionRecords);

        while (hashValueList.size() != 1) {
            hashValueList = calculateHashValue(hashValueList);
        }

        this.root = hashValueList.get(0);
    }

    // Calculate the hash value for each node which is determined by their children
    private List<String> calculateHashValue(List<String> transacrions) {
        List<String> hashValueList = new ArrayList<String>();
        int index = 0;
        int nodes = 0;
        while (index < transacrions.size()) {
            String value = "";
            // Each node should have numberOFChildren children
            while((index != transacrions.size()) && (nodes < numberOfChildren)){
                value = value + transacrions.get(index);
                nodes++;
                index++;
            }
            String sha2HexValue = getSHA2HexValue(value);
            hashValueList.add(sha2HexValue);
            nodes = 0;
        }
        return hashValueList;
    }

    // Get the SHA-256 value for a string
    public String getSHA2HexValue(String str) {
        byte[] cipher_byte;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            cipher_byte = md.digest();
            StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
            for(byte b: cipher_byte) {
                sb.append(String.format("%02x", b&0xff) );
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getRoot() {
        return this.root;
    }
}
