package com.twocookie.converter.factory;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ArgumentParserException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.interfaces.Parser;
import com.twocookie.converter.parser.argument.ConsoleParser;

public class ArgumentFactory implements Factory {

  private String[] args;

  public ArgumentFactory(String[] args) {
    this.args = args.clone();
  }

  @Override
  public Parser<?> create() throws ArgumentException {
    if (isConsoleParser()) {
      return new ConsoleParser(args);
    }
    throw new ArgumentParserException("Undefined type arguments");
  }

  private boolean isConsoleParser() {
    return args[0].contains("Input:") && args[1].contains("Output:");
  }
}
