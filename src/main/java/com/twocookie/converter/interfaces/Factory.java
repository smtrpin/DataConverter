package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.DAOException;
import com.twocookie.converter.exception.ValidatorException;

public interface Factory<T> {

  T create() throws ArgumentException, ValidatorException, DAOException;
}
