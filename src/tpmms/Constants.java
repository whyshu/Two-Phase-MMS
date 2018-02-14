package tpmms;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Constants {
    public static final int BLOCKSIZE=4096;
    //Get available ram
    static long maxMemory =5242880 ;
    public static final int RAMSIZE=(int)maxMemory;
    public static final int CHUNKSIZE=RAMSIZE/BLOCKSIZE;
    public static final int TUPLE_CNT_PER_BLOCK=40;
}
