package converter.argument;

import converter.ApplicationContext;
import converter.exception.AbstractParserException;
import converter.exception.IncorrectArgumentsException;
import converter.interfaces.ArgumentParserInterface;

public class ConsoleArgumentParser implements ArgumentParserInterface {

  private String[] args;
  private String input;
  private String output;
  private String mode;

  public ConsoleArgumentParser(String[] args) {
    this.args = args.clone();
  }

  @Override
  public void parseArgument() throws AbstractParserException {
    replaceArgumentOnPointer();
    isCorrectArgumentParameters();
    addParseResult();
  }

  private void addParseResult() {
    addInputParameters();
    addOutputParameters();
  }

  private void addInputParameters() {
    String[] inputParameters = splitArguments(input);
    ApplicationContext.getInstance().addInput(inputParameters);
  }

  private void addOutputParameters() {
    String[] outputParameters = splitArguments(output);
    String[] modeParameters = splitArguments(mode);
    for (int i = 0; i < modeParameters.length; i++) {
      ApplicationContext.getInstance().addOutput(modeParameters[i], outputParameters[i]);
    }
  }

  private void replaceArgumentOnPointer() {
    input = args[0].replace("Input:", "");
    output = args[1].replace("Output:", "");
    mode = args[2].replace("Mode:", "");
  }

  private void isCorrectArgumentParameters() throws AbstractParserException {
    if (isEmptyArguments(input) || isEmptyArguments(output) || isEmptyArguments(mode) || !isCorrectMode()) {
      throw new IncorrectArgumentsException("Incorrect argument parameters");
    }
  }

  private boolean isEmptyArguments(String args) {
    int argsLength = getCountArguments(args);
    return argsLength == 0;
  }

  private boolean isCorrectMode() {
    int modeLength = getCountArguments(mode);
    int outputLength = getCountArguments(output);
    return outputLength >= modeLength;
  }

  private int getCountArguments(String args) {
    String[] splitArgs = splitArguments(args);
    return splitArgs.length;
  }

  private String[] splitArguments(String args) {
    return args.split(";");
  }
}
