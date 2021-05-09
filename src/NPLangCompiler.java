import NPLang.CodeDialog;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.NullStmnt;
import NPLang.lexer.*;
import NPLang.parser.*;

public class NPLangCompiler {

    public static void main(String[] args) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        BasicParser bp = new BasicParser(lexer);

        run(lexer, bp);
    }

    public static void run(Lexer lexer, BasicParser bp) throws ParseException {
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                String res = t.compile();
                System.out.println("\n   === Compiled result in Python code ===   \n");
                System.out.println(res);
            }
        }
    }
}
