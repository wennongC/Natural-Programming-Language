package NPLang.ast.basic;

import NPLang.NPLangException;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree {
    protected List<ASTree> children;
    public String compiled_code;
    public Boolean compiled_flag = false;

    public ASTList(List<ASTree> list) {
        children = list;
        compiled_code = "";
        parent = null;
    }

    public ASTree child(int i) { return children.get(i); }
    public int numChildren() { return children.size(); }
    public Iterator<ASTree> children() { return children.iterator(); }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sep = "";
        for (ASTree t: children) {
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }

    public String location() {
        for (ASTree t: children) {
            String s = t.location();
            if (s != null)
                return s;
        }
        return null;
    }

    public String compile() { throw new NPLangException("Error: Compile invoked by ASTList base class.", this); }
    public String getCompiledCode() {
        if (compiled_flag) return compiled_code;
        else return compile();
    }
    public void do_not_display() { compiled_code = ""; }
}