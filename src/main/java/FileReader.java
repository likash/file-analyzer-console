import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor
public class FileReader {

    public String ReadAll(File file) {

        String content = new String();

        try  {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return content;
    }
}

