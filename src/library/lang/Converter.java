package library.lang;

import java.nio.charset.StandardCharsets;

/**
 * Convert objects and arrays
 */
public class Converter {
    /**
     * Convert a {@link String} to a byte array
     * @param string the {@link String} to convert
     * @return the corresponding byte array
     */
    public static byte[] stringToByte(String string){
        return string.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Convert a byte array to a {@link String}
     * @param array the byte array
     * @return the corresponding {@link String}
     */
    public static String byteToString(byte[] array){
        return new String(array);
    }
}
