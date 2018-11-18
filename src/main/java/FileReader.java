import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class FileReader {

    public List<File> getTxtFilesByDirectory (String path) {
        List<File> files = new ArrayList<>();
        try {
            files = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .filter(path1 -> path1.toString().endsWith(".txt"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return files;
    }

    public String ReadAll(File file) {

        String content = new String();

        try {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return content;
    }
}


