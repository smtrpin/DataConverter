package converter.factory.validator;

import converter.exception.AbstractValidatorException;
import converter.factory.AbstractFactory;
import converter.validator.AbstractValidator;

public abstract class AbstractValidatorFactory extends AbstractFactory{

  public AbstractValidatorFactory(String argument) {
    super(argument);
  }

  public abstract AbstractValidator configure() throws AbstractValidatorException;
}
