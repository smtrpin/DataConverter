package converter.resource.file;

import converter.resource.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourcePath extends Resource {

  public static boolean isDirectory(String directory) {
    Path path = Paths.get(directory);
    return Files.isDirectory(path);
  }

  public static boolean isWritable(String directory) {
    Path path = Paths.get(directory);
    return Files.isWritable(path);
  }
}
