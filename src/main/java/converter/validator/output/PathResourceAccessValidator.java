package converter.validator.output;

import converter.exception.AbstractValidatorException;
import converter.exception.ResourceAccessException;
import converter.resource.file.ResourcePath;
import converter.validator.AbstractResourceAccessValidator;

public class PathResourceAccessValidator extends AbstractResourceAccessValidator {

  private String argument;

  public PathResourceAccessValidator(String argument) {
    this.argument = argument;
  }

  @Override
  public void validate() throws AbstractValidatorException {
    isWritable();
  }

  private void isWritable() throws AbstractValidatorException {
    if (!ResourcePath.isWritable(argument)) {
      throw new ResourceAccessException("Directory is not writable");
    }
  }
}
