package NPLang.parser;

import java.util.HashSet;

import static NPLang.parser.Parser.rule;

import NPLang.ast.*;
import NPLang.ast.element.*;
import NPLang.ast.basic.ASTree;
import NPLang.lexer.*;
import NPLang.lexer.Lexer.ReservedTypes;

public class BasicParser {
    HashSet<String> reserved = new HashSet<String>();

    Parser expr0 = rule();

    // value: TYPE "value" LITERAL
    Parser valueExpr = rule(ValueExpr.class)
            .typeName(Type.class, reserved).sep("value")
            .or(
                    rule().identifier(Literal.class, reserved),
                    rule().number(Literal.class)
            );

    // pronoun: value | IDENTIFIER
    Parser pronoun = rule(Pronoun.class)
            .or(rule().ast(valueExpr), rule().identifier(Identifier.class, reserved));

    // declaration: "set a variable called" IDENTIFIER [("with" value)]
    Parser declaration = rule(Declaration.class)
            .sep("set").option(rule().sep("a")).sep("variable").option(rule().sep("called"))
            .identifier(Identifier.class, reserved)
            .option(rule().sep("with").ast(valueExpr));

    // assignment: "assign" pronoun ("to" | "with") pronoun ["as" TYPE]
    Parser assignment = rule(Assignment.class)
            .sep("assign").ast(pronoun).particle(Particle.class, reserved).ast(pronoun)
            .option(rule().sep("as").typeName(Type.class, reserved));

    // calculation: OP pronoun [("by" | "and") pronoun]
    Parser calculation = rule(Calculation.class).operator(Operator.class, reserved)
            .ast(pronoun).option(rule().particle(Particle.class, reserved).ast(pronoun));

    // statement: declaration | assignment | calculation | invoke | returnExpr
    Parser statement = rule(Statement.class).or(
            rule().ast(declaration),
            rule().ast(assignment),
            rule().ast(calculation)
    );

    // sentence: statement {"," {EOL} "then" statement}
    Parser sentence = rule(Sentence.class).ast(statement)
            .repeat(rule()
                    .sep(",").repeat(rule().sep(Token.EOL)).sep("then")
                    .ast(statement)
            );

    // program: {sentence ("." | EOL)}
    Parser program = rule().or(sentence, rule(NullStmnt.class)).sep(".", Token.EOL);

    public BasicParser(Lexer lexer) {
        reserved.add(",");
        reserved.add(".");
        reserved.add(Token.EOL);

        lexer.addNewType(ReservedTypes.TYPE, Type.reserved);
        lexer.addNewType(ReservedTypes.OPERATOR, Operator.reserved);
        lexer.addNewType(ReservedTypes.PARTICLE, Particle.reserved);
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }

}
