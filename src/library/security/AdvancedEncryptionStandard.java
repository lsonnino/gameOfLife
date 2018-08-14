package library.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Encrypt and decypt Advanced Encryption Standard (AES)
 */
public class AdvancedEncryptionStandard implements Serializable
{
    private transient static final String ALGORITHM = "AES";
    private byte[] salt;

    /**
     * Constructor of the {@link AdvancedEncryptionStandard tool}
     */
    public AdvancedEncryptionStandard(){
        SecureRandom secureRandom = new SecureRandom();
        salt = secureRandom.generateSeed(8);
    }

    /**
     * Generate a secret key
     * @param password the password used to encrypt the message
     * @return the corresponding {@link SecretKey}
     * @throws NoSuchAlgorithmException thrown if the used algorithm is not found
     * @throws InvalidKeySpecException thrown if the key specification is not valid
     */
    private SecretKey generateSecretKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), ALGORITHM);
    }

    /**
     * Encrypt the given plain text
     * @param plainText the plain text
     * @param key the password
     * @return the {@link EncryptedAESMessage} which can be serialized and decrypted using the key
     * @throws SecurityException thrown if there was a problem encrypting the message
     */
    public EncryptedAESMessage encrypt(String plainText, String key) throws SecurityException {
        try {
            SecretKey secret = generateSecretKey(key);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();
            byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] ciphertext = cipher.doFinal(plainText.getBytes("UTF-8"));

            return new EncryptedAESMessage(iv, ciphertext);
        }
        catch(Exception exc){
            throw new SecurityException(exc);
        }
    }

    /**
     * Decrypt the given {@link EncryptedAESMessage}
     * @param message the encrypted text {@link EncryptedAESMessage}
     * @param key the password
     * @return plain text
     * @throws SecurityException thrown if there was a problem decrypting the message
     */
    public String decrypt(EncryptedAESMessage message, String key) throws SecurityException {
        try {
            SecretKey secret = generateSecretKey(key);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(message.getInitializationVector()));
            String plaintext = new String(cipher.doFinal(message.getMessage()), "UTF-8");

            return plaintext;
        }
        catch(Exception exc){
            throw new SecurityException(exc);
        }
    }
}