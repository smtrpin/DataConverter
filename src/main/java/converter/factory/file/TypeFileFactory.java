package converter.factory.file;

import converter.exception.AbstractParserException;
import converter.exception.UnknownTypeParserException;
import converter.resource.file.type.AbstractResourceTypeFile;
import converter.resource.file.type.JSONTypeResource;
import converter.resource.file.type.PropertiesTypeResource;

public class TypeFileFactory {

  private String extension;

  public TypeFileFactory(String extension) {
    this.extension = extension;
  }

  public AbstractResourceTypeFile configure() throws AbstractParserException {
    if (extension.equals("properties")) {
      return new PropertiesTypeResource();
    } else if (extension.equals("json")) {
      return new JSONTypeResource();
    }
    throw new UnknownTypeParserException("Type from " + extension + " not found");
  }
}
