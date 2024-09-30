package ws.academy;

public class Settings {
    private String inputFilePath;
    private String outputFilePath;
    private boolean isCompress;
    private boolean isExtract;

    Settings(String[] runArgs) {
        for (int i = 0; i < runArgs.length; i++) {
            switch (runArgs[i]) {
                case "-c" -> isCompress = true;
                case "-e" -> isExtract = true;
                case "-input" -> inputFilePath = runArgs[++i];
                case "-output" -> outputFilePath = runArgs[++i];
            }
        }
    }

    boolean isCompressOperation() {
        return isCompress;
    }

    boolean isExtractOperation() {
        return isExtract;
    }

    String getInputFilePath() {
        return inputFilePath;
    }

    String getOutputFilePath() {
        return outputFilePath;
    }
}
