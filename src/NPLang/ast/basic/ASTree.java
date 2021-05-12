package NPLang.ast.basic;

import java.util.Iterator;

public abstract class ASTree implements Iterable<ASTree> {
    protected ASTList parent;

    public void setParent(ASTList parent) { this.parent = parent; }
    public ASTList parent() { return parent; };
    public abstract ASTree child(int i);
    public abstract int numChildren();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public Iterator<ASTree> iterator() { return children(); }
    public abstract String compile();
}
