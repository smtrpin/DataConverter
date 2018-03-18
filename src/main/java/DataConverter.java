import converter.Converter;
import converter.exception.AbstractGeneratorException;
import converter.exception.AbstractParserException;
import converter.exception.AbstractValidatorException;
import converter.exception.AbstractWriterException;

import java.io.IOException;

public class DataConverter {

  public static void main(String[] args) {
    try {
      Converter converter = new Converter(args);
      converter.convert();
      System.out.println("Complete");
    } catch (AbstractParserException | AbstractValidatorException | IOException | AbstractGeneratorException | AbstractWriterException e) {
      System.out.println(e.getMessage());
    }
  }
}
