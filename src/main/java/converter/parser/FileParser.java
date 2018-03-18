package converter.parser;

import converter.exception.AbstractParserException;
import converter.exception.AbstractValidatorException;
import converter.exception.DataParserException;
import converter.factory.file.TypeFileFactory;
import converter.resource.file.ResourceFile;
import converter.resource.file.type.AbstractResourceTypeFile;

import java.io.IOException;

public class FileParser extends AbstractParser {

  private String argument;

  public FileParser(String argument) {
    this.argument = argument;
  }

  @Override
  public void parse() throws AbstractParserException, AbstractValidatorException, IOException {
    String fileExtension = getFileExtension();
    isFileExtension(fileExtension);
    AbstractResourceTypeFile abstractResourceTypeFile = createFileTypeFactory(fileExtension);
    abstractResourceTypeFile.parse(argument);
  }

  private AbstractResourceTypeFile createFileTypeFactory(String filExtension) throws AbstractParserException {
    TypeFileFactory typeFileFactory = new TypeFileFactory(filExtension);
    return typeFileFactory.configure();
  }

  private String getFileExtension() {
    return ResourceFile.getFileExtension(argument);
  }

  private void isFileExtension(String extension) throws AbstractParserException {
    if (extension == null) {
      throw new DataParserException("Extension from file: \"" + argument + "\" is null");
    }
  }
}
