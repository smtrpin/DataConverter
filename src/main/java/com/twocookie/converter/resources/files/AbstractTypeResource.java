package com.twocookie.converter.resources.files;

import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.resources.FileResource;

import java.io.IOException;
import java.util.HashSet;

public abstract class AbstractTypeResource {

  private FileResource fileResource;

  public AbstractTypeResource(FileResource fileResource) {
    this.fileResource = fileResource;
  }

  public boolean isEmpty() {
    return fileResource.getLength() == 0;
  }

  public String toString() {
    return fileResource.toString();
  }

  public String getFileCharset() throws IOException {
    return fileResource.getFileCharset();
  }

  public abstract void isCorrectFormat() throws IOException, ValidatorException;
  public abstract HashSet<String> getDataByKey(String key) throws IOException, ValidatorException;
}
