package enchatter;

public interface IArgumentMatcher {
    // OptionValueAmount 0 means that no values are expected for this option
    int getOptionValueAmount();
    boolean isMyOption(String commandLineArgument);
    ArgToken.TokenType getType();
}
