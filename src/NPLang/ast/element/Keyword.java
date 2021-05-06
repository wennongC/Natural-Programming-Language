package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Keyword extends ASTLeaf {
    public static final int to = 1;
    public static final int with = 2;
    public static final int by = 3;
    public static final int and = 4;

    public Keyword(Token t) { super(t); }
}
