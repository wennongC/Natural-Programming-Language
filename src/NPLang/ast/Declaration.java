package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.*;

import java.util.List;

public class Declaration extends ASTList {
    public Declaration(List<ASTree> c) { super(c); }

    public Identifier identifier() { return (Identifier) child(0); }
    public ASTree value() {
        if (numChildren() > 1) return child(1);
        else return null;
    }

    // "set a variable called" IDENTIFIER [("with" value)]
    public String compile() {
        compiled_code = identifier().name() + " = ";

        if (value() == null) compiled_code += "None";
        else compiled_code += value().compile();

        compiled_flag = true;
        return compiled_code;
    }
}
