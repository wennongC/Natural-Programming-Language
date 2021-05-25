package NPLang.ast;

import NPLang.NPLangException;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;
import NPLang.ast.element.Particle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pronoun extends ASTList {
    public static final String it = "it";
    public static final String them = "them";

    public Pronoun(List<ASTree> c) { super(c); }


    private String analyseNode(Statement stmt) {
        ASTree node = stmt.child(0);
        if (node instanceof Declaration) {
            return ((Declaration)node).identifier().name();
        } else if (node instanceof Calculation) {
            switch (((Calculation) node).particle().text()) {
                case Particle.and:
                    // Assign the whole calculation expression to this Pronoun
                    return ((Calculation) node).compiled_code;
                case Particle.by:
                    // Assign the left value to this Pronoun
                    return ((Calculation) node).op1().compiled_code;
            }
        } else if (node instanceof Assignment) {
            return ((Assignment) node).get_lr_op(Assignment.LR_VALUE.LEFT_VALUE).compiled_code;
        }
        return null;
    }

    // Analyze the pronoun word
    private void decodePronoun(String p) {
        Statement previous;
        switch (p) {
            case Pronoun.it:
                previous = ((Statement)parent().parent()).previous();
                if (previous == null)
                    throw new NPLangException("Error: unable to decode pronoun 'it'", this);
                compiled_code = analyseNode(previous);
                break;

            case Pronoun.them:
                ArrayList<String> results = new ArrayList<>();
                previous = ((Statement)parent().parent()).previous();
                while (previous != null) {
                    results.add(analyseNode(previous));
                    previous = previous.previous(); // Continue to trace back one statement before
                }
                Collections.reverse(results);
                compiled_code = String.join(", ", results);
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
            decodePronoun(compiled_code);
        }

        return compiled_code;
    }
}
