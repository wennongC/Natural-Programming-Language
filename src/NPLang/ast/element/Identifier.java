package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Identifier extends ASTLeaf {
    public Identifier(Token t) { super(t); }
    public String name() { return token().getText(); }
}
