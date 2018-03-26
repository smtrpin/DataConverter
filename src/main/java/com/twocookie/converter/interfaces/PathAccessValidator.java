package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ValidatorException;

public interface PathAccessValidator extends Validator {

  void isWritable() throws ValidatorException;
}
