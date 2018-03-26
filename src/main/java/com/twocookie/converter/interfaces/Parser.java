package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.storage.ArgumentStorage;

public interface Parser<T> {
  ArgumentStorage parse() throws ArgumentException;
}
