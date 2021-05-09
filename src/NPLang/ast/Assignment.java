package NPLang.ast;

import NPLang.NPLangException;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Particle;

import java.util.List;

public class Assignment extends ASTList {
    public Assignment(List<ASTree> c) { super(c); }

    public Pronoun op1() { return (Pronoun)child(0); }
    public Particle particle() { return (Particle)child(1); }
    public Pronoun op2() { return (Pronoun)child(2); }

    // assignment: "assign" pronoun ("to" | "with") pronoun
    public String compile() {
        String left, right;
        switch (particle().text()) {
            case Particle.to:
                left = op2().compile();
                right = op1().compile();
                break;
            case Particle.with:
                left = op1().compile();
                right = op2().compile();
                break;
            default:
                throw new NPLangException("Error: Unknown Particle", particle());
        }

        compiled_code = left + " = " + right + "\n";
        return compiled_code;
    }
}
