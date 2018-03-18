package converter.writer;

import converter.exception.AbstractWriterException;
import converter.exception.WriterUseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConverterFileWriter extends AbstractWriter {

  private String output;

  public ConverterFileWriter(String output) {
    this.output = output;
  }

  @Override
  public void write(String generatedData) throws AbstractWriterException, IOException {
    Scanner in = new Scanner(System.in, "utf-8");
    System.out.println("Введите, пожалуйста, имя файла и расширение");
    String nameFile = in.nextLine();
    checkInputFile(nameFile);
    File file = createFile(nameFile);
    if (file.createNewFile()) {
      writeFile(file, generatedData);
    } else {
      throw new WriterUseException("File can not be created");
    }
  }

  private File createFile(String name) {
    return new File(output, name);
  }

  private void writeFile(File file, String text) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    Writer fileWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
    try {
      fileWriter.write(text);
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      fileWriter.close();
    }
  }

  private void checkInputFile(String inputFile) throws AbstractWriterException {
    if (!inputFile.contains(".") || inputFile.isEmpty()) {
      throw new WriterUseException("Incorrect input data");
    }
  }
}
