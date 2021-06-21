package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;

import java.util.List;

public class Statement extends ASTList {
    private int index; // record the index in the parent Sentence's children list

    public Statement(List<ASTree> c) { super(c); }

    public void setIndex(int i) { index = i; }

    public Statement previous() {
        if (index == 0) return null;
        else return (Statement) parent.child(index-1);
    }

    // statement: declaration | assignment | calculation | invoke | returnExpr
    public String compile() { return compile(0); }
    public String compile(int indentDepth) {
        if (child(0) instanceof Function)
            compiled_code = ((Function)child(0)).compile(indentDepth);
        else
            compiled_code = child(0).compile();

        compiled_flag = true;
        return compiled_code;
    }
}
