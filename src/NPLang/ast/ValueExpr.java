package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.lexer.Token;

import java.util.List;

public class ValueExpr extends ASTList {
    public ValueExpr(List<ASTree> c) { super(c); }

    public ASTree type() { return child(0); }
    public ASTree value() { return child(2); }
}
