import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@AllArgsConstructor
public class FileReader {

    private String path;

    public ArrayList<String> ReadByLine() {
        File file = new File(path);
        ArrayList<String> lines = new ArrayList<>();

        try (java.io.FileReader fr = new java.io.FileReader(file)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return lines;
    }


}

