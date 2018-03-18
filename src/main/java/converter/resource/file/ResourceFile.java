package converter.resource.file;

import converter.resource.Resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceFile extends Resource {

  public static boolean isEmpty(File file) {
    return file.length() == 0;
  }

  public static boolean isReadable(String directory) {
    Path path = getPathFromString(directory);
    return Files.isReadable(path);
  }

  public static boolean isFile(String directory) {
    Path path = getPathFromString(directory);
    return Files.isRegularFile(path);
  }

  public static String getFileExtension(String directory) {
    if(directory.lastIndexOf(".") != -1 && directory.lastIndexOf(".") != 0) {
      return directory.substring(directory.lastIndexOf(".") + 1);
    }
    return null;
  }

  private static Path getPathFromString(String stringDirectory) {
    return Paths.get(stringDirectory);
  }
}
