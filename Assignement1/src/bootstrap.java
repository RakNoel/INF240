import java.math.BigInteger;

/**
 * Main Bootstrapper that will simulate the scenario
 *
 * @author RakNoel
 * @version 1.0
 * @since 15.10.18
 */
public class bootstrap {
    public static void main(String[] args) {
        //CipherHandelers
        RSAHandler rsa = new RSAHandler();
        AESHandler aes = new AESHandler();

        //Alice knows
        String secretText = "securityisnoeasy";
        byte recurrence = 0x1B;
        byte seed = (byte) 0xF0;
        LFSR AliceLFSR = new LFSR(recurrence, seed);

        byte[] secretAESKey = AliceLFSR.getKeyStream(128);
        System.out.println(new BigInteger(secretAESKey).toString(16));

        BigInteger bobN = new BigInteger("00:af:09:83:ad:69:61:1f:8e:5d:a1:20:6f:ce:63:8f:7b:b7:f0:3e:5a:f5:36:67:88:d7:11:26:a9:45:e9:f8:c7".replace(":", ""), 16);
        BigInteger bobPublicKey = new BigInteger("2").pow(16).add(new BigInteger("1"));

        String c1 = rsa.encode(new BigInteger("F0", 16), bobPublicKey, bobN).toString(16);

        System.out.println(c1);

        byte[] c2 = aes.encode(secretText.getBytes(), secretAESKey);
        for (byte letter : c2)
            System.out.print((char) letter);
        System.out.println();


        //Bob knows
        BigInteger bobPrivateKey = new BigInteger("58:7c:9b:d7:cf:bd:2c:c1:c0:ed:92:c3:52:f8:1b:f1:5e:68:be:b0:b3:7c:cd:b0:4e:37:b4:3f:71:11:5a:31".replace(":", ""), 16);
        Byte resC1 = rsa.decode(new BigInteger(c1, 16), bobPrivateKey, bobN).byteValue();
        System.out.println(resC1);
        LFSR BobLFSR = new LFSR(recurrence, resC1);

        byte[] assumedSecretAESKey = BobLFSR.getKeyStream(128);
        byte[] recievedSecretText = aes.decode(c2, assumedSecretAESKey);

        for (byte letter : recievedSecretText)
            System.out.print((char) letter);
        System.out.println();
    }
}
