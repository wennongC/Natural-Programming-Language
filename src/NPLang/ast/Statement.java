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

    // statement: declaration | assignment | calculation
    public String compile() {
        compiled_code = child(0).compile();
        return compiled_code;
    }
}
