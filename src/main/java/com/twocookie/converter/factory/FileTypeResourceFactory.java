package com.twocookie.converter.factory;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ExtensionFileException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.resources.files.AbstractTypeResource;
import com.twocookie.converter.resources.files.JSONTypeResource;
import com.twocookie.converter.resources.files.PropertiesTypeResource;

public class FileTypeResourceFactory implements Factory {

  private FileResource fileResource;

  public FileTypeResourceFactory(FileResource fileResource) {
    this.fileResource = fileResource;
  }

  @Override
  public AbstractTypeResource create() throws ArgumentException {
    String fileExtension = fileResource.getFileExtension();
    if (fileExtension.equalsIgnoreCase("properties")) {
      return new PropertiesTypeResource(fileResource);
    } else if (fileExtension.equalsIgnoreCase("json")) {
      return new JSONTypeResource(fileResource);
    }
    throw new ExtensionFileException("Undefined file extension from " + fileResource.toString());
  }

}
