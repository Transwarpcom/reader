package org.springframework.boot.web.servlet.view;

import com.samskivert.mustache.Mustache;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/view/MustacheViewResolver.class */
public class MustacheViewResolver extends AbstractTemplateViewResolver {
    private final Mustache.Compiler compiler;
    private String charset;

    public MustacheViewResolver() {
        this.compiler = Mustache.compiler();
        setViewClass(requiredViewClass());
    }

    public MustacheViewResolver(Mustache.Compiler compiler) {
        this.compiler = compiler;
        setViewClass(requiredViewClass());
    }

    protected Class<?> requiredViewClass() {
        return MustacheView.class;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        MustacheView view = super.buildView(viewName);
        view.setCompiler(this.compiler);
        view.setCharset(this.charset);
        return view;
    }
}
