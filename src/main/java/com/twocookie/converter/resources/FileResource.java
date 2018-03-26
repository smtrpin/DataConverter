package com.twocookie.converter.resources;

import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.interfaces.LineParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileResource extends AbstractResource {

  private File file;

  public FileResource(File file) {
    this.file = file;
  }

  public File getFile() {
    return file;
  }

  public boolean isReadable() {
    return file.canRead();
  }

  public String getFileExtension() {
    String directory = file.toString();
    if(directory.lastIndexOf(".") != -1 && directory.lastIndexOf(".") != 0) {
      return directory.substring(directory.lastIndexOf(".") + 1);
    }
    return null;
  }

  public long getLength() {
    return file.length();
  }

  public String getFileCharset() throws IOException {
    InputStreamReader inputStreamReader = getInputStreamReader();
    String encoding = inputStreamReader.getEncoding();
    closeInputStreamReader(inputStreamReader);
    return encoding;
  }

  public void readFile(LineParser lineParser) throws IOException, ValidatorException {
    InputStreamReader fileReader = getInputStreamReader();
    BufferedReader reader = new BufferedReader(fileReader);
    while (reader.ready()) {
      String line = reader.readLine();
      lineParser.parse(line);
    }
    closeInputStreamReader(fileReader);
    closeBufferedReader(reader);
  }

  public InputStreamReader getInputStreamReader() throws FileNotFoundException {
    FileInputStream fileInputStream = new FileInputStream(file);
    return new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
  }

  @Override
  public String toString() {
    return file.toString();
  }

  private void closeInputStreamReader(InputStreamReader stream) throws IOException {
    stream.close();
  }

  private void closeBufferedReader(BufferedReader reader) throws IOException {
    reader.close();
  }
}
