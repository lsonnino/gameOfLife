package library.security;

import java.io.Serializable;

/**
 * This is a String encrypted used AES (with the {@link AdvancedEncryptionStandard} tool).
 * It contains the encrypted message and the initialization vector.
 */
public class EncryptedAESMessage implements Serializable {
    private byte[] initializationVector;
    private byte[] message;

    /**
     * Constructor of the {@link EncryptedAESMessage}
     * @param initializationVector the inisialization vector
     * @param message the encrypted message
     */
    public EncryptedAESMessage(byte[] initializationVector, byte[] message){
        this.initializationVector = initializationVector;
        this.message = message;
    }

    /**
     * Get the initialization vector
     * @return the initialization vector
     */
    public byte[] getInitializationVector() {
        return initializationVector;
    }
    /**
     * Get the message
     * @return the encrypted message
     */
    public byte[] getMessage() {
        return message;
    }
}
