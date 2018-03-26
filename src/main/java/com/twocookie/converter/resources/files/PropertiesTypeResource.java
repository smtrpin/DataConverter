package com.twocookie.converter.resources.files;

import com.twocookie.converter.exception.SyntacticValidatorException;
import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.interfaces.LineParser;
import com.twocookie.converter.resources.FileResource;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesTypeResource extends AbstractTypeResource {

  private FileResource fileResource;
  private Pattern propertiesPattern = Pattern.compile("^(.*)=(.*)$");
  private Properties properties = null;


  public PropertiesTypeResource(FileResource fileResource) {
    super(fileResource);
    this.fileResource = fileResource;
  }

  @Override
  public void isCorrectFormat() throws IOException, ValidatorException {
    LineParser lineParser = this::checkLineFormat;
    fileResource.readFile(lineParser);
  }

  @Override
  public HashSet<String> getDataByKey(String key) throws IOException {
    createProperties();
    String info = properties.getProperty(key, "-");
    return parseLine(info);
  }

  private void createProperties() throws IOException {
    if (properties == null) {
      Properties instanceProperty = new Properties();
      instanceProperty.load(fileResource.getInputStreamReader());
      properties = instanceProperty;
    }
  }

  private HashSet<String> parseLine(String line) {
    HashSet<String> values = new HashSet<>();
    String[] items = explodeText(line, "\",\"");
    for (String item : items) {
      values.add(replaceText(item.trim(), "^\"+|\"+$", ""));
    }
    return values;
  }

  private String replaceText(String text, String oldSymbol, String newSymbol) {
    return text.replaceAll(oldSymbol, newSymbol);
  }

  private String[] explodeText(String text, String symbol) {
    return text.split(symbol);
  }

  private void checkLineFormat(String line) throws ValidatorException {
    if (!checkFormat(line)) {
      throw new SyntacticValidatorException("Incorrect line format from " + line);
    }
  }

  private boolean checkFormat(String line) {
    Matcher matcher = matchLine(line);
    return matcher.find(0);
  }

  private Matcher matchLine(String line) {
    return propertiesPattern.matcher(line);
  }
}
