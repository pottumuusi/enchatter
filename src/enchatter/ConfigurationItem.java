package enchatter;

public class ConfigurationItem<T> {
    private T mActualItem;
    private ArgToken.TokenType mTokenType;

    ConfigurationItem(ArgToken.TokenType type, T item) {
        mActualItem = item;
        mTokenType = type;
    }

    public T getActualItem() {
        return mActualItem;
    }

    public ArgToken.TokenType getTokenType() {
        return mTokenType;
    }
}
