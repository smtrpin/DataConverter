package com.twocookie.converter.domain.entity;

import java.util.HashSet;

public class PersonalData {

  private HashSet<String> fio;
  private HashSet<String> dob;
  private HashSet<String> email;
  private HashSet<String> skype;
  private HashSet<String> phone;
  private HashSet<String> vk;
  private HashSet<String> avatar;
  private HashSet<String> target;
  private HashSet<String> experience;
  private HashSet<String> education;
  private HashSet<String> additionalEducation;
  private HashSet<String> skills;
  private HashSet<String> codeExample;

  public PersonalData(
          HashSet<String> fio,
          HashSet<String> dob,
          HashSet<String> email,
          HashSet<String> skype,
          HashSet<String> phone,
          HashSet<String> vk,
          HashSet<String> avatar,
          HashSet<String> target,
          HashSet<String> experience,
          HashSet<String> education,
          HashSet<String> additionalEducation,
          HashSet<String> skills,
          HashSet<String> codeExample) {
    this.fio = fio;
    this.dob = dob;
    this.email = email;
    this.skype = skype;
    this.phone = phone;
    this.vk = vk;
    this.avatar = avatar;
    this.target = target;
    this.experience = experience;
    this.education = education;
    this.additionalEducation = additionalEducation;
    this.skills = skills;
    this.codeExample = codeExample;
  }

  public HashSet<String> getFio() {
    return fio;
  }

  public HashSet<String> getDob() {
    return dob;
  }

  public HashSet<String> getEmail() {
    return email;
  }

  public HashSet<String> getSkype() {
    return skype;
  }

  public HashSet<String> getPhone() {
    return phone;
  }

  public HashSet<String> getVk() {
    return vk;
  }

  public HashSet<String> getAvatar() {
    return avatar;
  }

  public HashSet<String> getTarget() {
    return target;
  }

  public HashSet<String> getExperience() {
    return experience;
  }

  public HashSet<String> getEducation() {
    return education;
  }

  public HashSet<String> getAdditionalEducation() {
    return additionalEducation;
  }

  public HashSet<String> getSkills() {
    return skills;
  }

  public HashSet<String> getCodeExample() {
    return codeExample;
  }
}
