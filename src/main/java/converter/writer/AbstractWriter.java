package converter.writer;

import converter.exception.AbstractWriterException;

import java.io.IOException;

public abstract class AbstractWriter {
  public abstract void write(String generatedData) throws AbstractWriterException, IOException;
}
