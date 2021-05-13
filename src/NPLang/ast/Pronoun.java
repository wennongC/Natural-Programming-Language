package NPLang.ast;

import NPLang.NPLangException;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;
import NPLang.ast.element.Particle;

import java.util.List;

public class Pronoun extends ASTList {
    public static final String it = "it";
    public static final String them = "them";

    public Pronoun(List<ASTree> c) { super(c); }


    // Analyze the pronoun word
    public void decodePronoun() {
        switch (compiled_code) {
            case Pronoun.it:
                Statement previous = ((Statement)parent().parent()).previous();
                if (previous == null)
                    throw new NPLangException("Error: unable to decode pronoun 'it'", this);

                ASTree node = previous.child(0);
                if (node instanceof Declaration) {
                    compiled_code = ((Declaration)node).identifier().name();
                } else if (node instanceof Calculation) {
                    switch (((Calculation) node).particle().text()) {
                        case Particle.and:
                            compiled_code = ((Calculation) node).compiled_code;
                            break;
                        case Particle.by:
                            compiled_code = ((Calculation) node).op1().compiled_code;
                            break;
                    }
                }
                break;
            case Pronoun.them:

                break;
            default:
        }
    }

    // pronoun: value | IDENTIFIER | it | them
    public String compile() {

        if (child(0) instanceof ValueExpr)
            compiled_code = child(0).compile();
        else {
            compiled_code = ((Identifier) child(0)).name();
             decodePronoun();
        }

        return compiled_code;
    }
}
