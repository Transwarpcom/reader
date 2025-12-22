package net.minidev.asm;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/BeansAccessBuilder.class */
public class BeansAccessBuilder {
    private static String METHOD_ACCESS_NAME = Type.getInternalName(BeansAccess.class);
    final Class<?> type;
    final Accessor[] accs;
    final DynamicClassLoader loader;
    final String className;
    final String accessClassName;
    final String accessClassNameInternal;
    final String classNameInternal;
    final HashMap<Class<?>, Method> convMtds = new HashMap<>();
    Class<? extends Exception> exceptionClass = NoSuchFieldException.class;

    public BeansAccessBuilder(Class<?> type, Accessor[] accs, DynamicClassLoader loader) {
        this.type = type;
        this.accs = accs;
        this.loader = loader;
        this.className = type.getName();
        if (this.className.startsWith("java.")) {
            this.accessClassName = "net.minidev.asm." + this.className + "AccAccess";
        } else {
            this.accessClassName = this.className.concat("AccAccess");
        }
        this.accessClassNameInternal = this.accessClassName.replace('.', '/');
        this.classNameInternal = this.className.replace('.', '/');
    }

    public void addConversion(Iterable<Class<?>> conv) throws SecurityException {
        if (conv == null) {
            return;
        }
        for (Class<?> c : conv) {
            addConversion(c);
        }
    }

    public void addConversion(Class<?> conv) throws SecurityException {
        if (conv == null) {
            return;
        }
        for (Method mtd : conv.getMethods()) {
            if ((mtd.getModifiers() & 8) != 0) {
                Class<?>[] param = mtd.getParameterTypes();
                if (param.length == 1 && param[0].equals(Object.class)) {
                    Class<?> rType = mtd.getReturnType();
                    if (!rType.equals(Void.TYPE)) {
                        this.convMtds.put(rType, mtd);
                    }
                }
            }
        }
    }

