package com.twocookie.converter.validator.argument;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ArgumentValidatorException;

public class ArgumentValidator {

  private String[] args;
  private final Short MIN_COUNT_ARGUMENTS = 2;

  public ArgumentValidator(String[] args) {
    this.args = args.clone();
  }

  public void argumentValidate() throws ArgumentException {
    if (!isCorrectCountArgument()) {
      throw new ArgumentValidatorException("Incorrect number of arguments. Necessary number of arguments: " + MIN_COUNT_ARGUMENTS);
    }
  }

  private boolean isCorrectCountArgument() {
    return args.length == MIN_COUNT_ARGUMENTS;
  }


}
