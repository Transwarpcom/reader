package io.legado.app.lib.icu4j;

/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/lib/icu4j/CharsetRecog_2022.class */
abstract class CharsetRecog_2022 extends CharsetRecognizer {
    CharsetRecog_2022() {
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int match(byte[] r6, int r7, byte[][] r8) {
        /*
            Method dump skipped, instructions count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.lib.icu4j.CharsetRecog_2022.match(byte[], int, byte[][]):int");
    }

    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/lib/icu4j/CharsetRecog_2022$CharsetRecog_2022JP.class */
    static class CharsetRecog_2022JP extends CharsetRecog_2022 {
        private final byte[][] escapeSequences = {new byte[]{27, 36, 40, 67}, new byte[]{27, 36, 40, 68}, new byte[]{27, 36, 64}, new byte[]{27, 36, 65}, new byte[]{27, 36, 66}, new byte[]{27, 38, 64}, new byte[]{27, 40, 66}, new byte[]{27, 40, 72}, new byte[]{27, 40, 73}, new byte[]{27, 40, 74}, new byte[]{27, 46, 65}, new byte[]{27, 46, 70}};

        /* JADX WARN: Type inference failed for: r1v1, types: [byte[], byte[][]] */
        CharsetRecog_2022JP() {
        }

        @Override // io.legado.app.lib.icu4j.CharsetRecognizer
        String getName() {
            return "ISO-2022-JP";
        }

        @Override // io.legado.app.lib.icu4j.CharsetRecognizer
        CharsetMatch match(CharsetDetector det) {
            int confidence = match(det.fInputBytes, det.fInputLen, this.escapeSequences);
            if (confidence == 0) {
                return null;
            }
            return new CharsetMatch(det, this, confidence);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/lib/icu4j/CharsetRecog_2022$CharsetRecog_2022KR.class */
    static class CharsetRecog_2022KR extends CharsetRecog_2022 {
        private final byte[][] escapeSequences = {new byte[]{27, 36, 41, 67}};

        /* JADX WARN: Type inference failed for: r1v1, types: [byte[], byte[][]] */
        CharsetRecog_2022KR() {
        }

        @Override // io.legado.app.lib.icu4j.CharsetRecognizer
        String getName() {
            return "ISO-2022-KR";
        }

        @Override // io.legado.app.lib.icu4j.CharsetRecognizer
        CharsetMatch match(CharsetDetector det) {
            int confidence = match(det.fInputBytes, det.fInputLen, this.escapeSequences);
            if (confidence == 0) {
                return null;
            }
            return new CharsetMatch(det, this, confidence);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/lib/icu4j/CharsetRecog_2022$CharsetRecog_2022CN.class */
    static class CharsetRecog_2022CN extends CharsetRecog_2022 {
        private final byte[][] escapeSequences = {new byte[]{27, 36, 41, 65}, new byte[]{27, 36, 41, 71}, new byte[]{27, 36, 42, 72}, new byte[]{27, 36, 41, 69}, new byte[]{27, 36, 43, 73}, new byte[]{27, 36, 43, 74}, new byte[]{27, 36, 43, 75}, new byte[]{27, 36, 43, 76}, new byte[]{27, 36, 43, 77}, new byte[]{27, 78}, new byte[]{27, 79}};

        /* JADX WARN: Type inference failed for: r1v1, types: [byte[], byte[][]] */
        CharsetRecog_2022CN() {
        }

        @Override // io.legado.app.lib.icu4j.CharsetRecognizer
        String getName() {
            return "ISO-2022-CN";
        }

        @Override // io.legado.app.lib.icu4j.CharsetRecognizer
        CharsetMatch match(CharsetDetector det) {
            int confidence = match(det.fInputBytes, det.fInputLen, this.escapeSequences);
            if (confidence == 0) {
                return null;
            }
            return new CharsetMatch(det, this, confidence);
        }
    }
}
