package NPLang.ast;

import NPLang.NPLangException;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Literal;
import NPLang.ast.element.Type;

import java.util.List;

public class ValueExpr extends ASTList {
    public ValueExpr(List<ASTree> c) { super(c); }

    public ASTree type() { return child(0); }
    public ASTree value() { return child(1); }

    public String compile() {
        switch (((Type)type()).name()) {
            case Type.integer:
                compiled_code += String.valueOf(((Literal)value()).value());
                break;
            case Type.string:
                compiled_code += '\"' + ((Literal)value()).text() + '\"';
                break;
            default:
                throw new NPLangException("Unknown Type: " + type(), this);
        }
        return compiled_code;
    }
}
