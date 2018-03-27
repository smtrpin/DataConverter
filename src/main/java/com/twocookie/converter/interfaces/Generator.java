package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.GeneratorException;

import java.io.IOException;

public interface Generator {

  void generate() throws IOException, GeneratorException;
  String toString();
}
