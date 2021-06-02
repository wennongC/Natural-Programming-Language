package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Particle extends ASTLeaf {
    public static final String to = "to";
    public static final String with = "with";
    public static final String by = "by";
    public static final String and = "and";

    public static final String[] reserved = {to, with, by, and};

    public Particle(Token t) { super(t); }

    public String text() { return token().getText(); }
}
