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
            case Type.t_integer:
                compiled_code += String.valueOf(((Literal)value()).value());
                break;

            case Type.t_string:
                compiled_code += '\"' + ((Literal)value()).text() + '\"';
                break;

            case Type.t_boolean:
                String bool = ((Literal)value()).text();
                if (bool.equalsIgnoreCase("true")) compiled_code += "True";
                else if (bool.equalsIgnoreCase("false")) compiled_code += "False";
                else throw new NPLangException("Unknown Value for Type 'boolean': " + bool, this);
                break;

            default:
                throw new NPLangException("Unknown Type: " + type(), this);
        }
        return compiled_code;
    }
}
