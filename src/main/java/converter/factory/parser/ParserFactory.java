package converter.factory.parser;

import converter.exception.AbstractParserException;
import converter.exception.DataParserException;
import converter.interfaces.ParserInterface;
import converter.parser.AbstractParser;
import converter.parser.FileParser;

public class ParserFactory extends AbstractParserFactory implements ParserInterface {

  private String argument;

  public ParserFactory(String argument) {
    super(argument);
    this.argument = argument;
  }

  @Override
  public AbstractParser configure() throws AbstractParserException {
    if (isFile()) {
      return new FileParser(argument);
    }
    throw new DataParserException("Undefined type from input parameter: " + argument);
  }
}
