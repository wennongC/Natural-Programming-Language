package NPLang.ast;

import NPLang.Util;
import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;

import java.util.ArrayList;
import java.util.List;

public class Sentence extends ASTList {
    public ArrayList<String> compiled_statements = new ArrayList<>();

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

        // Step 1: Compile each statement. (pronoun inside statements might invoke do_not_display)
        for (ASTree child: children) {
            ((Statement)child).compile(indentDepth);
        }

        // Step 2: Generate string by considering each statement's visibility
        for (ASTree child: children) {
            if (!child.visible) continue;
            statements.append(Util.repeatTab(indentDepth))
                    .append(child.getCompiledCode())
                    .append("\n");
        }

        compiled_code = statements.toString();
        compiled_flag = true;
        return compiled_code;
    }
}
