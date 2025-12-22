package cn.hutool.core.io.checksum.crc16;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/checksum/crc16/CRC16Ansi.class */
public class CRC16Ansi extends CRC16Checksum {
    private static final long serialVersionUID = 1;
    private static final int WC_POLY = 40961;

    @Override // cn.hutool.core.io.checksum.crc16.CRC16Checksum, java.util.zip.Checksum
    public void reset() {
        this.wCRCin = 65535;
    }

    @Override // java.util.zip.Checksum
    public void update(int b) {
        int hi = this.wCRCin >> 8;
        this.wCRCin = hi ^ b;
        for (int i = 0; i < 8; i++) {
            int flag = this.wCRCin & 1;
            this.wCRCin >>= 1;
            if (flag == 1) {
                this.wCRCin ^= WC_POLY;
            }
        }
    }
}
