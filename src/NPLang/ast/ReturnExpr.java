package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;

import java.util.List;

public class ReturnExpr extends ASTList {
    public ReturnExpr(List<ASTree> c) { super(c); }

    public Pronoun returnValue() { return ((Pronoun)child(0)); }

    // returnExpr: "return" Pronoun
    public String compile() {
        compiled_code = "return " + returnValue().compile();
        return compiled_code;
    }
}
