package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Operator extends ASTLeaf {
    public static final String add = "add";
    public static final String subtract = "subtract";
    public static final String minus = "minus";
    public static final String multiply = "multiply";
    public static final String divide = "divide";

    public Operator(Token t) { super(t); }
    public String name() { return token().getText(); }
}
