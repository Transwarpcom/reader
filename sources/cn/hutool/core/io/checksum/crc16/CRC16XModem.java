package cn.hutool.core.io.checksum.crc16;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/checksum/crc16/CRC16XModem.class */
public class CRC16XModem extends CRC16Checksum {
    private static final long serialVersionUID = 1;
    private static final int WC_POLY = 4129;

    @Override // cn.hutool.core.io.checksum.crc16.CRC16Checksum, java.util.zip.Checksum
    public void update(byte[] b, int off, int len) {
        super.update(b, off, len);
        this.wCRCin &= 65535;
    }

    @Override // java.util.zip.Checksum
    public void update(int b) {
        for (int i = 0; i < 8; i++) {
            boolean bit = ((b >> (7 - i)) & 1) == 1;
            boolean c15 = ((this.wCRCin >> 15) & 1) == 1;
            this.wCRCin <<= 1;
            if (c15 ^ bit) {
                this.wCRCin ^= WC_POLY;
            }
        }
    }
}
