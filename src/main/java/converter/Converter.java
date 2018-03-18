package converter;

import converter.exception.*;
import converter.factory.argument.ArgumentParserFactory;
import converter.factory.generator.GeneratorFactory;
import converter.factory.parser.ParserFactory;
import converter.factory.validator.InputResourceAccessFactory;
import converter.factory.validator.InputResourceSyntacticFactory;
import converter.factory.validator.OutputResourceAccessFactory;
import converter.factory.writer.WriterFactory;
import converter.interfaces.ArgumentParserInterface;
import converter.interfaces.GeneratorInterface;
import converter.parser.AbstractParser;
import converter.validator.AbstractResourceAccessValidator;
import converter.validator.AbstractResourceSyntacticValidator;
import converter.writer.AbstractWriter;

import java.io.IOException;
import java.util.*;

public class Converter {
  private String[] args;

  public Converter(String[] args) {
    this.args = args.clone();
  }

  public void convert() throws DataConverterException {
    try {
      isCorrectArgumentCount();
      parseArguments();
      validateInputArguments();
      validateOutputArguments();
      parseInputData();
      generate();
    } catch (IOException e) {
      throw new DataConverterException(e);
    }
  }

  private void isCorrectArgumentCount() throws AbstractParserException {
    if (args.length < 3) {
      throw new IncorrectArgumentsException("Invalid count arguments exception");
    }
  }

  private void parseArguments() throws AbstractParserException {
    ArgumentParserFactory argumentParserFactory = new ArgumentParserFactory(args);
    ArgumentParserInterface abstractArgumentParser = argumentParserFactory.configure();
    abstractArgumentParser.parseArgument();
  }

  private void validateInputArguments() throws AbstractValidatorException, IOException, AbstractParserException {
    for (String inputEntry : ApplicationContext.getInstance().getInput()) {
      validateInputResourceAccess(inputEntry);
      validateInputResourceSyntactic(inputEntry);
    }
  }

  private void validateInputResourceAccess(String inputArgument) throws AbstractValidatorException {
    InputResourceAccessFactory inputValidatorFactory = new InputResourceAccessFactory(inputArgument);
    AbstractResourceAccessValidator abstractResourceAccessValidator = inputValidatorFactory.configure();
    abstractResourceAccessValidator.validate();
  }

  private void validateInputResourceSyntactic(String inputArgument) throws AbstractValidatorException, IOException, AbstractParserException {
    InputResourceSyntacticFactory inputResourceSyntacticFactory = new InputResourceSyntacticFactory(inputArgument);
    AbstractResourceSyntacticValidator abstractResourceSyntacticValidator = inputResourceSyntacticFactory.configure();
    abstractResourceSyntacticValidator.validate();
  }

  private void validateOutputArguments() throws AbstractValidatorException {
    LinkedHashMap<String, String> outputMap = ApplicationContext.getInstance().getOutput();
    List<String> duplicateList = new ArrayList<>();
    for (Map.Entry entryOutput : outputMap.entrySet()) {
      String currentElement = (String) entryOutput.getValue();
      if (!duplicateList.contains(currentElement)) {
        validateOutputResourceAccess(currentElement);
        duplicateList.add(currentElement);
      }
    }
  }

  private void validateOutputResourceAccess(String outputArgument) throws AbstractValidatorException {
    OutputResourceAccessFactory outputResourceAccessFactory = new OutputResourceAccessFactory(outputArgument);
    AbstractResourceAccessValidator abstractResourceAccessValidator = outputResourceAccessFactory.configure();
    abstractResourceAccessValidator.validate();
  }

  private void parseInputData() throws AbstractParserException, AbstractValidatorException, IOException {
    for (String inputEntry : ApplicationContext.getInstance().getInput()) {
      parseInputResourceSyntactic(inputEntry);
    }
  }

  private void parseInputResourceSyntactic(String inputArgument) throws AbstractParserException, AbstractValidatorException, IOException {
    ParserFactory parserFactory = new ParserFactory(inputArgument);
    AbstractParser abstractParser = parserFactory.configure();
    abstractParser.parse();
  }

  private void generate() throws AbstractGeneratorException, IOException, AbstractWriterException {
    for (Map.Entry outputEntry : ApplicationContext.getInstance().getOutput().entrySet()) {
      String generatedData = generateModeFactory((String) outputEntry.getKey());
      writeGeneratedData((String) outputEntry.getValue(), generatedData);
    }
  }

  private String generateModeFactory(String mode) throws AbstractGeneratorException, IOException {
    GeneratorFactory generatorFactory = new GeneratorFactory(mode, (HashMap) ApplicationContext.getInstance().getParseData().clone());
    GeneratorInterface abstractGenerator = generatorFactory.configure();
    return abstractGenerator.generate();
  }

  private void writeGeneratedData(String output, String generatedData) throws AbstractWriterException, IOException {
    WriterFactory writerFactory = new WriterFactory(output);
    AbstractWriter abstractWriter = writerFactory.configure();
    abstractWriter.write(generatedData);
  }
}
