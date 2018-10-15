import java.math.BigInteger;

/**
 * A class used to encrypt or decrypt
 * asymmetrically using the RSA encryption
 * algorithm
 *
 * @author RakNoel
 * @version 1.0
 * @since 15.10.18
 */
public class RSAHandler {

    public BigInteger encode(BigInteger data, BigInteger keyE, BigInteger keyN) {
        return data.modPow(keyE, keyN);
    }

    public BigInteger decode(BigInteger cipher, BigInteger keyD, BigInteger keyN) {
        return cipher.modPow(keyD, keyN);
    }
}
