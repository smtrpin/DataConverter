package com.twocookie.converter.validator;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.factory.FileTypeResourceFactory;
import com.twocookie.converter.interfaces.FileSyntacticValidator;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.resources.files.AbstractTypeResource;

import java.io.IOException;

public class FileResourceSyntacticValidator implements FileSyntacticValidator {

  private FileResource fileResource;

  public FileResourceSyntacticValidator(FileResource fileResource) {
    this.fileResource = fileResource;
  }

  @Override
  public void validate() throws ValidatorException, ArgumentException, IOException {
    FileTypeResourceFactory fileTypeResourceFactory = new FileTypeResourceFactory(fileResource);
    AbstractTypeResource abstractTypeResource = fileTypeResourceFactory.create();
    isEmpty(abstractTypeResource);
    isCorrectCharset(abstractTypeResource);
    isCorrectFormat(abstractTypeResource);
  }

  @Override
  public void isEmpty(AbstractTypeResource abstractFileResource) throws ValidatorException {
    if (abstractFileResource.isEmpty()) {
      throw new ValidatorException("File " + abstractFileResource.toString() + " is empty");
    }
  }

  @Override
  public void isCorrectCharset(AbstractTypeResource abstractFileResource) throws ValidatorException, IOException {
    if (!abstractFileResource.getFileCharset().equals("UTF8")) {
      throw new ValidatorException("Incorrect charset from file " + abstractFileResource.toString());
    }
  }

  @Override
  public void isCorrectFormat(AbstractTypeResource abstractFileResource) throws ValidatorException, IOException {
    abstractFileResource.isCorrectFormat();
  }
}
