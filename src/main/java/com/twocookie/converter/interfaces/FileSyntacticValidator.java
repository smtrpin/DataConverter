package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.resources.files.AbstractTypeResource;

import java.io.IOException;

public interface FileSyntacticValidator extends Validator {

  void isEmpty(AbstractTypeResource abstractTypeResource) throws ValidatorException;
  void isCorrectFormat(AbstractTypeResource abstractTypeResource) throws ValidatorException, IOException;
  void isCorrectCharset(AbstractTypeResource abstractTypeResource) throws ValidatorException, IOException;
}
