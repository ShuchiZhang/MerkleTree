import java.security.MessageDigest;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class MultiNodeMerkleTree {

    int numberOfChildren;
    List<String> transactionRecords;
    String root;

    public MultiNodeMerkleTree(int numberOfChildren, List<String> txList) {
        this.numberOfChildren = numberOfChildren;
        this.transactionRecords = txList;
        root = "";
    }

    public void constructTree() {

        List<String> hashValueList = calculateHashValue(transactionRecords);

        while (hashValueList.size() != 1) {
            hashValueList = calculateHashValue(hashValueList);
        }

        this.root = hashValueList.get(0);
    }

    private List<String> calculateHashValue(List<String> transacrions) {
        List<String> hashValueList = new ArrayList<String>();
        int index = 0;
        int nodes = 0;
        while (index < transacrions.size()) {
            String value = "";
            while((index != transacrions.size()) && (nodes < numberOfChildren)){
                value = value + transacrions.get(index);
                nodes++;
                index++;
            }
//            String left = transacrions.get(index);
//            index++;
//            String right = "";
//            if (index != transacrions.size()) {
//                right = transacrions.get(index);
//            }
            String sha2HexValue = getSHA2HexValue(value);
            hashValueList.add(sha2HexValue);
            nodes = 0;
        }
        return hashValueList;
    }

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
