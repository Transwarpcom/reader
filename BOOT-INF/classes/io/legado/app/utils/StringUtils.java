package io.legado.app.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Result.Companion;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\nJ\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nJ\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nJ\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nJ\u000e\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nJ\u000e\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\nJ\u0012\u0010%\u001a\u0004\u0018\u00010\n2\b\u0010&\u001a\u0004\u0018\u00010\nJ\u0016\u0010'\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u0006J\u0010\u0010)\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010\nJ\u000e\u0010*\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nJ\u000e\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u0016J\u000e\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\nJ\u0010\u0010/\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010\nR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R \u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u00061"},
   d2 = {"Lio/legado/app/utils/StringUtils;", "", "()V", "ChnMap", "Ljava/util/HashMap;", "", "", "DAY_OF_YESTERDAY", "HOUR_OF_DAY", "TAG", "", "TIME_UNIT", "chnMap", "getChnMap", "()Ljava/util/HashMap;", "byteToHexString", "bytes", "", "chineseNumToInt", "chNum", "dateConvert", "time", "", "pattern", "source", "formatHtml", "html", "fullToHalf", "input", "halfToFull", "hexStringToByte", "hexString", "isContainNumber", "", "company", "isNumeric", "str", "removeUTFCharacters", "data", "repeat", "n", "stringToInt", "toFirstCapital", "toSize", "length", "trim", "s", "wordCountFormat", "wc", "reader-pro"}
)
public final class StringUtils {
   @NotNull
   public static final StringUtils INSTANCE = new StringUtils();
   @NotNull
   private static final String TAG = "StringUtils";
   private static final int HOUR_OF_DAY = 24;
   private static final int DAY_OF_YESTERDAY = 2;
   private static final int TIME_UNIT = 60;
   @NotNull
   private static final HashMap<Character, Integer> ChnMap;

   private StringUtils() {
   }

   private final HashMap<Character, Integer> getChnMap() {
      HashMap map = new HashMap();
      String cnStr = "零一二三四五六七八九十";
      boolean var5 = false;
      char[] var10000 = cnStr.toCharArray();
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toCharArray()");
      char[] c = var10000;
      int var4 = 0;

      Map var6;
      Character var7;
      Integer var8;
      boolean var9;
      int i;
      do {
         i = var4++;
         var6 = (Map)map;
         var7 = c[i];
         var8 = i;
         var9 = false;
         var6.put(var7, var8);
      } while(var4 <= 10);

      cnStr = "〇壹贰叁肆伍陆柒捌玖拾";
      var5 = false;
      var10000 = cnStr.toCharArray();
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toCharArray()");
      c = var10000;
      var4 = 0;

      do {
         i = var4++;
         var6 = (Map)map;
         var7 = c[i];
         var8 = i;
         var9 = false;
         var6.put(var7, var8);
      } while(var4 <= 10);

      Map var11 = (Map)map;
      Character var12 = '两';
      Integer var13 = 2;
      boolean var14 = false;
      var11.put(var12, var13);
      var11 = (Map)map;
      var12 = '百';
      var13 = 100;
      var14 = false;
      var11.put(var12, var13);
      var11 = (Map)map;
      var12 = '佰';
      var13 = 100;
      var14 = false;
      var11.put(var12, var13);
      var11 = (Map)map;
      var12 = '千';
      var13 = 1000;
      var14 = false;
      var11.put(var12, var13);
      var11 = (Map)map;
      var12 = '仟';
      var13 = 1000;
      var14 = false;
      var11.put(var12, var13);
      var11 = (Map)map;
      var12 = '万';
      var13 = 10000;
      var14 = false;
      var11.put(var12, var13);
      var11 = (Map)map;
      var12 = '亿';
      var13 = 100000000;
      var14 = false;
      var11.put(var12, var13);
      return map;
   }

