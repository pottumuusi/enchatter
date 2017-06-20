package enchatter;

import java.util.ArrayDeque;

public abstract class ArgParser {
    private static int mUpcomingValuesLeft = -1;
    private static ArrayDeque<ArgToken> mAllTokens = new ArrayDeque<ArgToken>();

    private static final String NULL_ARG_NOT_ACCEPTED =
        "Arg must not be null";

    private static final String NULL_TOKEN_NOT_ACCEPTED =
        "Token must not be null when it is intended to be used";

    private static final IArgumentMatcher[] allTokenInstances = {
        new PortToken(),
    };

    public static ArrayDeque<ArgToken> tokenizeProgramArguments(String[] args)
        throws IllegalArgumentException, InstantiationException {

        ArgToken token = null;

        for (String arg : args) {
            // arg is either option or value

            if (expectingOption()) {
                token = makeMatchedTokenFromOption(arg);
                arg = null;
                continue;
            }

            if (expectingValue())
                addValueToToken(token, arg);

            if (valuesCollectedForCurrentToken()) {
                readyTokenToTokenCollection(token);
                token = null;
            }
        }

        return new ArrayDeque<ArgToken>(mAllTokens);
    }

    private static ArgToken makeMatchedTokenFromOption(String arg) throws
        IllegalArgumentException,
        TypeNotPresentException,
        InstantiationException {

        if (null == arg)
            throw new IllegalStateException(NULL_ARG_NOT_ACCEPTED);

        ArgToken token = null;

        for (IArgumentMatcher matcher : allTokenInstances) {
            if (matcher.isMyOption(arg)) {
                ArgToken.TokenType tokenType = matcher.getType();
                mUpcomingValuesLeft = matcher.getOptionValueAmount();
                token = ArgToken.makeToken(tokenType);
            }
        }

        if (null == token)
            throw new IllegalArgumentException(
                    "Unrecognized program argument: " + arg);


        return token;
    }

    private static void addValueToToken(ArgToken token, String arg) {
        if (null == token)
            throw new IllegalStateException(NULL_TOKEN_NOT_ACCEPTED);

        if (null == arg)
            throw new IllegalStateException(NULL_ARG_NOT_ACCEPTED);

        token.addValue(arg);
        mUpcomingValuesLeft--;
    }

    private static void readyTokenToTokenCollection(ArgToken token) {
        if (null == token)
            throw new IllegalStateException(NULL_TOKEN_NOT_ACCEPTED);

        mUpcomingValuesLeft--;
        mAllTokens.push(token);
    }

    private static boolean expectingValue() {
        return mUpcomingValuesLeft > 0;
    }

    private static boolean valuesCollectedForCurrentToken() {
        return 0 == mUpcomingValuesLeft;
    }

    private static boolean expectingOption() {
        return -1 == mUpcomingValuesLeft;
    }
}
