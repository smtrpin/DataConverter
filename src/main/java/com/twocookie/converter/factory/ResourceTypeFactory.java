package com.twocookie.converter.factory;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ArgumentParserException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.resources.PathResource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceTypeFactory implements Factory {
  private String resource;

  public ResourceTypeFactory(String resource) {
    this.resource = resource;
  }

  @Override
  public AbstractResource create() throws ArgumentException {
    if (isFile()) {
      return new FileResource(new File(resource));
    } else if (isPath()) {
      return new PathResource(Paths.get(resource));
    }
    throw new ArgumentParserException("Undefined type from " + resource);
  }

  private boolean isFile() {
    return Files.isRegularFile(Paths.get(resource));
  }

  private boolean isPath() {
    return Files.isDirectory(Paths.get(resource));
  }
}
