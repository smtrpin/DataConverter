package converter.factory;

import converter.resource.file.ResourceFile;
import converter.resource.file.ResourcePath;

public class AbstractFactory {

  private String argument;

  public AbstractFactory(String argument) {
    this.argument = argument;
  }

  protected boolean isFile() {
    return ResourceFile.isFile(argument);
  }

  protected boolean isPath() {
    return ResourcePath.isDirectory(argument);
  }
}
