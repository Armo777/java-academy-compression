package ws.academy;

import java.io.Reader;
import java.io.Writer;

public interface Archiver {

    /**
     * Сжатие текстовых данных.
     */
    void compress(Reader source, Writer destination);

    /**
     * Извлечение текстовых данных
     */
    void extract(Reader source, Writer destination);
}
