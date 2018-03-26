package com.twocookie.converter.factory;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.interfaces.ResourceWriter;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.resources.PathResource;
import com.twocookie.converter.writer.PathWriter;


public class WriterFactory implements Factory {

  private AbstractResource abstractResource;

  public WriterFactory(AbstractResource abstractResource) {
    this.abstractResource = abstractResource;
  }

  @Override
  public ResourceWriter create() throws ArgumentException {
    if (abstractResource instanceof PathResource) {
      return new PathWriter((PathResource) abstractResource);
    }
    throw new ArgumentException("Undefined type factory from " + abstractResource.toString());
  }
}
