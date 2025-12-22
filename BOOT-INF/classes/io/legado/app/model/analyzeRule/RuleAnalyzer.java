package io.legado.app.model.analyzeRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 <2\u00020\u0001:\u0001<B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\tJ\u0016\u0010%\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\tJ\u000e\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u0003J\u001f\u0010(\u001a\u00020\u00052\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030)\"\u00020\u0003¢\u0006\u0002\u0010*J\u0014\u0010+\u001a\u00020\u00172\n\u0010'\u001a\u00020,\"\u00020\tH\u0002J8\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00172\b\b\u0002\u00100\u001a\u00020\u00172\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000302J,\u0010-\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u000302J\u0006\u00105\u001a\u000206J\u001e\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u001aj\b\u0012\u0004\u0012\u00020\u0003`\u001bH\u0083\u0010¢\u0006\u0002\b8J2\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u001aj\b\u0012\u0004\u0012\u00020\u0003`\u001b2\u0012\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030)\"\u00020\u0003H\u0086\u0010¢\u0006\u0002\u0010:J\u0006\u0010;\u001a\u000206R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u001aj\b\u0012\u0004\u0012\u00020\u0003`\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u001aj\b\u0012\u0004\u0012\u00020\u0003`\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="},
   d2 = {"Lio/legado/app/model/analyzeRule/RuleAnalyzer;", "", "data", "", "code", "", "(Ljava/lang/String;Z)V", "chompBalanced", "Lkotlin/reflect/KFunction2;", "", "getChompBalanced", "()Lkotlin/reflect/KFunction;", "elementsType", "getElementsType", "()Ljava/lang/String;", "setElementsType", "(Ljava/lang/String;)V", "innerType", "getInnerType", "()Z", "setInnerType", "(Z)V", "pos", "", "queue", "rule", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "ruleTypeList", "getRuleTypeList", "()Ljava/util/ArrayList;", "start", "startX", "step", "chompCodeBalanced", "open", "close", "chompRuleBalanced", "consumeTo", "seq", "consumeToAny", "", "([Ljava/lang/String;)Z", "findToAny", "", "innerRule", "inner", "startStep", "endStep", "fr", "Lkotlin/Function1;", "startStr", "endStr", "reSetPos", "", "splitRule", "splitRuleNext", "split", "([Ljava/lang/String;)Ljava/util/ArrayList;", "trim", "Companion", "reader-pro"}
)
public final class RuleAnalyzer {
   @NotNull
   public static final RuleAnalyzer.Companion Companion = new RuleAnalyzer.Companion((DefaultConstructorMarker)null);
   @NotNull
   private String queue;
   private int pos;
   private int start;
   private int startX;
   @NotNull
   private ArrayList<String> rule;
   private int step;
   @NotNull
   private String elementsType;
   private boolean innerType;
   @NotNull
   private final ArrayList<String> ruleTypeList;
   @NotNull
   private final KFunction<Boolean> chompBalanced;
   private static final char ESC = '\\';

   public RuleAnalyzer(@NotNull String data, boolean code) {
      Intrinsics.checkNotNullParameter(data, "data");
      super();
      this.queue = data;
      this.rule = new ArrayList();
      this.elementsType = "";
      this.innerType = true;
      this.ruleTypeList = new ArrayList();
      this.chompBalanced = code ? (KFunction)(new Function2<Character, Character, Boolean>(this) {
         public final boolean invoke(char p0, char p1) {
            return ((RuleAnalyzer)this.receiver).chompCodeBalanced(p0, p1);
         }
      }) : (KFunction)(new Function2<Character, Character, Boolean>(this) {
         public final boolean invoke(char p0, char p1) {
            return ((RuleAnalyzer)this.receiver).chompRuleBalanced(p0, p1);
         }
      });
   }

   // $FF: synthetic method
   public RuleAnalyzer(String var1, boolean var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      this(var1, var2);
   }

   @NotNull
   public final String getElementsType() {
      return this.elementsType;
   }

   public final void setElementsType(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.elementsType = var1;
   }

   public final boolean getInnerType() {
      return this.innerType;
   }

   public final void setInnerType(boolean <set-?>) {
      this.innerType = var1;
   }

   public final void trim() {
      if (this.queue.charAt(this.pos) == '@' || Intrinsics.compare(this.queue.charAt(this.pos), 33) < 0) {
         for(int var2 = this.pos++; this.queue.charAt(this.pos) == '@' || Intrinsics.compare(this.queue.charAt(this.pos), 33) < 0; var2 = this.pos++) {
         }

         this.start = this.pos;
         this.startX = this.pos;
      }

   }

