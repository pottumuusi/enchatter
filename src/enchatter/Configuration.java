package enchatter;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Configuration {
    private Integer mPort;
    private boolean mFrozen;
    private HashMap<ArgToken.TokenType, Object> mAllConfigurationItems;

    public static final int MIN_PORT_VALUE = 0;
    public static final int MAX_PORT_VALUE = 49151;
    public static final int DEFAULT_PORT_VALUE = 7000;

    // TODO make a real "debug" program argument
    public static final boolean DEBUG = true;

    public Configuration() {
        mFrozen = false;
        mPort = -1;
        mAllConfigurationItems = new HashMap<ArgToken.TokenType, Object>();
    }

    public void setDefaultConfiguration() {
        mPort = DEFAULT_PORT_VALUE;
    }

    public void preloadConfiguration(ArrayDeque<ArgToken> tokens) {
        ConfigurationItem item;
        IConfigurationMaker maker;

        if (mFrozen)
            return;

        for (int i = 0; i < tokens.size(); i++) {
            maker = (IConfigurationMaker)tokens.pop();
            item = maker.makeConfigurationItem();

            mAllConfigurationItems.put(
                    (ArgToken.TokenType) item.getTokenType(),
                    item.getActualItem());
        }
    }

    public void loadPreloadedConfiguration() {
        if (mFrozen)
            return;

        mPort = (Integer) tryToLoad(ArgToken.TokenType.PORT, mPort);
    }

    private Object tryToLoad(ArgToken.TokenType type, Object original) {
        Object preloaded = mAllConfigurationItems.get(type);
        return (null != preloaded) ? preloaded : original;
    }

    public void checkValidity() throws IllegalStateException {
        if (mPort < MIN_PORT_VALUE || mPort > MAX_PORT_VALUE)
            throw new IllegalStateException("Invalid port number: " + mPort);
    }

    public void postLoadClean() {
        mAllConfigurationItems.clear();
        mAllConfigurationItems = null;
    }

    public int getPort() {
        return mPort.intValue();
    }

    public void freeze() {
        mFrozen = true;
    }

    public void unfreeze() {
        mFrozen = false;
    }

    public boolean isFrozen() {
        return mFrozen;
    }
}
