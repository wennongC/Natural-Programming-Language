package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;

import java.util.List;

public class Pronoun extends ASTList {
    public static final int it = 1;
    public static final int them = 2;

    public Pronoun(List<ASTree> c) { super(c); }


    public String analyze() {
        // To-do
        // Analyze the pronoun word
        return null;
    }

    // pronoun: value | IDENTIFIER | it | them
    public String compile() {
        compiled_code = analyze();

        if (compiled_code == null) {
            if (child(0) instanceof ValueExpr)
                compiled_code = child(0).compile();
            else
                compiled_code = ((Identifier)child(0)).name();
        }

        return compiled_code;
    }
}
