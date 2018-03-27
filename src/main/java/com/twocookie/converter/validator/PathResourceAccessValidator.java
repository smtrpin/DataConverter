package com.twocookie.converter.validator;

import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.interfaces.PathAccessValidator;
import com.twocookie.converter.resources.PathResource;

public class PathResourceAccessValidator implements PathAccessValidator {

  private PathResource pathResource;

  public PathResourceAccessValidator(PathResource pathResource) {
    this.pathResource = pathResource;
  }

  @Override
  public void validate() throws ValidatorException {
    isWritable();
  }

  @Override
  public void isWritable() throws ValidatorException {
    if (!pathResource.isWritable()) {
      throw new ValidatorException("Directory " + pathResource.toString() + " is not writable");
    }
  }
}
