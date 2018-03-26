package com.twocookie.converter.writer;

import com.twocookie.converter.exception.WriterException;
import com.twocookie.converter.generator.HTMLGenerator;
import com.twocookie.converter.interfaces.ResourceWriter;
import com.twocookie.converter.resources.PathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

public class PathWriter implements ResourceWriter {

  private PathResource pathResource;

  public PathWriter(PathResource pathResource) {
    this.pathResource = pathResource;
  }

  @Override
  public void write(HashSet<HTMLGenerator> generator) throws WriterException, IOException {
    for (HTMLGenerator htmlGenerator : generator) {
      Scanner in = new Scanner(System.in, "utf-8");
      System.out.println("Введите, пожалуйста, имя файла и расширение");
      String nameFile = in.nextLine();
      checkInputFile(nameFile);
      File file = createFile(pathResource.getPath(), nameFile);
      if (file.createNewFile()) {
        writeFile(file, htmlGenerator.toString());
      } else {
        throw new WriterException("File can not be created");
      }
    }
  }

  private File createFile(Path path, String name) {
    return new File(path.toString(), name);
  }

  private void writeFile(File file, String text) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    Writer fileWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
    try {
      fileWriter.write(text);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      fileWriter.flush();
      fileWriter.close();
    }
  }

  private void checkInputFile(String inputFile) throws WriterException {
    if (!inputFile.contains(".") || inputFile.isEmpty()) {
      throw new WriterException("Incorrect input data");
    }
  }
}
