package NPLang.lexer;

import NPLang.parser.ParseException;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public static String regexPat
            = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<>();
    private boolean hasMore;
    private LineNumberReader reader;

    public enum ReservedTypes {
        TYPE, OPERATOR, PARTICLE
    }

    private HashMap<ReservedTypes, String[]> reserved;   // Reserved words for specific usage

    public Lexer(Reader r) {
        hasMore = true;
        reader = new LineNumberReader(r);
        reserved = new HashMap<>();
    }

    public void addNewType(ReservedTypes type, String[] typeValues) {
        reserved.put(type, typeValues);
    }

    public Token read() throws ParseException {
        if (fillQueue(0))
            return queue.remove(0);
        else
            return Token.EOF;
    }

    public Token peek(int i) throws ParseException {
        if (fillQueue(i))
            return queue.get(i);
        else
            return Token.EOF;
    }

    private boolean fillQueue(int i) throws ParseException {
        while (i >= queue.size())
            if (hasMore)
                readLine();
            else
                return false;
        return true;
    }

    protected void readLine() throws ParseException {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }
        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        int tokenIdx = 1;
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNo, tokenIdx++, matcher);
                pos = matcher.end();
            }
            else
                throw new ParseException("bad token at line " + lineNo + ", " + tokenIdx);
        }
        queue.add(new IdToken(lineNo, tokenIdx, Token.EOL));
    }

    protected void addToken(int lineNo, int tokenIdx, Matcher matcher) {
        String m = matcher.group(1);
        if (m != null) // if not a space
            if (matcher.group(2) == null) { // if not a comment
                Token token;
                if (matcher.group(3) != null)
                    token = new NumToken(lineNo, tokenIdx, Integer.parseInt(m));
                else if (matcher.group(4) != null)
                    token = new StrToken(lineNo, tokenIdx, toStringLiteral(m));
                else {
                    String[] types = reserved.get(ReservedTypes.TYPE);
                    String[] operators = reserved.get(ReservedTypes.OPERATOR);
                    String[] particles = reserved.get(ReservedTypes.PARTICLE);

                    if (Arrays.asList(types).contains(m)) {
                        token = new TypeToken(lineNo, tokenIdx, m);
                    } else if (Arrays.asList(operators).contains(m)) {
                        token = new OperatorToken(lineNo, tokenIdx, m);
                    } else if (Arrays.asList(particles).contains(m)) {
                        token = new ParticleToken(lineNo, tokenIdx, m);
                    }

                    else {
                        token = new IdToken(lineNo, tokenIdx, m);
                    }
                }
                queue.add(token);
            }
    }

    protected static class TypeToken extends Token {
        private String text;
        protected TypeToken(int line, int idx, String typeName) {
            super(line, idx);
            text = typeName;
        }
        public boolean isType() { return true; }
        public String getText() { return text; }
    }

    protected static class OperatorToken extends Token {
        private String text;
        protected OperatorToken(int line, int idx, String opName) {
            super(line, idx);
            text = opName;
        }
        public boolean isOperator() { return true; }
        public String getText() { return text; }
    }

    protected static class ParticleToken extends Token {
        private String text;
        protected ParticleToken(int line, int idx, String parName) {
            super(line, idx);
            text = parName;
        }
        public boolean isParticle() { return true; }
        public boolean isIdentifier() { return true; } // For avoiding some conflict parsing issue.
        public String getText() { return text; }
    }

    protected static class IdToken extends Token {
        private String text;
        protected IdToken(int line, int idx, String id) {
            super(line, idx);
            text = id;
        }
        public boolean isIdentifier() { return true; }
        public String getText() { return text; }
    }

    protected static class NumToken extends Token {
        private int value;

        protected NumToken(int line, int idx, int v) {
            super(line, idx);
            value = v;
        }
        public boolean isNumber() { return true; }
        public String getText() { return Integer.toString(value); }
        public int getNumber() { return value; }
    }

    protected static class StrToken extends Token {
        private String literal;
        StrToken(int line, int idx, String str) {
            super(line, idx);
            literal = str;
        }
        public boolean isString() { return true; }
        public String getText() { return literal; }
    }

    protected String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                char c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\')
                    c = s.charAt(++i);
                else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
