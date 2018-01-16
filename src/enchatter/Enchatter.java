package enchatter;

public class Enchatter {
    private static ChatRoomServer mChatRoomServer;
    private static ChatClient mChatClient;

    private final static boolean IS_CLIENT = true;
    private final static boolean IS_SERVER = false;

    public static void main(String[] args) throws InstantiationException {
        try {
            setup(args);
        } catch (Exception e) {
            printOnError(
                    "\nError while setting up\n=======================\n",
                    e);
            throw new InstantiationException("Stopping...");
        }

        run();
    }

    private static void setup(String[] args) throws Exception {
        if (IS_CLIENT && IS_SERVER)
            throw new InstantiationException
                ("Enchatter can't be client and server at the same time");

        if (!(IS_CLIENT || IS_SERVER))
            throw new InstantiationException
                ("Enchatter needs to be defined either as client or server");

        if (IS_SERVER)
            mChatRoomServer = new ChatRoomServer();

        if (IS_CLIENT)
            mChatClient = new ChatClient();

        configure(args);
    }

    private static void configure(String[] args) throws Exception {
        ChatRoomServer server = mChatRoomServer;
        ChatClient client = mChatClient;

        if (null == server && null == client)
            throw new InstantiationException("Nothing to configure");

        if (null != server) {
            server.configure(args);
            System.out.println("Server port is: " + server.getPort());
        }

        if (null != client) {
            client.configure(args);
            System.out.println("Client port is: " + client.getPort());
        }
    }

    private static void run() {
    }

    private static void printOnError(String errorMsg, Exception e) {
        System.err.print(errorMsg);

        if (Configuration.DEBUG)
            e.printStackTrace();

        printUsage();
    }

    private static void printUsage() {
        System.out.print("\nUsage\n=====\n");
        System.out.println("java enchatter.ChatRoomServer [options]");
        System.out.println("\nOptions");
        System.out.println("\t-p, --port <port number>");
        System.out.print("\n");
    }
}
