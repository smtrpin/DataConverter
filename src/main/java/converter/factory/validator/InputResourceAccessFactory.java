package converter.factory.validator;

import converter.exception.AbstractValidatorException;
import converter.exception.UnknownTypeValidatorException;
import converter.validator.AbstractResourceAccessValidator;
import converter.validator.input.FileResourceAccessValidator;

public class InputResourceAccessFactory extends AbstractValidatorFactory {
  private String argument;

  public InputResourceAccessFactory(String argument) {
    super(argument);
    this.argument = argument;
  }

  public AbstractResourceAccessValidator configure() throws AbstractValidatorException {
    if (isFile()) {
      return new FileResourceAccessValidator(argument);
    }
    throw new UnknownTypeValidatorException("Undefined type from input parameter: " + argument);
  }
}
