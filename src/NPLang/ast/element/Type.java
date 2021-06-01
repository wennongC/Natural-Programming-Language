package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Type extends ASTLeaf {
    public static final String t_integer = "integer";
    public static final String t_string = "string";
    public static final String t_boolean = "boolean";

    public static final String t_list = "list";
    public static final String t_tuple = "tuple";

    public Type(Token t) { super(t); }
    public String name() { return token().getText(); }
}
