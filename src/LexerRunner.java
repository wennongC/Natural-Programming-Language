import NaturalProgLang.CodeDialog;
import NaturalProgLang.lexer.Lexer;
import NaturalProgLang.lexer.Token;
import NaturalProgLang.parser.ParseException;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF; ) {
            System.out.println("At line " + t.getLineNumber() + ", " + t.getTokenIdx()
                    + " => " + t.getText());
        }
    }
}