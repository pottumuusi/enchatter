package enchatter;

import java.util.ArrayDeque;

public class PortToken
    extends ArgToken
    implements IArgumentMatcher, IConfigurationMaker {

    private static final String OPTION_IDENTIFIER_LONG = "--port";
    private static final String OPTION_IDENTIFIER_SHORT = "-p";
    private static final int OPTION_VALUE_AMOUNT = 1;

    PortToken() {
        super(ArgToken.TokenType.PORT, new ArrayDeque<String>());
    }

    PortToken(ArrayDeque<String> values) throws InstantiationException {
        super(ArgToken.TokenType.PORT, values);

        if (null == values)
            throw new InstantiationException("Null array provided");
    }

    public int getOptionValueAmount() {
        return OPTION_VALUE_AMOUNT;
    }

    public boolean isMyOption(String arg) {
        return
            arg.equals(PortToken.OPTION_IDENTIFIER_LONG) ||
            arg.equals(PortToken.OPTION_IDENTIFIER_SHORT);
    }

    public ConfigurationItem makeConfigurationItem() {
        return new ConfigurationItem<Integer>(
                getType(),
                Integer.parseInt(mValues.getFirst()));
    }
}
