package NPLang.parser;

import NPLang.ast.Function;
import NPLang.ast.Invoke;
import NPLang.ast.ReturnExpr;
import NPLang.ast.element.Identifier;
import NPLang.ast.element.Literal;
import NPLang.ast.element.NullStmnt;
import NPLang.lexer.*;

import static NPLang.parser.Parser.rule;

public class FuncParser extends BasicParser {

    // invoke: (“invoke”|"call") IDENTIFIER [“with” pronoun]
    Parser invoke = rule(Invoke.class).or(rule().sep("invoke"),rule().sep("call"))
            .identifier(Identifier.class, reserved)
            .option(rule().sep("with").ast(pronoun));

    // function: “define function” IDENTIFIER ["taking" LITERAL] ("argument" | "arguments) ("." | EOL)
    //          {sentence ("." | EOL)} “end define”
    Parser function = rule(Function.class)
            .sep("define").option(rule().sep("a")).sep("function").option(rule().sep("called"))
            .identifier(Identifier.class, reserved)
            .option(rule().sep("taking", "with")
                    .or(
                        rule().identifier(Literal.class, reserved),
                        rule().number(Literal.class)
                    ).sep("argument", "arguments")).sep(".", Token.EOL)
            .repeat(rule().or(sentence, rule(NullStmnt.class)).sep(".", Token.EOL), "end")
            .sep("end").sep("define");

    // returnExpr: "return" Pronoun
    Parser returnExpr = rule(ReturnExpr.class).sep("return").ast(pronoun);

    public FuncParser(Lexer lexer) {
        super(lexer);

        statement.insertChoice(invoke);
        statement.insertChoice(returnExpr);
        statement.insertChoice(function);
    }
}