   public final void reSetPos() {
      this.pos = 0;
      this.startX = 0;
   }

   public final boolean consumeTo(@NotNull String seq) {
      Intrinsics.checkNotNullParameter(seq, "seq");
      this.start = this.pos;
      int offset = StringsKt.indexOf$default((CharSequence)this.queue, seq, this.pos, false, 4, (Object)null);
      boolean var10000;
      if (offset != -1) {
         this.pos = offset;
         var10000 = true;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public final boolean consumeToAny(@NotNull String... seq) {
      Intrinsics.checkNotNullParameter(seq, "seq");

      for(int pos = this.pos; pos != this.queue.length(); ++pos) {
         String[] var3 = seq;
         int var4 = 0;
         int var5 = seq.length;

         while(var4 < var5) {
            String s = var3[var4];
            ++var4;
            if (StringsKt.regionMatches$default(this.queue, pos, s, 0, s.length(), false, 16, (Object)null)) {
               this.step = s.length();
               this.pos = pos;
               return true;
            }
         }
      }

      return false;
   }

   private final int findToAny(char... seq) {
      for(int pos = this.pos; pos != this.queue.length(); ++pos) {
         char[] var3 = seq;
         int var4 = 0;
         int var5 = seq.length;

         while(var4 < var5) {
            char s = var3[var4];
            ++var4;
            if (this.queue.charAt(pos) == s) {
               return pos;
            }
         }
      }

      return -1;
   }

   public final boolean chompCodeBalanced(char open, char close) {
      int pos = this.pos;
      int depth = 0;
      int otherDepth = 0;
      boolean inSingleQuote = false;
      boolean inDoubleQuote = false;

      while(pos != this.queue.length()) {
         int var9 = pos++;
         char c = this.queue.charAt(var9);
         if (c != '\\') {
            if (c == '\'' && !inDoubleQuote) {
               inSingleQuote = !inSingleQuote;
            } else if (c == '"' && !inSingleQuote) {
               inDoubleQuote = !inDoubleQuote;
            }

            if (!inSingleQuote && !inDoubleQuote) {
               if (c == '[') {
                  ++depth;
               } else if (c == ']') {
                  depth += -1;
               } else if (depth == 0) {
                  if (c == open) {
                     ++otherDepth;
                  } else if (c == close) {
                     otherDepth += -1;
                  }
               }
            }
         } else {
            ++pos;
         }

         if (depth <= 0 && otherDepth <= 0) {
            break;
         }
      }

      boolean var10000;
      if (depth <= 0 && otherDepth <= 0) {
         this.pos = pos;
         var10000 = true;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public final boolean chompRuleBalanced(char open, char close) {
      int pos = this.pos;
      int depth = 0;
      boolean inSingleQuote = false;
      boolean inDoubleQuote = false;

      while(pos != this.queue.length()) {
         int var8 = pos++;
         char c = this.queue.charAt(var8);
         if (c == '\'' && !inDoubleQuote) {
            inSingleQuote = !inSingleQuote;
         } else if (c == '"' && !inSingleQuote) {
            inDoubleQuote = !inDoubleQuote;
         }

         if (!inSingleQuote && !inDoubleQuote) {
            if (c == '\\') {
               ++pos;
            } else if (c == open) {
               ++depth;
            } else if (c == close) {
               depth += -1;
            }
         }

         if (depth <= 0) {
            break;
         }
      }

      boolean var10000;
      if (depth > 0) {
         var10000 = false;
      } else {
         this.pos = pos;
         var10000 = true;
      }

      return var10000;
   }

   @NotNull
   public final ArrayList<String> splitRule(@NotNull String... split) {
      Intrinsics.checkNotNullParameter(split, "split");
      RuleAnalyzer var2 = this;
      String[] var3 = split;

      while(true) {
         RuleAnalyzer var4 = var2;
         Collection var14;
         String var17;
         int var18;
         boolean var21;
         boolean var24;
         String var26;
         ArrayList var27;
         if (var3.length == 1) {
            var2.elementsType = var3[0];
            if (!var2.consumeTo(var2.elementsType)) {
               var14 = (Collection)var2.rule;
               var17 = var2.queue;
               var18 = var2.startX;
               var21 = false;
               if (var17 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var26 = var17.substring(var18);
               Intrinsics.checkNotNullExpressionValue(var26, "(this as java.lang.String).substring(startIndex)");
               var17 = var26;
               var24 = false;
               var14.add(var17);
               var27 = var2.rule;
            } else {
               var2.step = var2.elementsType.length();
               var27 = var2.splitRuleNext();
            }

            return var27;
         }

         if (!var2.consumeToAny((String[])Arrays.copyOf(var3, var3.length))) {
            var14 = (Collection)var2.rule;
            var17 = var2.queue;
            var18 = var2.startX;
            var21 = false;
            if (var17 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var26 = var17.substring(var18);
            Intrinsics.checkNotNullExpressionValue(var26, "(this as java.lang.String).substring(startIndex)");
            var17 = var26;
            var24 = false;
            var14.add(var17);
            return var2.rule;
         }

         int end = var2.pos;
         var2.pos = var2.start;

         do {
            char[] var8 = new char[]{'[', '('};
            int st = var4.findToAny(var8);
            String var9;
            int var11;
            boolean var12;
            boolean var13;
            String[] var16;
            String var10001;
            String var10003;
            Collection var19;
            int var20;
            String var22;
            boolean var23;
            int var25;
            if (st == -1) {
               var16 = new String[1];
               var9 = var4.queue;
               var20 = var4.startX;
               var23 = false;
               if (var9 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10003 = var9.substring(var20, end);
               Intrinsics.checkNotNullExpressionValue(var10003, "(this as java.lang.Strin…ing(startIndex, endIndex)");
               var16[0] = var10003;
               var4.rule = CollectionsKt.arrayListOf(var16);
               var17 = var4.queue;
               var18 = end + var4.step;
               var21 = false;
               if (var17 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10001 = var17.substring(end, var18);
               Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
               var4.elementsType = var10001;

               for(var4.pos = end + var4.step; var4.consumeTo(var4.elementsType); var4.pos += var4.step) {
                  var19 = (Collection)var4.rule;
                  var22 = var4.queue;
                  var11 = var4.start;
                  var25 = var4.pos;
                  var13 = false;
                  if (var22 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var26 = var22.substring(var11, var25);
                  Intrinsics.checkNotNullExpressionValue(var26, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var22 = var26;
                  var23 = false;
                  var19.add(var22);
               }

               var19 = (Collection)var4.rule;
               var22 = var4.queue;
               var11 = var4.pos;
               var12 = false;
               if (var22 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var26 = var22.substring(var11);
               Intrinsics.checkNotNullExpressionValue(var26, "(this as java.lang.String).substring(startIndex)");
               var22 = var26;
               var23 = false;
               var19.add(var22);
               return var4.rule;
            }

            if (st > end) {
               var16 = new String[1];
               var9 = var4.queue;
               var20 = var4.startX;
               var23 = false;
               if (var9 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               } else {
                  var10003 = var9.substring(var20, end);
                  Intrinsics.checkNotNullExpressionValue(var10003, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var16[0] = var10003;
                  var4.rule = CollectionsKt.arrayListOf(var16);
                  var17 = var4.queue;
                  var18 = end + var4.step;
                  var21 = false;
                  if (var17 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  } else {
                     var10001 = var17.substring(end, var18);
                     Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                     var4.elementsType = var10001;

                     for(var4.pos = end + var4.step; var4.consumeTo(var4.elementsType) && var4.pos < st; var4.pos += var4.step) {
                        var19 = (Collection)var4.rule;
                        var22 = var4.queue;
                        var11 = var4.start;
                        var25 = var4.pos;
                        var13 = false;
                        if (var22 == null) {
                           throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }

                        var26 = var22.substring(var11, var25);
                        Intrinsics.checkNotNullExpressionValue(var26, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        var22 = var26;
                        var23 = false;
                        var19.add(var22);
                     }

                     if (var4.pos > st) {
                        var4.startX = var4.start;
                        var27 = var4.splitRuleNext();
                     } else {
                        var19 = (Collection)var4.rule;
                        var22 = var4.queue;
                        var11 = var4.pos;
                        var12 = false;
                        if (var22 == null) {
                           throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }

                        var26 = var22.substring(var11);
                        Intrinsics.checkNotNullExpressionValue(var26, "(this as java.lang.String).substring(startIndex)");
                        var22 = var26;
                        var23 = false;
                        var19.add(var22);
                        var27 = var4.rule;
                     }

                     return var27;
                  }
               }
            }

            var4.pos = st;
            char next = var4.queue.charAt(var4.pos) == '[' ? 93 : 41;
            if (!(Boolean)((Function2)var4.chompBalanced).invoke(var4.queue.charAt(var4.pos), Character.valueOf((char)next))) {
               Error var10000 = new Error;
               var9 = var4.queue;
               byte var10 = 0;
               var11 = var4.start;
               var12 = false;
               if (var9 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               String var10002 = var9.substring(var10, var11);
               Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.Strin…ing(startIndex, endIndex)");
               var10000.<init>(Intrinsics.stringPlus(var10002, "后未平衡"));
               throw var10000;
            }
         } while(end > var4.pos);

         var4.start = var4.pos;
         var2 = var4;
         var3 = (String[])Arrays.copyOf(var3, var3.length);
      }
   }

   @JvmName(
      name = "splitRuleNext"
   )
   private final ArrayList<String> splitRuleNext() {
      RuleAnalyzer var1 = this;

      while(true) {
         label80:
         while(true) {
            RuleAnalyzer var2 = var1;
            int end = var1.pos;
            var1.pos = var1.start;

            String var6;
            boolean var8;
            String var10000;
            do {
               char[] var5 = new char[]{'[', '('};
               int st = var2.findToAny(var5);
               boolean var9;
               boolean var10;
               Collection var13;
               String[] var16;
               String var10002;
               int var18;
               String var19;
               String var20;
               int var21;
               if (st == -1) {
                  var13 = (Collection)var2.rule;
                  var16 = new String[1];
                  var19 = var2.queue;
                  var21 = var2.startX;
                  var10 = false;
                  if (var19 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10002 = var19.substring(var21, end);
                  Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var16[0] = var10002;
                  var8 = false;
                  CollectionsKt.addAll(var13, var16);

                  for(var2.pos = end + var2.step; var2.consumeTo(var2.elementsType); var2.pos += var2.step) {
                     var13 = (Collection)var2.rule;
                     var20 = var2.queue;
                     var18 = var2.start;
                     var21 = var2.pos;
                     var10 = false;
                     if (var20 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                     }

                     var10000 = var20.substring(var18, var21);
                     Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                     var20 = var10000;
                     var8 = false;
                     var13.add(var20);
                  }

                  var13 = (Collection)var2.rule;
                  var20 = var2.queue;
                  var18 = var2.pos;
                  var9 = false;
                  if (var20 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10000 = var20.substring(var18);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
                  var20 = var10000;
                  var8 = false;
                  var13.add(var20);
                  return var2.rule;
               }

               if (st > end) {
                  var13 = (Collection)var2.rule;
                  var16 = new String[1];
                  var19 = var2.queue;
                  var21 = var2.startX;
                  var10 = false;
                  if (var19 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10002 = var19.substring(var21, end);
                  Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var16[0] = var10002;
                  Iterable var17 = (Iterable)CollectionsKt.arrayListOf(var16);
                  var8 = false;
                  CollectionsKt.addAll(var13, var17);

                  for(var2.pos = end + var2.step; var2.consumeTo(var2.elementsType) && var2.pos < st; var2.pos += var2.step) {
                     var13 = (Collection)var2.rule;
                     var20 = var2.queue;
                     var18 = var2.start;
                     var21 = var2.pos;
                     var10 = false;
                     if (var20 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                     }

                     var10000 = var20.substring(var18, var21);
                     Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                     var20 = var10000;
                     var8 = false;
                     var13.add(var20);
                  }

                  if (var2.pos <= st) {
                     var13 = (Collection)var2.rule;
                     var20 = var2.queue;
                     var18 = var2.pos;
                     var9 = false;
                     if (var20 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                     }

                     var10000 = var20.substring(var18);
                     Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
                     var20 = var10000;
                     var8 = false;
                     var13.add(var20);
                     return var2.rule;
                  }

                  var2.startX = var2.start;
                  var1 = var2;
                  continue label80;
               }

               var2.pos = st;
               char next = var2.queue.charAt(var2.pos) == '[' ? 93 : 41;
               if (!(Boolean)((Function2)var2.chompBalanced).invoke(var2.queue.charAt(var2.pos), Character.valueOf((char)next))) {
                  Error var22 = new Error;
                  var6 = var2.queue;
                  byte var15 = 0;
                  var18 = var2.start;
                  var9 = false;
                  if (var6 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10002 = var6.substring(var15, var18);
                  Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  var22.<init>(Intrinsics.stringPlus(var10002, "后未平衡"));
                  throw var22;
               }
            } while(end > var2.pos);

            var2.start = var2.pos;
            if (!var2.consumeTo(var2.elementsType)) {
               Collection var12 = (Collection)var2.rule;
               var6 = var2.queue;
               int var7 = var2.startX;
               var8 = false;
               if (var6 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10000 = var6.substring(var7);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
               var6 = var10000;
               boolean var14 = false;
               var12.add(var6);
               return var2.rule;
            }

            var1 = var2;
         }
      }
   }

   @NotNull
   public final String innerRule(@NotNull String inner, int startStep, int endStep, @NotNull Function1<? super String, String> fr) {
      Intrinsics.checkNotNullParameter(inner, "inner");
      Intrinsics.checkNotNullParameter(fr, "fr");
      StringBuilder st = new StringBuilder();

      while(true) {
         boolean var11;
         String var10001;
         boolean var18;
         while(this.consumeTo(inner)) {
            int posPre = this.pos;
            if (this.chompCodeBalanced('{', '}')) {
               String var8 = this.queue;
               int var9 = posPre + startStep;
               int var10 = this.pos - endStep;
               var11 = false;
               if (var8 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               var10001 = var8.substring(var9, var10);
               Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
               String frv = (String)fr.invoke(var10001);
               CharSequence var16 = (CharSequence)frv;
               var18 = false;
               boolean var19 = false;
               if (var16 != null && var16.length() != 0) {
                  var8 = this.queue;
                  var9 = this.startX;
                  var19 = false;
                  if (var8 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10001 = var8.substring(var9, posPre);
                  Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  st.append(Intrinsics.stringPlus(var10001, frv));
                  this.startX = this.pos;
                  continue;
               }
            }

            this.pos += inner.length();
         }

         String var10000;
         if (this.startX == 0) {
            var10000 = "";
         } else {
            boolean var17 = false;
            var18 = false;
            var11 = false;
            String var12 = this.queue;
            int var13 = this.startX;
            boolean var14 = false;
            if (var12 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10001 = var12.substring(var13);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
            st.append(var10001);
            String var15 = st.toString();
            Intrinsics.checkNotNullExpressionValue(var15, "st.apply {\n            append(queue.substring(startX))\n        }.toString()");
            var10000 = var15;
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   public static String innerRule$default(RuleAnalyzer var0, String var1, int var2, int var3, Function1 var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 1;
      }

      if ((var5 & 4) != 0) {
         var3 = 1;
      }

      return var0.innerRule(var1, var2, var3, var4);
   }

   @NotNull
   public final String innerRule(@NotNull String startStr, @NotNull String endStr, @NotNull Function1<? super String, String> fr) {
      Intrinsics.checkNotNullParameter(startStr, "startStr");
      Intrinsics.checkNotNullParameter(endStr, "endStr");
      Intrinsics.checkNotNullParameter(fr, "fr");
      StringBuilder st = new StringBuilder();

      boolean var10;
      String var10001;
      while(this.consumeTo(startStr)) {
         this.pos += startStr.length();
         int posPre = this.pos;
         if (this.consumeTo(endStr)) {
            String var7 = this.queue;
            int var8 = this.pos;
            boolean var9 = false;
            if (var7 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10001 = var7.substring(posPre, var8);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String frv = (String)fr.invoke(var10001);
            var7 = this.queue;
            var8 = this.startX;
            int var17 = posPre - startStr.length();
            var10 = false;
            if (var7 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10001 = var7.substring(var8, var17);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            st.append(Intrinsics.stringPlus(var10001, frv));
            this.pos += endStr.length();
            this.startX = this.pos;
         }
      }

      String var10000;
      if (this.startX == 0) {
         var10000 = this.queue;
      } else {
         boolean var15 = false;
         boolean var16 = false;
         var10 = false;
         String var11 = this.queue;
         int var12 = this.startX;
         boolean var13 = false;
         if (var11 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         }

         var10001 = var11.substring(var12);
         Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
         st.append(var10001);
         String var14 = st.toString();
         Intrinsics.checkNotNullExpressionValue(var14, "st.apply {\n            append(queue.substring(startX))\n        }.toString()");
         var10000 = var14;
      }

      return var10000;
   }

   @NotNull
   public final ArrayList<String> getRuleTypeList() {
      return this.ruleTypeList;
   }

   @NotNull
   public final KFunction<Boolean> getChompBalanced() {
      return this.chompBalanced;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lio/legado/app/model/analyzeRule/RuleAnalyzer$Companion;", "", "()V", "ESC", "", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
