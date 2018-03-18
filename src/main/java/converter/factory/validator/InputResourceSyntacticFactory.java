package converter.factory.validator;

import converter.exception.AbstractValidatorException;
import converter.exception.UnknownTypeValidatorException;
import converter.validator.AbstractResourceSyntacticValidator;
import converter.validator.input.FileResourceSyntacticValidator;

public class InputResourceSyntacticFactory extends AbstractValidatorFactory {
  private String argument;

  public InputResourceSyntacticFactory(String argument) {
    super(argument);
    this.argument = argument;
  }

  public AbstractResourceSyntacticValidator configure() throws AbstractValidatorException {
    if (isFile()) {
      return new FileResourceSyntacticValidator(argument);
    }
    throw new UnknownTypeValidatorException("Undefined type from input parameter: " + argument);
  }

}
