package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.*;

import java.util.List;

public class Declaration extends ASTList {
    public Declaration(List<ASTree> c) { super(c); }

    public ASTree identifier() { return child(0); }
    public ASTree value() {
        if (numChildren() > 1) return child(1);
        else return null;
    }

    // "set a variable called" IDENTIFIER [("with" value)]
    public String compile() {
        compiled_code = ((Identifier)identifier()).name() + " = ";

        if (value() == null) compiled_code += "None\n";
        else compiled_code += value().compile() + "\n";

        return compiled_code;
    }
}
