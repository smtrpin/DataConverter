package converter.resource.file.type;

import converter.ApplicationContext;
import converter.ParserData;
import converter.exception.AbstractValidatorException;
import converter.exception.ResourceSyntacticException;
import converter.interfaces.LineParser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesTypeResource extends AbstractResourceTypeFile {

  private Pattern propertiesPattern = Pattern.compile("^(.*)=(.*)$");

  @Override
  public void validate(String argument) throws AbstractValidatorException, IOException {
    super.file = new File(argument);
    isEmpty();
    checkCharset();
    validateLineFormat();
  }

  @Override
  public void parse(String argument) throws AbstractValidatorException, IOException {
    super.file = new File(argument);
    LineParser lineParser = this::parseLine;
    readFile(lineParser);
  }

  private void validateLineFormat() throws AbstractValidatorException, IOException {
    LineParser lineParser = this::checkFormatLine;
    readFile(lineParser);
  }

  private void checkFormatLine(String line) throws ResourceSyntacticException {
    if (!isCorrectFormatLine(line)) {
      throw new ResourceSyntacticException("Incorrect line format. Error line: " + line + " on file " + file);
    }
  }

  private boolean isCorrectFormatLine(String line) {
    Matcher matcher = matchLine(line);
    return matcher.find(0);
  }

  private void parseLine(String line) {
    explodeLine(line);
  }

  private void explodeLine(String line) {
    Matcher matcher = matchLine(line);
    while (matcher.find()) {
      String keyMatch = matcher.group(1);
      LinkedHashSet<String> valMatch = addValueToMap(splitValueFromSymbol(matcher.group(2), "\",\""));

      ApplicationContext.getInstance().setData(keyMatch, new ParserData(valMatch));
    }
  }

  private Matcher matchLine(String line) {
    return propertiesPattern.matcher(line);
  }

  private String[] splitValueFromSymbol(String phrase, String symbol) {
    return phrase.split(symbol);
  }

  private LinkedHashSet<String> addValueToMap(String[] values) {
    LinkedHashSet<String> strings = new LinkedHashSet<>();
    for (String value : values) {
      String text = replaceText(value.trim(), "^\"+|\"+$", "");
      strings.add(text);
    }
    return strings;
  }

  private String replaceText(String text, String oldSymbol, String newSymbol) {
    return text.replaceAll(oldSymbol, newSymbol);
  }
}
