package converter.interfaces;

import converter.exception.AbstractGeneratorException;

import java.io.IOException;

public interface GeneratorInterface {

  String generate() throws AbstractGeneratorException, IOException;
}
