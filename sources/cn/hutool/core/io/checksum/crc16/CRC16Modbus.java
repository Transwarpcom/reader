package cn.hutool.core.io.checksum.crc16;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/checksum/crc16/CRC16Modbus.class */
public class CRC16Modbus extends CRC16Checksum {
    private static final long serialVersionUID = 1;
    private static final int WC_POLY = 40961;

    @Override // cn.hutool.core.io.checksum.crc16.CRC16Checksum, java.util.zip.Checksum
    public void reset() {
        this.wCRCin = 65535;
    }

    @Override // java.util.zip.Checksum
    public void update(int b) {
        this.wCRCin ^= b & 255;
        for (int j = 0; j < 8; j++) {
            if ((this.wCRCin & 1) != 0) {
                this.wCRCin >>= 1;
                this.wCRCin ^= WC_POLY;
            } else {
                this.wCRCin >>= 1;
            }
        }
    }
}
