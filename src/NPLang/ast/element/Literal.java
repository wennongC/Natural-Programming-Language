package NPLang.ast.element;

import NPLang.ast.basic.ASTLeaf;
import NPLang.lexer.Token;

public class Literal extends ASTLeaf {
    public Literal(Token t) { super(t); }

    // For Numeric token
    public int value() {

        /*
         * To-do: translate a human-language number string into a actual numeric value
         */

        return token().getNumber();
    }

    // For Identifier token
    public String text() { return token().getText(); }
}
