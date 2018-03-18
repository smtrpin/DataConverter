package converter.interfaces;

import converter.exception.AbstractParserException;
import converter.parser.AbstractParser;

public interface ParserInterface {

  AbstractParser configure() throws AbstractParserException;
}
