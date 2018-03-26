package com.twocookie.converter.factory;

import com.twocookie.converter.dao.repository.PersonalInfoDAO;
import com.twocookie.converter.dao.repository.impl.FileStorageImpl;
import com.twocookie.converter.exception.DAOException;
import com.twocookie.converter.interfaces.Factory;
import com.twocookie.converter.resources.AbstractResource;
import com.twocookie.converter.resources.FileResource;

public class DAOFactory implements Factory {

  private AbstractResource abstractResource;

  public DAOFactory(AbstractResource abstractResource) {
    this.abstractResource = abstractResource;
  }

  @Override
  public PersonalInfoDAO create() throws DAOException {
    if (abstractResource instanceof FileResource) {
      return new FileStorageImpl((FileResource) abstractResource);
    }
    throw new DAOException("Undefined DAO type factory");
  }
}
