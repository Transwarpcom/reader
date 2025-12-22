package org.antlr.v4.runtime.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/InterpreterDataReader.class */
public class InterpreterDataReader {

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/InterpreterDataReader$InterpreterData.class */
    public static class InterpreterData {
        ATN atn;
        Vocabulary vocabulary;
        List<String> ruleNames;
        List<String> channels;
        List<String> modes;
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r9v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x028e: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:91:0x028e */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x028a: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('br' java.io.BufferedReader)]) A[TRY_LEAVE], block:B:89:0x028a */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r9v1, names: [br], types: [java.io.BufferedReader] */
    public static InterpreterData parseFile(String fileName) throws IOException {
        BufferedReader bufferedReader;
        Throwable th;
        ArrayList arrayList;
        ArrayList arrayList2;
        String line;
        int i;
        InterpreterData result = new InterpreterData();
        result.ruleNames = new ArrayList();
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(fileName));
                th = null;
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
            } finally {
            }
        } catch (IOException e) {
        }
        if (!bufferedReader.readLine().equals("token literal names:")) {
            throw new RuntimeException("Unexpected data entry");
        }
        while (true) {
            String line2 = bufferedReader.readLine();
            if (line2 == null || line2.isEmpty()) {
                break;
            }
            arrayList.add(line2.equals("null") ? "" : line2);
        }
        if (!bufferedReader.readLine().equals("token symbolic names:")) {
            throw new RuntimeException("Unexpected data entry");
        }
        while (true) {
            String line3 = bufferedReader.readLine();
            if (line3 == null || line3.isEmpty()) {
                break;
            }
            arrayList2.add(line3.equals("null") ? "" : line3);
        }
        result.vocabulary = new VocabularyImpl((String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]));
        if (!bufferedReader.readLine().equals("rule names:")) {
            throw new RuntimeException("Unexpected data entry");
        }
        while (true) {
            line = bufferedReader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            result.ruleNames.add(line);
        }
        if (line.equals("channel names:")) {
            result.channels = new ArrayList();
            while (true) {
                String line4 = bufferedReader.readLine();
                if (line4 == null || line4.isEmpty()) {
                    break;
                }
                result.channels.add(line4);
            }
            if (!bufferedReader.readLine().equals("mode names:")) {
                throw new RuntimeException("Unexpected data entry");
            }
            result.modes = new ArrayList();
            while (true) {
                String line5 = bufferedReader.readLine();
                if (line5 == null || line5.isEmpty()) {
                    break;
                }
                result.modes.add(line5);
            }
        }
        if (!bufferedReader.readLine().equals("atn:")) {
            throw new RuntimeException("Unexpected data entry");
        }
        String[] strArrSplit = bufferedReader.readLine().split(",");
        char[] cArr = new char[strArrSplit.length];
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String str = strArrSplit[i2];
            if (str.startsWith("[")) {
                i = Integer.parseInt(str.substring(1).trim());
            } else if (str.endsWith("]")) {
                i = Integer.parseInt(str.substring(0, str.length() - 1).trim());
            } else {
                i = Integer.parseInt(str.trim());
            }
            cArr[i2] = (char) i;
        }
        result.atn = new ATNDeserializer().deserialize(cArr);
        if (bufferedReader != null) {
            if (0 != 0) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            } else {
                bufferedReader.close();
            }
        }
        return result;
    }
}
