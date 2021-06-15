package NPLang.ast;

import NPLang.Util;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;

import java.util.List;

public class Function extends ASTList {

    public Function(List<ASTree> c) { super(c); }

    public String functionName() { return ((Identifier)child(0)).name(); }

    // function: “define function” IDENTIFIER ("." | EOL) program “end define”
    public String compile() { return compile(0); }
    public String compile(int indentDepth) {

        StringBuilder funcDef = new StringBuilder();

        funcDef.append("def ").append(functionName()).append("() {\n");
        for (int i = 1; i < numChildren(); i++) {
            if (child(i) instanceof Sentence)
                funcDef.append(((Sentence)child(i)).compile(indentDepth + 1));
        }
        funcDef.append(Util.repeatTab(indentDepth)).append("}");

        compiled_code = funcDef.toString();
        return compiled_code;
    }


}
