package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;
import NPLang.ast.element.Identifier;

import java.util.List;

public class Function extends ASTList {

    public Function(List<ASTree> c) { super(c); }

    public String functionName() { return ((Identifier)child(0)).name(); }

    // function: “define function” IDENTIFIER program “end define”
    public String compile() {

        System.out.println(child(1));

        return compiled_code;
    }
}
