package ws.academy;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings(args);

        if (settings.isCompressOperation()) {
            try (Reader input = new FileReader(settings.getInputFilePath());
                 Writer output = new FileWriter(settings.getOutputFilePath())
            ) {
                getArchiver().compress(input, output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (settings.isExtractOperation()) {
            try (Reader input = new FileReader(settings.getInputFilePath());
                 Writer output = new PrintWriter(System.out)
            ) {
                getArchiver().extract(input, output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Arrays.toString(args));
    }

    public static Archiver getArchiver() {

    }
}
