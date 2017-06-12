package enchatter;

import java.util.ArrayDeque;

public abstract class ArgToken {
    protected ArrayDeque<String> mValues;
    private TokenType mType;

    public enum TokenType {
        PORT,
        DEBUG,
    };

    public static final TokenType[] allTokenTypes = {
        TokenType.PORT,
    };

    protected ArgToken(TokenType type) {
        mType = type;
    }

    protected ArgToken(TokenType type, ArrayDeque<String> values) {
        mValues = values;
        mType = type;
    }

    public static ArgToken makeToken(TokenType type)
        throws TypeNotPresentException, InstantiationException {

        return makeToken(type, new ArrayDeque<String>());
    }

    public static ArgToken makeToken(TokenType type, ArrayDeque<String> values)
        throws TypeNotPresentException, InstantiationException {

        ArgToken newToken;

        switch (type) {
            case PORT:
                newToken = new PortToken(values);
                break;
            default:
                throw new IllegalArgumentException(
				"No support for making Token  of type ("
				+ type + ")");
        }

        return newToken;
    }

    public TokenType getType() {
        return mType;
    }

    public ArrayDeque<String> getValues() {
        return new ArrayDeque<String>(mValues);
    }

    public void addValue(String value) {
        mValues.push(value);
    }
}
