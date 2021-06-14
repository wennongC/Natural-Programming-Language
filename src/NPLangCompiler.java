import NPLang.CodeDialog;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.NullStmnt;
import NPLang.lexer.*;
import NPLang.parser.*;

public class NPLangCompiler {

    public static void main(String[] args) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        FuncParser fp = new FuncParser(lexer);

        run(lexer, fp);
    }

    public static void run(Lexer lexer, FuncParser parser) throws ParseException {
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = parser.parse(lexer);
            Parser.updateParent(t);
            if (!(t instanceof NullStmnt)) {
                String res = t.compile();
                System.out.println("\n   === Compiled result in Python code ===   \n");
                System.out.println(res);
            }
        }
    }
}
