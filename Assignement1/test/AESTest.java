import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * TODO: Describe test
 *
 * @author RakNoel
 * @version 1.0
 * @since 15.10.18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AESTest {

    @Test
    public void AESTest_MixColumns() {
        AESHandler aes = new AESHandler();

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        byte[] res = aes.mixColumns(key);
        byte[] dec = aes.inv_mixColumns(res);

        for (int i = 0; i < dec.length; i++){
            assertEquals(dec[i], key[i]);
        }

    }

    @Test
    public void AESTest_ShiftRows() {
        AESHandler aes = new AESHandler();

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        byte[] res = aes.shiftRows(key);
        byte[] dec = aes.inv_shiftRows(res);

        for (int i = 0; i < dec.length; i++){
            assertEquals(dec[i], key[i]);
        }
    }

    @Test
    public void AESTest_SubBytes() {
        AESHandler aes = new AESHandler();

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        byte[] res = aes.subBytes(key);
        byte[] dec = aes.inv_subBytes(res);

        for (int i = 0; i < dec.length; i++){
            assertEquals(dec[i], key[i]);
        }
    }

    @Test
    public void AESTest_AddKey() {
        AESHandler aes = new AESHandler();

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        byte[] msg = "helloworld123456".getBytes();

        byte[] res = aes.addkey(msg, key);
        byte[] dec = aes.addkey(res, key);

        for (int i = 0; i < dec.length; i++){
            assertEquals(dec[i], msg[i]);
        }
    }

    @Test
    public void AESTest_ShiftAndSub() {
        AESHandler aes = new AESHandler();

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        byte[] res = aes.subBytes(key);
        res = aes.shiftRows(res);
        byte[] dec = aes.inv_shiftRows(res);
        dec = aes.inv_subBytes(dec);

        for (int i = 0; i < dec.length; i++){
            assertEquals(Arrays.toString(dec), Arrays.toString(key));
        }
    }

    @Test
    public void AESTest_EncodeDecodeBlock() {
        AESHandler aes = new AESHandler();

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        byte[] msg = "helloworld123456".getBytes();

        byte[][] keyList = aes.expandKeys(key);

        byte[] res = aes.encodeBlock(msg, keyList);
        byte[] dec = aes.decodeBlock(res, keyList);

        for (int i = 0; i < dec.length; i++){
            assertEquals(dec[i], msg[i]);
        }
    }
}