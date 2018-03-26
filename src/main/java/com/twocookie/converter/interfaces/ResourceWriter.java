package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.WriterException;
import com.twocookie.converter.generator.HTMLGenerator;

import java.io.IOException;
import java.util.HashSet;

public interface ResourceWriter {

  void write(HashSet<HTMLGenerator> generator) throws WriterException, IOException;
}
