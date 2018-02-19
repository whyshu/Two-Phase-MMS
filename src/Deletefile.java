import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Deletefile {
    public void del(String file_name) throws IOException {
        Path folderPath= Paths.get(Constants.DATA_DIR);
        Files.list(folderPath).filter(p -> p.toString().contains(file_name)).forEach((p) -> {
            try {
                Files.deleteIfExists(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] argv) throws IOException {
        Deletefile d=new Deletefile();
        d.del(Constants.SORTED_FILE_PREFIX);
        d.del(Constants.UNSORTED_FILE_PREFIX);
    }
}
