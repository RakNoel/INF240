import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * Supposed only to test the LSFR
 * <p>
 * not really a test
 *
 * @author RakNoel
 * @version 1.0
 * @since 15.10.18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LFSRTest {

    @Test
    public void LFSRTest_Just_Test() {
        byte recurrence = 0x1B;
        byte seed = (byte) 0xF0;
        LFSR myLFSR = new LFSR(recurrence, seed);

        for (int i = 0; i < 128; i++) {
            System.out.println(myLFSR.getNextByte());
        }
    }

    @Test
    public void LFSRTest_First_equals_seed() {
        byte recurrence = 0x1B;
        byte seed = (byte) 0xF0;
        LFSR myLFSR = new LFSR(recurrence, seed);

        assertEquals(myLFSR.getNextByte(), seed);
    }
}