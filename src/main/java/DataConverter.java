import converter.Converter;
import converter.exception.*;

import java.io.IOException;

public class DataConverter {

  public static void main(String[] args) {
    try {
      Converter converter = new Converter(args);
      converter.convert();
      System.out.println("Complete");
    } catch (DataConverterException e) {
      System.out.println(e.getMessage());
    }
  }
}
