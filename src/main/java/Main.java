import com.twocookie.converter.Converter;
import com.twocookie.converter.exception.DataConverterException;
import com.twocookie.converter.generator.HTMLGenerator;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class Main {

  public static void main(String[] args) {
    try {
      Converter converter = new Converter(args);
      HashSet<HTMLGenerator> convertResult = converter.convert();
      System.out.println(convertResult);
    } catch (DataConverterException | IOException | InterruptedException | ExecutionException e) {
      System.out.println(e.getMessage());
    }
  }
}
