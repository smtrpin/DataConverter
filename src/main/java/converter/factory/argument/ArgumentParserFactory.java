package converter.factory.argument;

import converter.argument.ConsoleArgumentParser;
import converter.exception.UnknownTypeParserException;
import converter.interfaces.ArgumentParserInterface;

public class ArgumentParserFactory {
  String[] args;

  public ArgumentParserFactory(String[] args) {
    this.args = args.clone();
  }

  public ArgumentParserInterface configure() throws UnknownTypeParserException {
    if (isConsoleParser()) {
      return new ConsoleArgumentParser(args);
    }
    throw new UnknownTypeParserException("Type parser not found");
  }

  private boolean isConsoleParser() {
    return findPhraseInText("Input:", args[0]) && findPhraseInText("Output:", args[1]) && findPhraseInText("Mode:", args[2]);
  }

  private boolean findPhraseInText(String phrase, String text) {
    return text.contains(phrase);
  }
}
