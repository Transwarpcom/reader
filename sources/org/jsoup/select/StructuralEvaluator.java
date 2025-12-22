package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.nodes.Element;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator.class */
abstract class StructuralEvaluator extends Evaluator {
    Evaluator evaluator;

    StructuralEvaluator() {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$Root.class */
    static class Root extends Evaluator {
        Root() {
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            return root == element;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$Has.class */
    static class Has extends StructuralEvaluator {
        public Has(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Iterator<Element> it = element.getAllElements().iterator();
            while (it.hasNext()) {
                Element e = it.next();
                if (e != element && this.evaluator.matches(element, e)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":has(%s)", this.evaluator);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$Not.class */
    static class Not extends StructuralEvaluator {
        public Not(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element node) {
            return !this.evaluator.matches(root, node);
        }

        public String toString() {
            return String.format(":not(%s)", this.evaluator);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$Parent.class */
    static class Parent extends StructuralEvaluator {
        public Parent(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            if (root == element) {
                return false;
            }
            Element elementParent = element.parent();
            while (true) {
                Element parent = elementParent;
                if (parent != null) {
                    if (this.evaluator.matches(root, parent)) {
                        return true;
                    }
                    if (parent != root) {
                        elementParent = parent.parent();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        public String toString() {
            return String.format("%s ", this.evaluator);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$ImmediateParent.class */
    static class ImmediateParent extends StructuralEvaluator {
        public ImmediateParent(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Element parent;
            return (root == element || (parent = element.parent()) == null || !this.evaluator.matches(root, parent)) ? false : true;
        }

        public String toString() {
            return String.format("%s > ", this.evaluator);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$PreviousSibling.class */
    static class PreviousSibling extends StructuralEvaluator {
        public PreviousSibling(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            if (root == element) {
                return false;
            }
            Element elementPreviousElementSibling = element.previousElementSibling();
            while (true) {
                Element prev = elementPreviousElementSibling;
                if (prev != null) {
                    if (this.evaluator.matches(root, prev)) {
                        return true;
                    }
                    elementPreviousElementSibling = prev.previousElementSibling();
                } else {
                    return false;
                }
            }
        }

        public String toString() {
            return String.format("%s ~ ", this.evaluator);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/StructuralEvaluator$ImmediatePreviousSibling.class */
    static class ImmediatePreviousSibling extends StructuralEvaluator {
        public ImmediatePreviousSibling(Evaluator evaluator) {
            this.evaluator = evaluator;
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element element) {
            Element prev;
            return (root == element || (prev = element.previousElementSibling()) == null || !this.evaluator.matches(root, prev)) ? false : true;
        }

        public String toString() {
            return String.format("%s + ", this.evaluator);
        }
    }
}
