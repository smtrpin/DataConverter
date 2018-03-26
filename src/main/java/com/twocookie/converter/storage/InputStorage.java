package com.twocookie.converter.storage;

import com.twocookie.converter.resources.AbstractResource;

import java.util.Set;

public class InputStorage {

  private Set<AbstractResource> input;

  public InputStorage(Set<AbstractResource> input) {
    this.input = input;
  }

  public Set<AbstractResource> getInput() {
    return input;
  }
}
