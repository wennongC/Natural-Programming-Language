package NPLang;

import NPLang.ast.basic.ASTree;
import NPLang.lexer.Token;

import java.io.IOException;

public class NPLangException extends RuntimeException {
    public NPLangException(String m) {super(m); }

    public NPLangException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
