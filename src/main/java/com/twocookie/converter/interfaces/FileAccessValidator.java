package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ValidatorException;

public interface FileAccessValidator extends Validator {

  void isReadable() throws ValidatorException;
}
