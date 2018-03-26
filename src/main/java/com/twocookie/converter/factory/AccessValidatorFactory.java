package com.twocookie.converter.factory;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.interfaces.Validator;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.resources.PathResource;
import com.twocookie.converter.validator.FileResourceAccessValidator;
import com.twocookie.converter.validator.PathResourceAccessValidator;

public class AccessValidatorFactory implements Factory {

  private AbstractResource abstractResource;

  public AccessValidatorFactory(AbstractResource abstractResource) {
    this.abstractResource = abstractResource;
  }

  @Override
  public Validator create() throws ArgumentException {
    if (abstractResource instanceof FileResource) {
      return new FileResourceAccessValidator((FileResource) abstractResource);
    } else if (abstractResource instanceof PathResource) {
      return new PathResourceAccessValidator((PathResource) abstractResource);
    }
    throw new ArgumentException("Undefined type from " + abstractResource.toString());
  }
}
