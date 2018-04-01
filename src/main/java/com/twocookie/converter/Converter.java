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
import java.util.concurrent.*;

public class Converter {

  private String[] args;
  private ArgumentStorage argumentStorage;

  public Converter(String[] args) {
    this.args = args.clone();
  }

  public HashSet<HTMLGenerator> convert() throws DataConverterException, IOException, ExecutionException, InterruptedException {
    isCorrectArgument();
    parseArguments();
    validateInput();
    validateOutput();
    HashSet<PersonalData> personalData = parseData();
    HashSet<HTMLGenerator> pages = htmlGenerate(personalData);
    write(pages);
    return pages;
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

  private void validateInput() {
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getInputStorage().getInput();
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          try {
            Validator accessValidator = getAccessValidatorFactory(abstractResource);
            accessValidator.validate();

            Validator syntacticValidator = getSyntacticValidatorFactory(abstractResource);
            syntacticValidator.validate();
          } catch (ArgumentException | ValidatorException | IOException e) {
            System.out.println(e.getMessage());
          }
        }
      });
    }
    executorService.shutdown();
  }

  private void validateOutput() {
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getOutputStorage().getOutput();
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          try {
            Validator accessValidator = getAccessValidatorFactory(abstractResource);
            accessValidator.validate();
          } catch (ArgumentException | IOException | ValidatorException e) {
            System.out.println(e.getMessage());
          }
        }
      });
    }
    executorService.shutdown();
  }

  private Validator getAccessValidatorFactory(AbstractResource abstractResource) throws ArgumentException {
    AccessValidatorFactory accessValidatorFactory = new AccessValidatorFactory(abstractResource);
    return accessValidatorFactory.create();
  }

  private Validator getSyntacticValidatorFactory(AbstractResource abstractResource) throws ArgumentException {
    SyntacticValidatorFactory syntacticValidatorFactory = new SyntacticValidatorFactory(abstractResource);
    return syntacticValidatorFactory.create();
  }

  private HashSet<PersonalData> parseData() throws ExecutionException, InterruptedException {
    HashSet<PersonalData> personalData = new HashSet<>();
    HashSet<AbstractResource> abstractResourceHashSet = (HashSet<AbstractResource>) argumentStorage.getInputStorage().getInput();
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (AbstractResource abstractResource : abstractResourceHashSet) {
      Future<PersonalData> personalDataFuture = executorService.submit(new Callable<PersonalData>() {
        @Override
        public PersonalData call() throws Exception {
          DAOFactory daoFactory = new DAOFactory(abstractResource);
          PersonalInfoDAO personalInfoDAO = daoFactory.create();
          return personalInfoDAO.getPersonalData();
        }
      });

      personalData.add(personalDataFuture.get());
    }
    executorService.shutdown();
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
