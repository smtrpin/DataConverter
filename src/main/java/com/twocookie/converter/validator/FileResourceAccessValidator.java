package com.twocookie.converter.validator;

import com.twocookie.converter.exception.AccessValidatorException;
import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.interfaces.FileAccessValidator;
import com.twocookie.converter.resources.FileResource;

public class FileResourceAccessValidator implements FileAccessValidator {

  private FileResource fileResource;

  public FileResourceAccessValidator(FileResource fileResource) {
    this.fileResource = fileResource;
  }

  @Override
  public void validate() throws ValidatorException {
    isReadable();
  }

  @Override
  public void isReadable() throws ValidatorException {
    if (!fileResource.isReadable()) {
      throw new AccessValidatorException("File cannot be read");
    }
  }
}
