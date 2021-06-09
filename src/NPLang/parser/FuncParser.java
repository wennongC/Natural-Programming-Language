package NPLang.parser;

import NPLang.ast.Function;
import NPLang.ast.Invoke;
import NPLang.ast.element.Identifier;
import NPLang.lexer.*;

import static NPLang.parser.Parser.rule;

public class FuncParser extends BasicParser {

    // invoke: (“invoke”|"call") IDENTIFIER [“with” pronoun]
    Parser invoke = rule(Invoke.class).or(rule().sep("invoke"),rule().sep("call"))
            .identifier(Identifier.class, reserved)
            .option(rule().sep("with").ast(pronoun));

    // function: “define function” IDENTIFIER {sentence ("." | "." | EOL)} “end define”
    Parser function = rule(Function.class).sep("define").sep("function").identifier(Identifier.class, reserved)
            .ast(program)
            .sep("end").sep("define");

    public FuncParser(Lexer lexer) {
        super(lexer);

        statement.insertChoice(invoke);
        program.insertChoice(function);
    }
}
