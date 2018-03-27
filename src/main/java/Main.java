import com.twocookie.converter.Converter;
import com.twocookie.converter.exception.DataConverterException;

import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    try {
      Converter converter = new Converter(args);
      String convertResult = converter.convert();
      System.out.println(convertResult);
    } catch (DataConverterException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