    public Class<?> bulid() {
        ClassWriter cw = new ClassWriter(1);
        boolean USE_HASH = this.accs.length > 10;
        String signature = "Lnet/minidev/asm/BeansAccess<L" + this.classNameInternal + ";>;";
        cw.visit(50, 33, this.accessClassNameInternal, signature, METHOD_ACCESS_NAME, null);
        MethodVisitor mv = cw.visitMethod(1, Constants.CONSTRUCTOR_NAME, "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, METHOD_ACCESS_NAME, Constants.CONSTRUCTOR_NAME, "()V", false);
        mv.visitInsn(177);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        MethodVisitor mv2 = cw.visitMethod(1, "set", "(Ljava/lang/Object;ILjava/lang/Object;)V", null, null);
        mv2.visitCode();
        if (this.accs.length != 0) {
            if (this.accs.length > 14) {
                mv2.visitVarInsn(21, 2);
                Label[] labels = ASMUtil.newLabels(this.accs.length);
                Label defaultLabel = new Label();
                mv2.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);
                int i = 0;
                for (Accessor acc : this.accs) {
                    int i2 = i;
                    i++;
                    mv2.visitLabel(labels[i2]);
                    if (!acc.isWritable()) {
                        mv2.visitInsn(177);
                    } else {
                        internalSetFiled(mv2, acc);
                    }
                }
                mv2.visitLabel(defaultLabel);
            } else {
                Label[] labels2 = ASMUtil.newLabels(this.accs.length);
                int i3 = 0;
                for (Accessor acc2 : this.accs) {
                    ifNotEqJmp(mv2, 2, i3, labels2[i3]);
                    internalSetFiled(mv2, acc2);
                    mv2.visitLabel(labels2[i3]);
                    mv2.visitFrame(3, 0, null, 0, null);
                    i3++;
                }
            }
        }
        if (this.exceptionClass != null) {
            throwExIntParam(mv2, this.exceptionClass);
        } else {
            mv2.visitInsn(177);
        }
        mv2.visitMaxs(0, 0);
        mv2.visitEnd();
        MethodVisitor mv3 = cw.visitMethod(1, BeanUtil.PREFIX_GETTER_GET, "(Ljava/lang/Object;I)Ljava/lang/Object;", null, null);
        mv3.visitCode();
        if (this.accs.length != 0) {
            if (this.accs.length > 14) {
                mv3.visitVarInsn(21, 2);
                Label[] labels3 = ASMUtil.newLabels(this.accs.length);
                Label defaultLabel2 = new Label();
                mv3.visitTableSwitchInsn(0, labels3.length - 1, defaultLabel2, labels3);
                int i4 = 0;
                for (Accessor acc3 : this.accs) {
                    int i5 = i4;
                    i4++;
                    mv3.visitLabel(labels3[i5]);
                    mv3.visitFrame(3, 0, null, 0, null);
                    if (!acc3.isReadable()) {
                        mv3.visitInsn(1);
                        mv3.visitInsn(176);
                    } else {
                        mv3.visitVarInsn(25, 1);
                        mv3.visitTypeInsn(192, this.classNameInternal);
                        Type fieldType = Type.getType(acc3.getType());
                        if (acc3.isPublic() || acc3.getter == null) {
                            mv3.visitFieldInsn(180, this.classNameInternal, acc3.getName(), fieldType.getDescriptor());
                        } else {
                            String sig = Type.getMethodDescriptor(acc3.getter);
                            mv3.visitMethodInsn(182, this.classNameInternal, acc3.getter.getName(), sig, false);
                        }
                        ASMUtil.autoBoxing(mv3, fieldType);
                        mv3.visitInsn(176);
                    }
                }
                mv3.visitLabel(defaultLabel2);
                mv3.visitFrame(3, 0, null, 0, null);
            } else {
                Label[] labels4 = ASMUtil.newLabels(this.accs.length);
                int i6 = 0;
                for (Accessor acc4 : this.accs) {
                    ifNotEqJmp(mv3, 2, i6, labels4[i6]);
                    mv3.visitVarInsn(25, 1);
                    mv3.visitTypeInsn(192, this.classNameInternal);
                    Type fieldType2 = Type.getType(acc4.getType());
                    if (acc4.isPublic() || acc4.getter == null) {
                        mv3.visitFieldInsn(180, this.classNameInternal, acc4.getName(), fieldType2.getDescriptor());
                    } else {
                        if (acc4.getter == null) {
                            throw new RuntimeException("no Getter for field " + acc4.getName() + " in class " + this.className);
                        }
                        String sig2 = Type.getMethodDescriptor(acc4.getter);
                        mv3.visitMethodInsn(182, this.classNameInternal, acc4.getter.getName(), sig2, false);
                    }
                    ASMUtil.autoBoxing(mv3, fieldType2);
                    mv3.visitInsn(176);
                    mv3.visitLabel(labels4[i6]);
                    mv3.visitFrame(3, 0, null, 0, null);
                    i6++;
                }
            }
        } else {
            mv3.visitFrame(3, 0, null, 0, null);
        }
        if (this.exceptionClass != null) {
            throwExIntParam(mv3, this.exceptionClass);
        } else {
            mv3.visitInsn(1);
            mv3.visitInsn(176);
        }
        mv3.visitMaxs(0, 0);
        mv3.visitEnd();
        if (!USE_HASH) {
            MethodVisitor mv4 = cw.visitMethod(1, "set", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V", null, null);
            mv4.visitCode();
            Label[] labels5 = ASMUtil.newLabels(this.accs.length);
            int i7 = 0;
            for (Accessor acc5 : this.accs) {
                mv4.visitVarInsn(25, 2);
                mv4.visitLdcInsn(acc5.fieldName);
                mv4.visitMethodInsn(182, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
                mv4.visitJumpInsn(153, labels5[i7]);
                internalSetFiled(mv4, acc5);
                mv4.visitLabel(labels5[i7]);
                mv4.visitFrame(3, 0, null, 0, null);
                i7++;
            }
            if (this.exceptionClass != null) {
                throwExStrParam(mv4, this.exceptionClass);
            } else {
                mv4.visitInsn(177);
            }
            mv4.visitMaxs(0, 0);
            mv4.visitEnd();
        }
        if (!USE_HASH) {
            MethodVisitor mv5 = cw.visitMethod(1, BeanUtil.PREFIX_GETTER_GET, "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", null, null);
            mv5.visitCode();
            Label[] labels6 = ASMUtil.newLabels(this.accs.length);
            int i8 = 0;
            for (Accessor acc6 : this.accs) {
                mv5.visitVarInsn(25, 2);
                mv5.visitLdcInsn(acc6.fieldName);
                mv5.visitMethodInsn(182, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
                mv5.visitJumpInsn(153, labels6[i8]);
                mv5.visitVarInsn(25, 1);
                mv5.visitTypeInsn(192, this.classNameInternal);
                Type fieldType3 = Type.getType(acc6.getType());
                if (acc6.isPublic() || acc6.getter == null) {
                    mv5.visitFieldInsn(180, this.classNameInternal, acc6.getName(), fieldType3.getDescriptor());
                } else {
                    String sig3 = Type.getMethodDescriptor(acc6.getter);
                    mv5.visitMethodInsn(182, this.classNameInternal, acc6.getter.getName(), sig3, false);
                }
                ASMUtil.autoBoxing(mv5, fieldType3);
                mv5.visitInsn(176);
                mv5.visitLabel(labels6[i8]);
                mv5.visitFrame(3, 0, null, 0, null);
                i8++;
            }
            if (this.exceptionClass != null) {
                throwExStrParam(mv5, this.exceptionClass);
            } else {
                mv5.visitInsn(1);
                mv5.visitInsn(176);
            }
            mv5.visitMaxs(0, 0);
            mv5.visitEnd();
        }
        MethodVisitor mv6 = cw.visitMethod(1, "newInstance", "()Ljava/lang/Object;", null, null);
        mv6.visitCode();
        mv6.visitTypeInsn(187, this.classNameInternal);
        mv6.visitInsn(89);
        mv6.visitMethodInsn(183, this.classNameInternal, Constants.CONSTRUCTOR_NAME, "()V", false);
        mv6.visitInsn(176);
        mv6.visitMaxs(2, 1);
        mv6.visitEnd();
        cw.visitEnd();
        byte[] data = cw.toByteArray();
        return this.loader.defineClass(this.accessClassName, data);
    }

    private void dumpDebug(byte[] data, String destFile) {
    }

    private void internalSetFiled(MethodVisitor mv, Accessor acc) {
        mv.visitVarInsn(25, 1);
        mv.visitTypeInsn(192, this.classNameInternal);
        mv.visitVarInsn(25, 3);
        Type fieldType = Type.getType(acc.getType());
        Class<?> type = acc.getType();
        String destClsName = Type.getInternalName(type);
        Method conMtd = this.convMtds.get(type);
        if (conMtd != null) {
            String clsSig = Type.getInternalName(conMtd.getDeclaringClass());
            String mtdName = conMtd.getName();
            String mtdSig = Type.getMethodDescriptor(conMtd);
            mv.visitMethodInsn(184, clsSig, mtdName, mtdSig, false);
        } else if (acc.isEnum()) {
            Label isNull = new Label();
            mv.visitJumpInsn(198, isNull);
            mv.visitVarInsn(25, 3);
            mv.visitMethodInsn(182, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(184, destClsName, "valueOf", "(Ljava/lang/String;)L" + destClsName + ";", false);
            mv.visitVarInsn(58, 3);
            mv.visitLabel(isNull);
            mv.visitFrame(3, 0, null, 0, null);
            mv.visitVarInsn(25, 1);
            mv.visitTypeInsn(192, this.classNameInternal);
            mv.visitVarInsn(25, 3);
            mv.visitTypeInsn(192, destClsName);
        } else if (type.equals(String.class)) {
            Label isNull2 = new Label();
            mv.visitJumpInsn(198, isNull2);
            mv.visitVarInsn(25, 3);
            mv.visitMethodInsn(182, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
            mv.visitVarInsn(58, 3);
            mv.visitLabel(isNull2);
            mv.visitFrame(3, 0, null, 0, null);
            mv.visitVarInsn(25, 1);
            mv.visitTypeInsn(192, this.classNameInternal);
            mv.visitVarInsn(25, 3);
            mv.visitTypeInsn(192, destClsName);
        } else {
            mv.visitTypeInsn(192, destClsName);
        }
        if (acc.isPublic() || acc.setter == null) {
            mv.visitFieldInsn(181, this.classNameInternal, acc.getName(), fieldType.getDescriptor());
        } else {
            String sig = Type.getMethodDescriptor(acc.setter);
            mv.visitMethodInsn(182, this.classNameInternal, acc.setter.getName(), sig, false);
        }
        mv.visitInsn(177);
    }

    private void throwExIntParam(MethodVisitor mv, Class<?> exCls) {
        String exSig = Type.getInternalName(exCls);
        mv.visitTypeInsn(187, exSig);
        mv.visitInsn(89);
        mv.visitLdcInsn("mapping " + this.className + " failed to map field:");
        mv.visitVarInsn(21, 2);
        mv.visitMethodInsn(184, "java/lang/Integer", "toString", "(I)Ljava/lang/String;", false);
        mv.visitMethodInsn(182, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;", false);
        mv.visitMethodInsn(183, exSig, Constants.CONSTRUCTOR_NAME, "(Ljava/lang/String;)V", false);
        mv.visitInsn(191);
    }

    private void throwExStrParam(MethodVisitor mv, Class<?> exCls) {
        String exSig = Type.getInternalName(exCls);
        mv.visitTypeInsn(187, exSig);
        mv.visitInsn(89);
        mv.visitLdcInsn("mapping " + this.className + " failed to map field:");
        mv.visitVarInsn(25, 2);
        mv.visitMethodInsn(182, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;", false);
        mv.visitMethodInsn(183, exSig, Constants.CONSTRUCTOR_NAME, "(Ljava/lang/String;)V", false);
        mv.visitInsn(191);
    }

    private void ifNotEqJmp(MethodVisitor mv, int param, int value, Label label) {
        mv.visitVarInsn(21, param);
        if (value == 0) {
            mv.visitJumpInsn(154, label);
            return;
        }
        if (value == 1) {
            mv.visitInsn(4);
            mv.visitJumpInsn(160, label);
            return;
        }
        if (value == 2) {
            mv.visitInsn(5);
            mv.visitJumpInsn(160, label);
            return;
        }
        if (value == 3) {
            mv.visitInsn(6);
            mv.visitJumpInsn(160, label);
            return;
        }
        if (value == 4) {
            mv.visitInsn(7);
            mv.visitJumpInsn(160, label);
        } else if (value == 5) {
            mv.visitInsn(8);
            mv.visitJumpInsn(160, label);
        } else {
            if (value >= 6) {
                mv.visitIntInsn(16, value);
                mv.visitJumpInsn(160, label);
                return;
            }
            throw new RuntimeException("non supported negative values");
        }
    }
}
