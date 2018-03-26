package com.twocookie.converter.storage;

import com.twocookie.converter.resources.AbstractResource;

import java.util.Set;

public class OutputStorage {

  private Set<AbstractResource> output;

  public OutputStorage(Set<AbstractResource> outputCollection) {
    this.output = outputCollection;
  }

  public Set<AbstractResource> getOutput() {
    return output;
  }
}
