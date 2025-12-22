package io.legado.app.model.analyzeRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ7\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00042\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000b¨\u0006\r"},
   d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByRegex;", "", "()V", "getElement", "", "", "res", "regs", "", "index", "", "(Ljava/lang/String;[Ljava/lang/String;I)Ljava/util/List;", "getElements", "reader-pro"}
)
public final class AnalyzeByRegex {
   @NotNull
   public static final AnalyzeByRegex INSTANCE = new AnalyzeByRegex();

   private AnalyzeByRegex() {
   }

   @Nullable
   public final List<String> getElement(@NotNull String res, @NotNull String[] regs, int index) {
      Intrinsics.checkNotNullParameter(res, "res");
      Intrinsics.checkNotNullParameter(regs, "regs");
      Matcher resM = Pattern.compile(regs[index]).matcher((CharSequence)res);
      if (!resM.find()) {
         return null;
      } else {
         List var10000;
         if (index + 1 == regs.length) {
            boolean var7 = false;
            ArrayList info = new ArrayList();
            int var11 = 0;
            int var8 = resM.groupCount();
            int groupIndex;
            if (var11 <= var8) {
               do {
                  groupIndex = var11++;
                  String var10001 = resM.group(groupIndex);
                  Intrinsics.checkNotNull(var10001);
                  info.add(var10001);
               } while(groupIndex != var8);
            }

            var10000 = (List)info;
         } else {
            StringBuilder result = new StringBuilder();

            do {
               result.append(resM.group());
            } while(resM.find());

            String var12 = result.toString();
            Intrinsics.checkNotNullExpressionValue(var12, "result.toString()");
            int vIndex = index + 1;
            var10000 = this.getElement(var12, regs, vIndex);
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   public static List getElement$default(AnalyzeByRegex var0, String var1, String[] var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return var0.getElement(var1, var2, var3);
   }

   @NotNull
   public final List<List<String>> getElements(@NotNull String res, @NotNull String[] regs, int index) {
      Intrinsics.checkNotNullParameter(res, "res");
      Intrinsics.checkNotNullParameter(regs, "regs");
      Matcher resM = Pattern.compile(regs[index]).matcher((CharSequence)res);
      if (!resM.find()) {
         boolean var13 = false;
         return (List)(new ArrayList());
      } else if (index + 1 == regs.length) {
         ArrayList books = new ArrayList();

         do {
            boolean var8 = false;
            ArrayList info = new ArrayList();
            int var15 = 0;
            int var9 = resM.groupCount();
            int groupIndex;
            if (var15 <= var9) {
               do {
                  groupIndex = var15++;
                  String var11 = resM.group(groupIndex);
                  info.add(var11 == null ? "" : var11);
               } while(groupIndex != var9);
            }

            books.add(info);
         } while(resM.find());

         return (List)books;
      } else {
         StringBuilder result = new StringBuilder();

         do {
            result.append(resM.group());
         } while(resM.find());

         String var7 = result.toString();
         Intrinsics.checkNotNullExpressionValue(var7, "result.toString()");
         int vIndex = index + 1;
         return this.getElements(var7, regs, vIndex);
      }
   }

   // $FF: synthetic method
   public static List getElements$default(AnalyzeByRegex var0, String var1, String[] var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return var0.getElements(var1, var2, var3);
   }
}
