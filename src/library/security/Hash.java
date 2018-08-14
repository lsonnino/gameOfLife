package library.security;

import library.lang.Converter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class can hash text from various objects
 */
public class Hash {
    /**
     * Hash a {@link String} text
     * @param text the {@link String} to hash
     * @return the hashed {@link String}
     * @throws SecurityException thrown if there was a problem hashing the text
     */
    public static String hash(String text) throws SecurityException {
        return Converter.byteToString(hash(Converter.stringToByte(text)));
    }
    /**
     * Hash a byte array
     * @param text the byte array to hash
     * @return the hashed byte array
     * @throws SecurityException thrown if there was a problem hashing the byte array
     */
    public static byte[] hash(byte[] text) throws SecurityException {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(text);
        }
        catch(NoSuchAlgorithmException exc){
            throw new SecurityException(exc);
        }
    }
}
