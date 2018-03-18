package converter.validator.input;

import converter.exception.AbstractParserException;
import converter.exception.AbstractValidatorException;
import converter.exception.ResourceSyntacticException;
import converter.factory.file.TypeFileFactory;
import converter.resource.file.ResourceFile;
import converter.resource.file.type.AbstractResourceTypeFile;
import converter.validator.AbstractResourceSyntacticValidator;

import java.io.IOException;

public class FileResourceSyntacticValidator extends AbstractResourceSyntacticValidator {

  private String argument;

  public FileResourceSyntacticValidator(String argument) {
    this.argument = argument;
  }

  @Override
  public void validate() throws AbstractValidatorException, IOException, AbstractParserException {
    String fileExtension = getFileExtension();
    isFileExtension(fileExtension);
    AbstractResourceTypeFile abstractResourceTypeFile = createFileTypeFactory(fileExtension);
    abstractResourceTypeFile.validate(argument);
  }

  private AbstractResourceTypeFile createFileTypeFactory(String filExtension) throws AbstractParserException {
    TypeFileFactory typeFileFactory = new TypeFileFactory(filExtension);
    return typeFileFactory.configure();
  }

  private String getFileExtension() {
    return ResourceFile.getFileExtension(argument);
  }

  private void isFileExtension(String extension) throws AbstractValidatorException {
    if (extension == null) {
      throw new ResourceSyntacticException("Extension from file: \"" + argument + "\" is null");
    }
  }
}
