package converter.factory.generator;

import converter.exception.AbstractGeneratorException;
import converter.exception.UnknownTypeGeneratorException;
import converter.generator.HtmlGenerator;
import converter.interfaces.GeneratorInterface;

import java.util.HashMap;

public class GeneratorFactory {

  private String argument;
  private HashMap generatorMap;

  public GeneratorFactory(String argument, HashMap generatorMap) {
    this.argument = argument;
    this.generatorMap = generatorMap;
  }

  public GeneratorInterface configure() throws AbstractGeneratorException {
    if (argument.toUpperCase().equals("HTML")) {
      return new HtmlGenerator(generatorMap);
    }
    throw new UnknownTypeGeneratorException("Generator from type " + argument + " not found");
  }
}
