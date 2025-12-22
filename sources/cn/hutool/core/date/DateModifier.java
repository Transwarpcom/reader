package cn.hutool.core.date;

import java.util.Calendar;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/DateModifier.class */
public class DateModifier {
    private static final int[] IGNORE_FIELDS = {11, 9, 8, 6, 4, 3};

    /* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/DateModifier$ModifyType.class */
    public enum ModifyType {
        TRUNCATE,
        ROUND,
        CEILING
    }

    public static Calendar modify(Calendar calendar, int dateField, ModifyType modifyType) {
        return modify(calendar, dateField, modifyType, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Calendar modify(java.util.Calendar r5, int r6, cn.hutool.core.date.DateModifier.ModifyType r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.hutool.core.date.DateModifier.modify(java.util.Calendar, int, cn.hutool.core.date.DateModifier$ModifyType, boolean):java.util.Calendar");
    }

    private static void modifyField(Calendar calendar, int field, ModifyType modifyType) {
        int href;
        if (10 == field) {
            field = 11;
        }
        switch (modifyType) {
            case TRUNCATE:
                calendar.set(field, DateUtil.getBeginValue(calendar, field));
                break;
            case CEILING:
                calendar.set(field, DateUtil.getEndValue(calendar, field));
                break;
            case ROUND:
                int min = DateUtil.getBeginValue(calendar, field);
                int max = DateUtil.getEndValue(calendar, field);
                if (7 == field) {
                    href = (min + 3) % 7;
                } else {
                    href = ((max - min) / 2) + 1;
                }
                int value = calendar.get(field);
                calendar.set(field, value < href ? min : max);
                break;
        }
    }
}
