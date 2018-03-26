package com.twocookie.converter;

import com.twocookie.converter.dao.repository.PersonalInfoDAO;
import com.twocookie.converter.domain.entity.PersonalData;
import com.twocookie.converter.exception.*;
import com.twocookie.converter.factory.*;
import com.twocookie.converter.generator.HTMLGenerator;
import com.twocookie.converter.interfaces.Parser;
import com.twocookie.converter.interfaces.ResourceWriter;
import com.twocookie.converter.interfaces.Validator;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.storage.ArgumentStorage;
import com.twocookie.converter.validator.argument.ArgumentValidator;

import java.io.IOException;
import java.util.HashSet;

public class Converter {

  private String[] args;
  private ArgumentStorage argumentStorage;

  public Converter(String[] args) {
    this.args = args.clone();
  }

  public String convert() throws DataConverterException, IOException {
    isCorrectArgument();
    parseArguments();
    validateInput();
    validateOutput();
    HashSet<PersonalData> personalData = parseData();
    HashSet<HTMLGenerator> pages = htmlGenerate(personalData);
    write(pages);
    return "Complete";
  }

  private void isCorrectArgument() throws ArgumentException {
    ArgumentValidator argumentValidator = new ArgumentValidator(args);
    argumentValidator.argumentValidate();
  }

  private void parseArguments() throws ArgumentException {
    ArgumentFactory argumentFactory = new ArgumentFactory(args);
    Parser<?> argumentParser =  argumentFactory.create();
    this.argumentStorage = argumentParser.parse();
  }

  private void validateInput() throws ArgumentException, ValidatorException, IOException {
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getInputStorage().getInput();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      Validator accessValidator = getAccessValidatorFactory(abstractResource);
      accessValidator.validate();

      Validator syntacticValidator = getSyntacticValidatorFactory(abstractResource);
      syntacticValidator.validate();
    }
  }

  private void validateOutput() throws ArgumentException, IOException, ValidatorException {
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getOutputStorage().getOutput();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      Validator accessValidator = getAccessValidatorFactory(abstractResource);
      accessValidator.validate();
    }
  }

  private Validator getAccessValidatorFactory(AbstractResource abstractResource) throws ArgumentException {
    AccessValidatorFactory accessValidatorFactory = new AccessValidatorFactory(abstractResource);
    return accessValidatorFactory.create();
  }

  private Validator getSyntacticValidatorFactory(AbstractResource abstractResource) throws ArgumentException {
    SyntacticValidatorFactory syntacticValidatorFactory = new SyntacticValidatorFactory(abstractResource);
    return syntacticValidatorFactory.create();
  }

  private HashSet<PersonalData> parseData() throws ArgumentException, DAOException, IOException, ValidatorException {
    HashSet<PersonalData> personalData = new HashSet<>();
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getInputStorage().getInput();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      DAOFactory daoFactory = new DAOFactory(abstractResource);
      PersonalInfoDAO personalInfoDAO = daoFactory.create();
      personalData.add(personalInfoDAO.getPersonalData());
    }
    return personalData;
  }

  private HashSet<HTMLGenerator> htmlGenerate(HashSet<PersonalData> personalData) throws IOException, GeneratorException {
    HashSet<HTMLGenerator> htmlGenerators = new HashSet<>();
    for (PersonalData data : personalData) {
      HTMLGenerator htmlGenerator = new HTMLGenerator(data);
      htmlGenerator.generate();
      htmlGenerators.add(htmlGenerator);
    }
    return htmlGenerators;
  }

  private void write(HashSet<HTMLGenerator> htmlGenerators) throws ArgumentException, IOException, WriterException {
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getOutputStorage().getOutput();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      WriterFactory writerFactory = new WriterFactory(abstractResource);
      ResourceWriter writer = writerFactory.create();
      writer.write(htmlGenerators);
    }
  }
}
