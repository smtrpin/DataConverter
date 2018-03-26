package com.twocookie.converter.dao.repository;

import com.twocookie.converter.domain.entity.PersonalData;
import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ValidatorException;

import java.io.IOException;

public interface PersonalInfoDAO {

  PersonalData getPersonalData() throws ArgumentException, IOException, ValidatorException;
}
