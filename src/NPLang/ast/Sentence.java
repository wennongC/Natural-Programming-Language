package NPLang.ast;

import NPLang.Util;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;

import java.util.List;

public class Sentence extends ASTList {
    public Sentence(List<ASTree> c) {
        super(c);

        // Assign index for each statement
        int i = 0;
        for (ASTree child: children) {
            ((Statement)child).setIndex(i++);
        }
    }

    // sentence: statement {"," [EOL] "then" statement}
    public String compile() { return compile(0); }
    public String compile(int indentDepth) {
        StringBuilder statements = new StringBuilder();

        for (ASTree child: children) {
            statements.append(Util.repeatTab(indentDepth))
                    .append(((Statement)child).compile(indentDepth))
                    .append("\n");
        }
        compiled_code = statements.toString();
        return compiled_code;
    }
}
