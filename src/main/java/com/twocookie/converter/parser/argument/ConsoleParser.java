package com.twocookie.converter.parser.argument;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.factory.ResourceTypeFactory;
import com.twocookie.converter.interfaces.ArgumentParser;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.storage.ArgumentStorage;
import com.twocookie.converter.storage.InputStorage;
import com.twocookie.converter.storage.OutputStorage;

import java.util.HashSet;
import java.util.Set;

public class ConsoleParser implements ArgumentParser {

  private String[] args;

  public ConsoleParser(String[] args) {
    this.args = args.clone();
  }

  public ArgumentStorage parse() throws ArgumentException {
    String[] inputs = parseInput(args[0]);
    String[] outputs = parseOutput(args[1]);
    Set<AbstractResource> inputCollection = setInput(inputs);
    Set<AbstractResource> outputCollection = setOutput(outputs);
    ArgumentStorage argumentStorage = new ArgumentStorage();
    argumentStorage.setInputStorage(new InputStorage(inputCollection));
    argumentStorage.setOutputStorage(new OutputStorage(outputCollection));
    return argumentStorage;
  }

  @Override
  public String[] parseInput(String inputText) {
    String replacedText = replaceText(inputText, "", "Input:", "\"");
    return explodeArguments(replacedText, ";");
  }

  @Override
  public String[] parseOutput(String outputText) {
    String replacedText = replaceText(outputText, "", "Output:", "\"");
    return explodeArguments(replacedText, ";");
  }

  private String[] explodeArguments(String argument, String symbol) {
    return argument.split(symbol);
  }

  private String replaceText(String text, String newSymbol, String... oldSymbol) {
    for (String symbol : oldSymbol) {
      text = replaceText(text, newSymbol, symbol);
    }
    return text;
  }

  private String replaceText(String text, String newSymbol, String oldSymbol) {
    return text.replace(oldSymbol, newSymbol);
  }

  private Set<AbstractResource> setInput(String[] inputs) throws ArgumentException {
    Set<AbstractResource> abstractResources = new HashSet<>();
    for (String input : inputs) {
      abstractResources.add(createResourceFactory(input));
    }
    return abstractResources;
  }

  private Set<AbstractResource> setOutput(String[] outputs) throws ArgumentException {
    Set<AbstractResource> abstractResources = new HashSet<>();
    for (String output : outputs) {
      abstractResources.add(createResourceFactory(output));
    }
    return abstractResources;
  }

  private AbstractResource createResourceFactory(String resource) throws ArgumentException {
    ResourceTypeFactory resourceTypeFactory = new ResourceTypeFactory(resource);
    return resourceTypeFactory.create();
  }
}
