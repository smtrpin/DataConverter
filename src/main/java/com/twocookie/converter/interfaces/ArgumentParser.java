package com.twocookie.converter.interfaces;

public interface ArgumentParser extends Parser {

  String[] parseInput(String inputText);
  String[] parseOutput(String OutputText);
}
