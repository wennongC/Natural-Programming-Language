package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Type extends ASTLeaf {
    public static final String integer = "integer";
    public static final String string = "string";

    public Type(Token t) { super(t); }
    public String name() { return token().getText(); }
}
