import NPLang.CodeDialog;
import NPLang.lexer.Lexer;
import NPLang.lexer.Token;
import NPLang.parser.ParseException;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF; ) {
            System.out.println("At line " + t.getLineNumber() + ", " + t.getTokenIdx()
                    + " => " + t.getText());
        }
    }
}