package com.twocookie.converter.factory;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.interfaces.Validator;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.validator.FileResourceSyntacticValidator;

public class SyntacticValidatorFactory implements Factory {

  private AbstractResource abstractResource;

  public SyntacticValidatorFactory(AbstractResource abstractResource) {
    this.abstractResource = abstractResource;
  }

  @Override
  public Validator create() throws ArgumentException {

    if (abstractResource instanceof FileResource) {
      return new FileResourceSyntacticValidator((FileResource) abstractResource);
    }
    throw new ArgumentException("Undefined type from " + abstractResource.toString());
  }
}
