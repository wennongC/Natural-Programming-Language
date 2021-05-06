package NPLang.ast;

import NPLang.ast.basic.ASTList;
import NPLang.ast.basic.ASTree;

import java.util.List;

public class Pronoun extends ASTList {
    public static final int it = 1;
    public static final int them = 2;

    public Pronoun(List<ASTree> c) { super(c); }
}
