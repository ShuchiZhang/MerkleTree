import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

    List<String> transactionRecords;
    String root;

    public MerkleTree(List<String> txList) {
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
        while (index < transacrions.size()) {
            String left = transacrions.get(index);
            index++;
            String right = "";
            if (index != transacrions.size()) {
                right = transacrions.get(index);
            }
            // Value is the hash value of sum of two children
            String sha2HexValue = getSHA2HexValue(left + right);
            hashValueList.add(sha2HexValue);
            index++;
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
