import java.util.BitSet;

/**
 * Emulates a Linear Feedback Shift Register
 * to generate pseudo-random numbers.
 *
 * @author RakNoel
 * @version 1.0
 * @since 15.10.18
 */
public class LFSR {
    private byte recurrence;
    private byte previous;

    public LFSR(byte recurrence, byte seed) {
        this.recurrence = recurrence;
        this.previous = seed;
    }

    public byte getNextByte() {
        //The java BitSet only stores the positive bits, and can be created from bytes
        byte result = previous;
        byte[] b = {(byte) (recurrence & previous)};
        int nextBit = BitSet.valueOf(b).cardinality() % 2;

        previous >>= 1;
        previous ^= ((nextBit) << 7);

        return result;
    }

    /**
     * To keep it standard i use bits and divide by 8
     *
     * @param length in bits (x | 8)
     * @return bytearray with generated stream
     */
    public byte[] getKeyStream(int length) {
        assert (length % 8 == 0);
        byte[] key = new byte[length / 8];

        for (int i = 0; i < key.length; i++)
            key[i] = this.getNextByte();

        return key;
    }
}
