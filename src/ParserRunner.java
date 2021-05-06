import NPLang.CodeDialog;
import NPLang.ast.basic.ASTree;
import NPLang.lexer.Lexer;
import NPLang.lexer.Token;
import NPLang.parser.BasicParser;
import NPLang.parser.ParseException;

public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser bp = new BasicParser(l);
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }
}