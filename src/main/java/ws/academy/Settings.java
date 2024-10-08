package ws.academy;

public class Settings {
    private String inputFilePath;
    private String outputFilePath;
    private boolean isCompress;
    private boolean isExtract;

    public Settings(String[] runArgs) {
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

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }
}
