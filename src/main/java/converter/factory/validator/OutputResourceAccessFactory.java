package converter.factory.validator;

import converter.exception.AbstractValidatorException;
import converter.exception.UnknownTypeValidatorException;
import converter.validator.AbstractResourceAccessValidator;
import converter.validator.output.PathResourceAccessValidator;

public class OutputResourceAccessFactory extends AbstractValidatorFactory {

  private String argument;

  public OutputResourceAccessFactory(String argument) {
    super(argument);
    this.argument = argument;
  }

  @Override
  public AbstractResourceAccessValidator configure() throws AbstractValidatorException {
    if (isPath()) {
      return new PathResourceAccessValidator(argument);
    }
    throw new UnknownTypeValidatorException("Undefined type from output parameter: " + argument);
  }
}
