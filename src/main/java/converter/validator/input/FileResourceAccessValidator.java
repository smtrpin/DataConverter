package converter.validator.input;

import converter.exception.AbstractValidatorException;
import converter.exception.ResourceAccessException;
import converter.resource.file.ResourceFile;
import converter.validator.AbstractResourceAccessValidator;

public class FileResourceAccessValidator extends AbstractResourceAccessValidator {

  private String argument;

  public FileResourceAccessValidator(String argument) {
    this.argument = argument;
  }

  @Override
  public void validate() throws AbstractValidatorException {
    isReadableFile();
  }

  private void isReadableFile() throws AbstractValidatorException {
    if (!ResourceFile.isReadable(argument)) {
      throw new ResourceAccessException("File: \"" + argument + "\" is not readable");
    }
  }
}
