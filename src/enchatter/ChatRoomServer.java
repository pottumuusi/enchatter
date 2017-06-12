package enchatter;

import java.util.ArrayDeque;

public class ChatRoomServer {
    private Configuration mConfiguration;
    private ArrayDeque<ArgToken> mAllTokens;

    public ChatRoomServer() {
        mConfiguration = new Configuration();
        mAllTokens = null;
    }

    public static void main(String[] args) throws InstantiationException {
        try {
            ChatRoomServer chatRoomServer = new ChatRoomServer();
            chatRoomServer.configure(args);
            System.out.println("Server port is: " + chatRoomServer.getPort());
        } catch (Exception e) {
            printOnError(
                    "\nError while configuring\n=======================\n",
                    e);
            throw new InstantiationException("Stopping...");
        }
    }

    public int getPort() {
        return mConfiguration.getPort();
    }

    private void configure(String[] args) throws Exception {
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

    private static void printUsage() {
        System.out.print("\nUsage\n=====\n");
        System.out.println("java enchatter.ChatRoomServer [options]");
        System.out.println("\nOptions");
        System.out.println("\t-p, --port <port number>");
        System.out.print("\n");
    }

    private static void printOnError(String errorMsg, Exception e) {
        System.err.print(errorMsg);

        if (Configuration.DEBUG)
            e.printStackTrace();

        printUsage();
    }
}
