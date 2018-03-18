package converter;

import java.util.LinkedHashSet;

public class ParserData {

  private LinkedHashSet<String> parserStorage;

  public ParserData(LinkedHashSet<String> parserStorage) {
    this.parserStorage = parserStorage;
  }

  public LinkedHashSet<String> getParserStorage() {
    return parserStorage;
  }

}
