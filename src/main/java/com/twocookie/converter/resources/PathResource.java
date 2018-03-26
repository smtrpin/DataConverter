package com.twocookie.converter.resources;


import java.nio.file.Files;
import java.nio.file.Path;

public class PathResource extends AbstractResource {

  private Path path;

  public Path getPath() {
    return path;
  }

  public PathResource(Path path) {
    this.path = path;
  }

  public boolean isWritable() {
    return Files.isWritable(path);
  }

  @Override
  public String toString() {
    return path.toString();
  }
}
