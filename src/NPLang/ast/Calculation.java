package NPLang.ast;

import NPLang.NPLangException;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.*;

import java.util.List;

public class Calculation extends ASTList {
    public Calculation(List<ASTree> c) { super(c); }

    public Operator operator() { return (Operator)child(0); }
    public Pronoun op1() { return (Pronoun)child(1); }
    public Particle particle() { return (Particle)(child(2).child(0)); }
    public Pronoun op2() { return (Pronoun)(child(2).child(1)); }

    public String getOperator() {
        switch (operator().name()) {
            case Operator.add:
                return "+";
            case Operator.subtract:
            case Operator.minus:
                return "-";
            case Operator.multiply:
                return "*";
            case Operator.divide:
                return "/";
            default:
                throw new NPLangException("Error: Unknown Operator", operator());
        }
    }

    // calculation: OP pronoun [("by" | "and") pronoun]
    public String compile() {
        if (numChildren() == 2) {

            compiled_code += getOperator() + op1().compile();

        } else {
            switch (particle().text()) {
                case Particle.by:
                    compiled_code += op1().compile() + " " + getOperator() + "= " + op2().compile();
                    break;
                case Particle.and:
                    compiled_code += op1().compile() + " " + getOperator() + " " + op2().compile();
                    break;
                default:
                    throw new NPLangException("Error: Unknown Particle", particle());
            }
        }

        compiled_flag = true;
        return compiled_code;
    }
}