   @NotNull
   public final String dateConvert(long time, @NotNull String pattern) {
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Date date = new Date(time);
      SimpleDateFormat format = new SimpleDateFormat(pattern);
      String var6 = format.format(date);
      Intrinsics.checkNotNullExpressionValue(var6, "format.format(date)");
      return var6;
   }

   @NotNull
   public final String dateConvert(@NotNull String source, @NotNull String pattern) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      SimpleDateFormat format = new SimpleDateFormat(pattern);
      Calendar calendar = Calendar.getInstance();

      try {
         Date date = format.parse(source);
         long curTime = calendar.getTimeInMillis();
         calendar.setTime(date);
         long difSec = Math.abs((curTime - date.getTime()) / (long)1000);
         long difMin = difSec / (long)60;
         long difHour = difMin / (long)60;
         long difDate = difHour / (long)60;
         int oldHour = calendar.get(10);
         if (oldHour == 0) {
            if (difDate == 0L) {
               return "今天";
            } else if (difDate < 2L) {
               return "昨天";
            } else {
               SimpleDateFormat convertFormat = new SimpleDateFormat("yyyy-MM-dd");
               String var21 = convertFormat.format(date);
               Intrinsics.checkNotNullExpressionValue(var21, "convertFormat.format(date)");
               return var21;
            }
         } else {
            String var10000;
            if (difSec < 60L) {
               var10000 = difSec + "秒前";
            } else if (difMin < 60L) {
               var10000 = difMin + "分钟前";
            } else if (difHour < 24L) {
               var10000 = difHour + "小时前";
            } else if (difDate < 2L) {
               var10000 = "昨天";
            } else {
               SimpleDateFormat convertFormat = new SimpleDateFormat("yyyy-MM-dd");
               String var17 = convertFormat.format(date);
               Intrinsics.checkNotNullExpressionValue(var17, "{\n                    val convertFormat = SimpleDateFormat(\"yyyy-MM-dd\")\n                    convertFormat.format(date)\n                }");
               var10000 = var17;
            }

            return var10000;
         }
      } catch (ParseException var19) {
         var19.printStackTrace();
         return "";
      }
   }

   @NotNull
   public final String toSize(long length) {
      if (length <= 0L) {
         return "0";
      } else {
         String[] var4 = new String[]{"b", "kb", "M", "G", "T"};
         String[] units = var4;
         double var5 = (double)length;
         boolean var7 = false;
         double var10000 = Math.log10(var5);
         var5 = 1024.0D;
         var7 = false;
         int digitGroups = (int)(var10000 / Math.log10(var5));
         StringBuilder var11 = new StringBuilder();
         DecimalFormat var10001 = new DecimalFormat("#,##0.##");
         double var10002 = (double)length;
         var5 = 1024.0D;
         double var12 = (double)digitGroups;
         boolean var9 = false;
         return var11.append(var10001.format(var10002 / Math.pow(var5, var12))).append(' ').append(units[digitGroups]).toString();
      }
   }

   @NotNull
   public final String toFirstCapital(@NotNull String str) {
      Intrinsics.checkNotNullParameter(str, "str");
      byte var3 = 0;
      byte var4 = 1;
      boolean var5 = false;
      String var10000 = str.substring(var3, var4);
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
      String var2 = var10000;
      Locale var6 = Locale.getDefault();
      Intrinsics.checkNotNullExpressionValue(var6, "getDefault()");
      boolean var7 = false;
      if (var2 == null) {
         throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
      } else {
         var10000 = var2.toUpperCase(var6);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toUpperCase(locale)");
         var3 = 1;
         var7 = false;
         String var10001 = str.substring(var3);
         Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
         return Intrinsics.stringPlus(var10000, var10001);
      }
   }

   @NotNull
   public final String halfToFull(@NotNull String input) {
      Intrinsics.checkNotNullParameter(input, "input");
      boolean var4 = false;
      char[] var10000 = input.toCharArray();
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toCharArray()");
      char[] c = var10000;
      int var3 = 0;
      int var10 = c.length + -1;
      if (var3 <= var10) {
         do {
            int i = var3++;
            char var6 = c[i];
            boolean var7 = false;
            if (var6 == ' ') {
               c[i] = 12288;
            } else {
               char var11 = c[i];
               boolean var8 = false;
               if ('!' <= var11 ? var11 <= '~' : false) {
                  var6 = c[i];
                  var7 = false;
                  c[i] = (char)(var6 + 'ﻠ');
               }
            }
         } while(var3 <= var10);
      }

      boolean var9 = false;
      return new String(c);
   }

   @NotNull
   public final String fullToHalf(@NotNull String input) {
      Intrinsics.checkNotNullParameter(input, "input");
      boolean var4 = false;
      char[] var10000 = input.toCharArray();
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toCharArray()");
      char[] c = var10000;
      int var3 = 0;
      int var10 = c.length + -1;
      if (var3 <= var10) {
         do {
            int i = var3++;
            char var6 = c[i];
            boolean var7 = false;
            if (var6 == 12288) {
               c[i] = ' ';
            } else {
               char var11 = c[i];
               boolean var8 = false;
               if ('！' <= var11 ? var11 <= '～' : false) {
                  var6 = c[i];
                  var7 = false;
                  c[i] = (char)(var6 - 'ﻠ');
               }
            }
         } while(var3 <= var10);
      }

      boolean var9 = false;
      return new String(c);
   }

   public final int chineseNumToInt(@NotNull String chNum) {
      Intrinsics.checkNotNullParameter(chNum, "chNum");
      int result = 0;
      int tmp = 0;
      int billion = 0;
      boolean var7 = false;
      char[] var10000 = chNum.toCharArray();
      Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).toCharArray()");
      char[] cn = var10000;
      boolean var8;
      boolean var15;
      if (cn.length > 1) {
         CharSequence var6 = (CharSequence)chNum;
         String var18 = "^[〇零一二三四五六七八九壹贰叁肆伍陆柒捌玖]$";
         var8 = false;
         Regex var19 = new Regex(var18);
         var8 = false;
         if (var19.matches(var6)) {
            int var17 = 0;
            int var25 = cn.length + -1;
            if (var17 <= var25) {
               do {
                  int i = var17++;
                  Object var10003 = ChnMap.get(cn[i]);
                  Intrinsics.checkNotNull(var10003);
                  Object var29 = var10003;
                  Intrinsics.checkNotNullExpressionValue(var29, "ChnMap[cn[i]]!!");
                  cn[i] = (char)(48 + ((Number)var29).intValue());
               } while(var17 <= var25);
            }

            var15 = false;
            return Integer.parseInt(new String(cn));
         }
      }

      var15 = false;

      Object var20;
      try {
         Companion var21 = Result.Companion;
         var8 = false;
         int var23 = 0;
         int var28 = cn.length + -1;
         if (var23 <= var28) {
            do {
               int i = var23++;
               Object var30 = ChnMap.get(cn[i]);
               Intrinsics.checkNotNull(var30);
               Object var12 = var30;
               Intrinsics.checkNotNullExpressionValue(var12, "ChnMap[cn[i]]!!");
               int tmpNum = ((Number)var12).intValue();
               if (tmpNum == 100000000) {
                  result += tmp;
                  result *= tmpNum;
                  billion = billion * 100000000 + result;
                  result = 0;
                  tmp = 0;
               } else if (tmpNum == 10000) {
                  result += tmp;
                  result *= tmpNum;
                  tmp = 0;
               } else if (tmpNum >= 10) {
                  if (tmp == 0) {
                     tmp = 1;
                  }

                  result += tmpNum * tmp;
                  tmp = 0;
               } else {
                  int var31;
                  label55: {
                     if (i >= 2 && i == cn.length - 1) {
                        var30 = ChnMap.get(cn[i - 1]);
                        Intrinsics.checkNotNull(var30);
                        var12 = var30;
                        Intrinsics.checkNotNullExpressionValue(var12, "ChnMap[cn[i - 1]]!!");
                        if (((Number)var12).intValue() > 10) {
                           Object var10001 = ChnMap.get(cn[i - 1]);
                           Intrinsics.checkNotNull(var10001);
                           var12 = var10001;
                           Intrinsics.checkNotNullExpressionValue(var12, "ChnMap[cn[i - 1]]!!");
                           var31 = tmpNum * ((Number)var12).intValue() / 10;
                           break label55;
                        }
                     }

                     var31 = tmp * 10 + tmpNum;
                  }

                  tmp = var31;
               }
            } while(var23 <= var28);
         }

         result += tmp + billion;
         Integer var22 = result;
         boolean var27 = false;
         var20 = Result.constructor-impl(var22);
      } catch (Throwable var14) {
         Companion var9 = Result.Companion;
         boolean var10 = false;
         var20 = Result.constructor-impl(ResultKt.createFailure(var14));
      }

      Integer var24 = -1;
      var8 = false;
      return ((Number)(Result.isFailure-impl(var20) ? var24 : var20)).intValue();
   }

   public final int stringToInt(@Nullable String str) {
      if (str != null) {
         CharSequence var3 = (CharSequence)this.fullToHalf(str);
         String var4 = "\\s+";
         boolean var5 = false;
         Regex var11 = new Regex(var4);
         String var14 = "";
         boolean var6 = false;
         String num = var11.replace(var3, var14);
         boolean var9 = false;

         boolean var7;
         Object var12;
         try {
            Companion var13 = Result.Companion;
            var5 = false;
            Integer var15 = Integer.parseInt(num);
            var6 = false;
            var12 = Result.constructor-impl(var15);
         } catch (Throwable var8) {
            Companion var17 = Result.Companion;
            var7 = false;
            var12 = Result.constructor-impl(ResultKt.createFailure(var8));
         }

         Object var10 = var12;
         boolean var16 = false;
         var5 = false;
         Throwable var18 = Result.exceptionOrNull-impl(var10);
         Object var10000;
         if (var18 == null) {
            var10000 = var10;
         } else {
            var7 = false;
            var10000 = INSTANCE.chineseNumToInt(num);
         }

         return ((Number)var10000).intValue();
      } else {
         return -1;
      }
   }

   public final boolean isContainNumber(@NotNull String company) {
      Intrinsics.checkNotNullParameter(company, "company");
      Pattern p = Pattern.compile("[0-9]+");
      Matcher m = p.matcher((CharSequence)company);
      return m.find();
   }

   public final boolean isNumeric(@NotNull String str) {
      Intrinsics.checkNotNullParameter(str, "str");
      Pattern pattern = Pattern.compile("-?[0-9]+");
      Matcher isNum = pattern.matcher((CharSequence)str);
      return isNum.matches();
   }

   @NotNull
   public final String wordCountFormat(@Nullable String wc) {
      if (wc == null) {
         return "";
      } else {
         String wordsS = "";
         if (this.isNumeric(wc)) {
            boolean var5 = false;
            int words = Integer.parseInt(wc);
            if (words > 0) {
               wordsS = "" + words + '字';
               if (words > 10000) {
                  DecimalFormat df = new DecimalFormat("#.#");
                  wordsS = Intrinsics.stringPlus(df.format((double)((float)words * 1.0F) / 10000.0D), "万字");
               }
            }
         } else {
            wordsS = wc;
         }

         return wordsS;
      }
   }

   @NotNull
   public final String trim(@NotNull String s) {
      Intrinsics.checkNotNullParameter(s, "s");
      CharSequence var2 = (CharSequence)s;
      boolean var3 = false;
      if (var2.length() == 0) {
         return "";
      } else {
         int start = 0;
         int len = s.length();

         int end;
         char var5;
         boolean var6;
         for(end = len - 1; start < end; ++start) {
            var5 = s.charAt(start);
            var6 = false;
            if (var5 > ' ' && s.charAt(start) != 12288) {
               break;
            }
         }

         while(start < end) {
            var5 = s.charAt(end);
            var6 = false;
            if (var5 > ' ' && s.charAt(end) != 12288) {
               break;
            }

            --end;
         }

         if (end < len) {
            ++end;
         }

         String var10000;
         if (start <= 0 && end >= len) {
            var10000 = s;
         } else {
            var6 = false;
            var10000 = s.substring(start, end);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
         }

         return var10000;
      }
   }

   @NotNull
   public final String repeat(@NotNull String str, int n) {
      Intrinsics.checkNotNullParameter(str, "str");
      StringBuilder stringBuilder = new StringBuilder();
      int var4 = 0;
      if (var4 < n) {
         do {
            ++var4;
            stringBuilder.append(str);
         } while(var4 < n);
      }

      String var6 = stringBuilder.toString();
      Intrinsics.checkNotNullExpressionValue(var6, "stringBuilder.toString()");
      return var6;
   }

   @Nullable
   public final String removeUTFCharacters(@Nullable String data) {
      if (data == null) {
         return null;
      } else {
         Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
         Matcher m = p.matcher((CharSequence)data);
         StringBuffer buf = new StringBuffer(data.length());

         while(m.find()) {
            String var10000 = m.group(1);
            Intrinsics.checkNotNull(var10000);
            String ch = String.valueOf((char)Integer.parseInt(var10000, 16));
            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
         }

         m.appendTail(buf);
         return buf.toString();
      }
   }

   @NotNull
   public final String formatHtml(@NotNull String html) {
      Intrinsics.checkNotNullParameter(html, "html");
      String var10000;
      if (TextUtils.isEmpty((CharSequence)html)) {
         var10000 = "";
      } else {
         CharSequence var2 = (CharSequence)html;
         String var3 = "(?i)<(br[\\s/]*|/*p.*?|/*div.*?)>";
         boolean var4 = false;
         Regex var6 = new Regex(var3);
         String var7 = "\n";
         boolean var5 = false;
         var2 = (CharSequence)var6.replace(var2, var7);
         var3 = "<[script>]*.*?>|&nbsp;";
         var4 = false;
         var6 = new Regex(var3);
         var7 = "";
         var5 = false;
         var2 = (CharSequence)var6.replace(var2, var7);
         var3 = "\\s*\\n+\\s*";
         var4 = false;
         var6 = new Regex(var3);
         var7 = "\n　　";
         var5 = false;
         var2 = (CharSequence)var6.replace(var2, var7);
         var3 = "^[\\n\\s]+";
         var4 = false;
         var6 = new Regex(var3);
         var7 = "　　";
         var5 = false;
         var2 = (CharSequence)var6.replace(var2, var7);
         var3 = "[\\n\\s]+$";
         var4 = false;
         var6 = new Regex(var3);
         var7 = "";
         var5 = false;
         var10000 = var6.replace(var2, var7);
      }

      return var10000;
   }

   @NotNull
   public final String byteToHexString(@Nullable byte[] bytes) {
      if (bytes == null) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder(bytes.length * 2);
         byte[] var3 = bytes;
         int var4 = 0;

         int hex;
         for(int var5 = bytes.length; var4 < var5; sb.append(Integer.toHexString(hex))) {
            byte b = var3[var4];
            ++var4;
            hex = 255 & b;
            if (hex < 16) {
               sb.append('0');
            }
         }

         String var8 = sb.toString();
         Intrinsics.checkNotNullExpressionValue(var8, "sb.toString()");
         return var8;
      }
   }

   @NotNull
   public final byte[] hexStringToByte(@NotNull String hexString) {
      Intrinsics.checkNotNullParameter(hexString, "hexString");
      String hexStr = StringsKt.replace$default(hexString, " ", "", false, 4, (Object)null);
      int len = hexStr.length();
      byte[] bytes = new byte[len / 2];

      for(int i = 0; i < len; i += 2) {
         bytes[i / 2] = (byte)((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
      }

      return bytes;
   }

   static {
      ChnMap = INSTANCE.getChnMap();
   }
}
