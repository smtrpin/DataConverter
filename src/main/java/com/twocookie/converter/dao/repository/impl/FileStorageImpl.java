package com.twocookie.converter.dao.repository.impl;

import com.twocookie.converter.constants.PersonalDataKeys;
import com.twocookie.converter.dao.repository.PersonalInfoDAO;
import com.twocookie.converter.domain.entity.PersonalData;
import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.factory.FileTypeResourceFactory;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.resources.files.AbstractTypeResource;

import java.io.IOException;

public class FileStorageImpl implements PersonalInfoDAO {

  private FileResource fileResource;

  public FileStorageImpl(FileResource fileResource) {
    this.fileResource = fileResource;
  }

  @Override
  public PersonalData getPersonalData() throws ArgumentException, IOException, ValidatorException {
    FileTypeResourceFactory fileTypeResourceFactory = new FileTypeResourceFactory(fileResource);
    AbstractTypeResource abstractTypeResource = fileTypeResourceFactory.create();
    return new PersonalData(
            abstractTypeResource.getDataByKey(PersonalDataKeys.FIO.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.DOB.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.EMAIL.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.SKYPE.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.PHONE.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.VK.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.AVATAR.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.TARGET.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.EXPERIENCE.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.EDUCATION.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.ADDITIONAL_EDUCATION.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.SKILLS.getValue()),
            abstractTypeResource.getDataByKey(PersonalDataKeys.CODE_EXAMPLE.getValue())
    );
  }
}
