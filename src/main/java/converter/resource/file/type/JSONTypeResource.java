package converter.resource.file.type;

import converter.ApplicationContext;
import converter.ParserData;
import converter.exception.AbstractValidatorException;
import converter.exception.ResourceSyntacticException;
import converter.interfaces.LineParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONTypeResource extends AbstractResourceTypeFile{

  private Pattern jsonPattern = Pattern.compile("^\\{.*\\}$");

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
    LineParser lineParser = this::jsonParse;
    readFile(lineParser);
  }

  private void jsonParse(String line) throws AbstractValidatorException {
    JSONObject jsonObject = stringToJSON(line);
    Map<String, Object> map = jsonObject.toMap();
    for (Map.Entry entry : map.entrySet()) {
      Object value = entry.getValue();
      LinkedHashSet<String> valueElements = parseJSONValue(value.toString());
      ApplicationContext.getInstance().setData((String) entry.getKey(), new ParserData(valueElements));
    }
  }

  private LinkedHashSet<String> parseJSONValue(String value) {
    LinkedHashSet<String> values = new LinkedHashSet<>();
    value = trimSymbol(value, "[", "");
    value = trimSymbol(value, "]", "");
    String[] elements = explodeTextBySymbol(value, "\\,");
    Collections.addAll(values, elements);
    return values;
  }

  private String[] explodeTextBySymbol(String text, String symbol) {
    return text.split(symbol);
  }

  private String trimSymbol(String text, String oldSymnol, String newSymbol) {
    return text.replace(oldSymnol, newSymbol);
  }

  private void validateLineFormat() throws AbstractValidatorException, IOException {
    LineParser lineParser = this::formatParse;
    readFile(lineParser);
  }

  private void formatParse(String line) throws AbstractValidatorException {
    if (!isCorrectJSONFormat(line)) {
      throw new ResourceSyntacticException("Incorrect format from json file");
    }
  }

  private boolean isCorrectJSONFormat(String line) {
    Matcher matcher = jsonPattern.matcher(line);
    return matcher.find(0);
  }

  private JSONObject stringToJSON(String text) throws AbstractValidatorException {
    try {
      return new JSONObject(text);
    } catch (JSONException e) {
      throw new ResourceSyntacticException("Incorrect JSON");
    }
  }
}
