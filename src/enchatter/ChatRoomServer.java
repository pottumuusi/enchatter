package enchatter;

import java.util.ArrayDeque;

public class ChatRoomServer {
    private Configuration mConfiguration;
    private ArrayDeque<ArgToken> mAllTokens;

    public ChatRoomServer() {
        mConfiguration = new Configuration();
        mAllTokens = null;
    }

    public int getPort() {
        return mConfiguration.getPort();
    }

    public void configure(String[] args) throws Exception {
        if (mConfiguration.isFrozen())
            throw new IllegalStateException(
                    "Configuration is frozen. Can't configure.");

        makeAllConfigurationTokens(args);
        loadConfiguration();
    }

    private void makeAllConfigurationTokens(String[] args) throws Exception {
        if (args.length > 0)
            mAllTokens = ArgParser.tokenizeProgramArguments(args);

        // TODO reading config from file
    }

    private void loadConfiguration() throws Exception {
        mConfiguration.setDefaultConfiguration();

        if (null != mAllTokens) {
            mConfiguration.preloadConfiguration(mAllTokens);
            mConfiguration.loadPreloadedConfiguration();
            mConfiguration.postLoadClean();
        }

        mConfiguration.checkValidity();
        mConfiguration.freeze();
    }
}
