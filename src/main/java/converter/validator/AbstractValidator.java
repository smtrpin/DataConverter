package converter.validator;

import converter.exception.AbstractParserException;
import converter.exception.AbstractValidatorException;

import java.io.IOException;

public abstract class AbstractValidator {
  public abstract void validate() throws AbstractValidatorException, IOException, AbstractParserException;
}
