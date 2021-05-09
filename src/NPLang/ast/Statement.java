package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;

import java.util.List;

public class Statement extends ASTList {
    public Statement(List<ASTree> c) { super(c); }

    // statement: declaration | assignment | calculation
    public String compile() {
        compiled_code = child(0).compile();
        return compiled_code;
    }
}
