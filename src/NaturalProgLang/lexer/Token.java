package NaturalProgLang.lexer;

import NaturalProgLang.NPLangException;

public abstract class Token {
    public static final Token EOF = new Token(-1, 0){};    // end of file
    public static final String EOL = "\\n";                 // end of line
    private int lineNumber;
    private int tokenIdx; // The n-th token on that line.

    protected Token(int line, int idx) {
        lineNumber = line;
        tokenIdx = idx;
    }
    public int getLineNumber() { return lineNumber; }
    public int getTokenIdx() { return tokenIdx; }

    public boolean isIdentifier() { return false; }
    public boolean isNumber() { return false; }
    public boolean isString() { return false; }
    public int getNumber() { throw new NPLangException("not number token"); }
    public String getText() { return ""; }
}
