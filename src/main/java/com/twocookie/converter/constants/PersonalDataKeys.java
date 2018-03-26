package com.twocookie.converter.constants;

public enum PersonalDataKeys {

  FIO("fio"),
  DOB("dob"),
  EMAIL("email"),
  SKYPE("skype"),
  PHONE("phone"),
  VK("vk"),
  AVATAR("avatar"),
  TARGET("target"),
  EXPERIENCE("experience"),
  EDUCATION("education"),
  ADDITIONAL_EDUCATION("additionalEducation"),
  SKILLS("skills"),
  CODE_EXAMPLE("codeExample");

  private String value;

  PersonalDataKeys(String key) {
    value = key;
  }

  public String getValue() {
    return value;
  }

}
