import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static boolean deleteFile(String filename) {
        return new File(filename).delete();
    }

    public static boolean deleteFile(File file) {
        return file.delete();
    }

    public static void copy(String source, String destination) throws IOException {
        copy(new File(source), new File(destination));
    }

    public static void copy(File source, File destination) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source)); BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {
            copy(bis, bos);
        }
    }

    public static void copy(InputStream source, File destination) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(source); BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {
            copy(bis, bos);
        }
    }

    private static void copy(BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
        byte[] buffer = new byte[1024 * 1024];
        int byteCount;
        while ((byteCount = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, byteCount);
        }
    }
}
