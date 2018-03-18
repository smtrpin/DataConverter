package converter.interfaces;

import converter.exception.AbstractValidatorException;

public interface LineParser {
  void parse(String line) throws AbstractValidatorException;
}
