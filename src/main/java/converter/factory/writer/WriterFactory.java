package converter.factory.writer;

import converter.exception.AbstractWriterException;
import converter.exception.UnknownTypeWriterException;
import converter.resource.file.ResourcePath;
import converter.writer.AbstractWriter;
import converter.writer.ConverterFileWriter;

public class WriterFactory {
  private String output;
  public WriterFactory(String output) {
    this.output = output;
  }

  public AbstractWriter configure() throws AbstractWriterException {
    if (isDirectory()) {
      return new ConverterFileWriter(output);
    }
    throw new UnknownTypeWriterException("Unknown type from " + output);
  }

  private boolean isDirectory() {
    return ResourcePath.isDirectory(output);
  }
}
