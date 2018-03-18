package converter.parser;

import converter.exception.AbstractParserException;
import converter.exception.AbstractValidatorException;

import java.io.IOException;

public abstract class AbstractParser {

  public abstract void parse() throws AbstractParserException, AbstractValidatorException, IOException;
}
