package NPLang.ast;

import NPLang.NPLangException;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Particle;
import NPLang.ast.element.Type;

import java.util.List;

public class Assignment extends ASTList {
    public enum LR_VALUE { LEFT_VALUE, RIGHT_VALUE }
    public Assignment(List<ASTree> c) { super(c); }

    public Pronoun op1() { return (Pronoun)child(0); }
    public Particle particle() { return (Particle)child(1); }
    public Pronoun op2() { return (Pronoun)child(2); }

    public String asType() { return numChildren() > 3 ? ((Type)child(3)).name() : null; }

    public Pronoun get_lr_op(LR_VALUE lr) {
        switch (particle().text()) {
            case Particle.to:
                return lr == LR_VALUE.LEFT_VALUE ? op2() : op1();
            case Particle.with:
                return lr == LR_VALUE.LEFT_VALUE ? op1() : op2();
            default:
                throw new NPLangException(
                        "Error: Unknown Particle " + particle().text() + " in Assignment", particle()
                );
        }
    }

    // assignment: "assign" pronoun ("to" | "with") pronoun
    public String compile() {
        compiled_code = get_lr_op(LR_VALUE.LEFT_VALUE).compile() + " = ";

        String type = asType();
        if (type != null)
            switch (type) {
                case Type.t_list:
                    compiled_code += "[" + get_lr_op(LR_VALUE.RIGHT_VALUE).compile() + "]";
                    break;
                case Type.t_tuple:
                    compiled_code += "(" + get_lr_op(LR_VALUE.RIGHT_VALUE).compile() + ")";
                    break;
                default:

            }
        else
            compiled_code += get_lr_op(LR_VALUE.RIGHT_VALUE).compile();

        return compiled_code;
    }
}
