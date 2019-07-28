import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtils {
    public static String getMd5HashString(String string) {
        try {
            MessageDigest md5 = getMd5Instance();
            byte[] digested = md5.digest(string.getBytes("GBK"));
            return hashToString(digested);
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public static String getMd5HashString(File file) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            MessageDigest md5 = getMd5Instance();
            byte[] buffer = new byte[1024 * 1024];
            int byteCount;
            while ((byteCount = bis.read(buffer)) != -1) {
                md5.update(buffer, 0, byteCount);
            }
            return hashToString(md5.digest());
        } catch (IOException ex) {
            return null;
        }
    }

    private static MessageDigest getMd5Instance() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    private static String hashToString(byte[] hash) {
        StringBuilder result = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(b & 255);
            if (hex.length() == 1) {
                result.append('0');
            }
            result.append(hex);
        }
        return result.toString();
    }
}
