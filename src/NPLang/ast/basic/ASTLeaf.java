package NPLang.ast.basic;

import NPLang.NPLangException;
import NPLang.lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<>();
    protected Token token;
    public ASTLeaf(Token t) {
        token = t;
        parent = null;
    }

    public ASTree child(int i) { throw new IndexOutOfBoundsException(); }
    public int numChildren() { return 0; }
    public Iterator<ASTree> children() { return empty.iterator(); }

    public String toString() { return token.getText(); }
    public String location() { return "at line " + token.getLineNumber(); }
    public Token token() { return token; }

    public String compile() { throw new NPLangException("Error: compile() invoked by ASTLeaf.", this); }
    public String getCompiledCode() { throw new NPLangException("Error: getCompileCode() invoked by ASTLeaf.", this); }
}
