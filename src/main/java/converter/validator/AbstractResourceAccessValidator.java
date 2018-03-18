package converter.validator;

import converter.exception.AbstractValidatorException;

public abstract class AbstractResourceAccessValidator extends AbstractValidator {
  public abstract void validate() throws AbstractValidatorException;

}
