package com.twocookie.converter.storage;

import java.util.List;

public class JSONStorage {

  public List<String> fio;
  public List<String> dob;
  public List<String> email;
  public List<String> skype;
  public List<String> phone;
  public List<String> vk;
  public List<String> avatar;
  public List<String> target;
  public List<String> experience;
  public List<String> education;
  public List<String> additionalEducation;
  public List<String> skills;
  public List<String> codeExample;

  public List<String> getValue(String key) {
    switch (key) {
      case "fio" :
        return fio;
      case "dob" :
        return dob;
      case "email" :
        return email;
      case "skype" :
        return skype;
      case "phone" :
        return phone;
      case "vk" :
        return vk;
      case "avatar" :
        return avatar;
      case "target" :
        return target;
      case "experience" :
        return experience;
      case "education" :
        return education;
      case "additionalEducation" :
        return additionalEducation;
      case "skills" :
        return skills;
      case "codeExample" :
        return codeExample;
      default:
        return null;
    }
  }

}
