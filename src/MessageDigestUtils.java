import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtils {
    public static String getMd5HashString(String string) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            StringBuilder result = new StringBuilder();
            try {
                byte[] digested = md5.digest(string.getBytes("GBK"));
                for (byte b : digested) {
                    String hex = Integer.toHexString(b & 255);
                    if (hex.length() == 1) {
                        result.append('0');
                    }
                    result.append(hex);
                }
                return result.toString();
            } catch (UnsupportedEncodingException ex) {
            }
        } catch (NoSuchAlgorithmException ex) {
        }
        return null;
    }
}
