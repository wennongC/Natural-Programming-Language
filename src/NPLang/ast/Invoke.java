package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;

import java.util.List;

public class Invoke extends ASTList {

    public Invoke(List<ASTree> c) { super(c); }

    public String functionName() { return ((Identifier)child(1)).name(); }
    public Pronoun arguments() { return numChildren() > 2 ? (Pronoun)child(2) : null; }

    // invoke: “invoke” IDENTIFIER “with” pronoun
    public String compile() {
        String args = "";
        if (numChildren() > 2) args = arguments().compile();

        compiled_code = functionName() + "(" + args + ")";

        return compiled_code;
    }
}

