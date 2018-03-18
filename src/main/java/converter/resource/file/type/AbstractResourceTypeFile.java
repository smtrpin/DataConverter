package converter.resource.file.type;

import converter.exception.AbstractValidatorException;
import converter.exception.ResourceSyntacticException;
import converter.interfaces.LineParser;
import converter.resource.Resource;
import converter.resource.file.ResourceFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class AbstractResourceTypeFile extends Resource {

  protected File file;

  public abstract void validate(String argument) throws AbstractValidatorException, IOException;
  public abstract void parse(String argument) throws AbstractValidatorException, IOException;

  protected void isEmpty() throws AbstractValidatorException {
    if (ResourceFile.isEmpty(file)) {
      throw new ResourceSyntacticException("File " + file + " is empty");
    }
  }

  protected String fileCharset() throws IOException {
    InputStreamReader inputStreamReader = getInputStreamReader();
    String encoding = inputStreamReader.getEncoding();
    closeInputStreamReader(inputStreamReader);
    return encoding;
  }

  protected void checkCharset() throws AbstractValidatorException, IOException {

    if (!fileCharset().equals("UTF8")) {
      throw new ResourceSyntacticException("Incorrect charset");
    }
  }

  protected void readFile(LineParser lineParser) throws AbstractValidatorException, IOException {
    InputStreamReader fileReader = getInputStreamReader();
    BufferedReader reader = new BufferedReader(fileReader);
    while (reader.ready()) {
      String line = reader.readLine();
      lineParser.parse(line);
    }
    closeInputStreamReader(fileReader);
    closeBufferedReader(reader);
  }

  private InputStreamReader getInputStreamReader() throws FileNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(file);
    return new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
  }

  private void closeInputStreamReader(InputStreamReader stream) throws IOException {
    stream.close();
  }

  private void closeBufferedReader(BufferedReader reader) throws IOException {
    reader.close();
  }
}
