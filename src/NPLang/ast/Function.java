package NPLang.ast;

import NPLang.Util;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;
import NPLang.ast.element.Literal;

import java.util.List;

public class Function extends ASTList {

    public Function(List<ASTree> c) { super(c); }

    public String functionName() { return ((Identifier)child(0)).name(); }
    public int numOfArguments() {
        if (numChildren() < 2) return 0;
        if (child(1) instanceof Literal) {
            return ((Literal) child(1)).value();
        } else return 0;
    }

    // function: “define function” IDENTIFIER ("." | EOL) program “end define”
    public String compile() { return compile(0); }
    public String compile(int indentDepth) {

        StringBuilder funcDef = new StringBuilder();

        funcDef.append("def ").append(functionName()).append("(");

        for(int i = 0; i < numOfArguments(); i++) {
            if (i == 0) funcDef.append("p1");
            else funcDef.append(String.format(", p%d", i+1));
        }

        funcDef.append(") {\n");

        int bodyIdx = 1;
        if (child(1) instanceof Literal) bodyIdx = 2;
        for (; bodyIdx < numChildren(); bodyIdx++) {
            if (child(bodyIdx) instanceof Sentence)
                funcDef.append(((Sentence)child(bodyIdx)).compile(indentDepth + 1));
        }
        funcDef.append(Util.repeatTab(indentDepth)).append("}");

        compiled_code = funcDef.toString();
        compiled_flag = true;
        return compiled_code;
    }


}
