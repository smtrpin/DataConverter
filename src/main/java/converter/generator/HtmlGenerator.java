package converter.generator;

import converter.ParserData;
import converter.exception.AbstractGeneratorException;
import converter.exception.GeneratorUseException;
import converter.interfaces.GeneratorInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class HtmlGenerator implements GeneratorInterface {

  private HashMap map;
  private HashMap<String, String> translate = new HashMap<>();
  private StringBuilder htmlPage = new StringBuilder();
  private final static String HEADER_TEMPLATE = "header.html";
  private final static String BLOCK_HEAD_TEMPLATE = "block-head.html";
  private final static String BLOCK_SECTION_TEMPLATE = "block-section.html";
  private final static String FOOTER_TEMPLATE = "footer.html";

  public HtmlGenerator(HashMap map) {
    this.map = map;
    setTranslation();
  }

  @Override
  public String generate() throws AbstractGeneratorException, IOException {
    setHeader();
    setBlockHead();
    setBlockPersonalInfo();
    setBlockTarget();
    setBlockExperience();
    setBlockEducation();
    setBlockAdditionalEducation();
    setBlockSkills();
    setBlockCodeExample();
    setFooter();
    return htmlPage.toString();
  }

  private void setTranslation() {
    translate.put("fio", "ФИО");
    translate.put("dob", "Дата рождения");
    translate.put("phone", "Телефон");
    translate.put("vk", "Вконтакте");
    translate.put("email", "e-mail");
    translate.put("skype", "Skype");
    translate.put("avatar", "Аватар");
    translate.put("target", "Цель");
    translate.put("experience", "Опыт");
    translate.put("education", "Образование");
    translate.put("additionalEducation", "Дополнительно образование");
    translate.put("skills", "Навыки");
    translate.put("codeExample", "Пример кода");
  }

  private void setHeader() throws AbstractGeneratorException, IOException {
    isTemplate(HEADER_TEMPLATE);
    String headerTemplate = getTemplate(HEADER_TEMPLATE);
    headerTemplate = replaceTitle(headerTemplate, "Результат тестового задания");
    htmlPage.append(headerTemplate);
  }

  private void setFooter() throws AbstractGeneratorException, IOException {
    isTemplate(FOOTER_TEMPLATE);
    String headerTemplate = getTemplate(FOOTER_TEMPLATE);
    htmlPage.append(headerTemplate);
  }

  private void setBlockHead() throws AbstractGeneratorException, IOException {
    isTemplate(BLOCK_HEAD_TEMPLATE);
    String blockHeadTemplate = getTemplate(BLOCK_HEAD_TEMPLATE);
    htmlPage.append(blockHeadTemplate);
  }

  private void setBlockPersonalInfo() throws AbstractGeneratorException, IOException {
    HashMap<String, String> blockPersonalInfoMap = new HashMap<>();
    blockPersonalInfoMap.put("template", BLOCK_SECTION_TEMPLATE);
    blockPersonalInfoMap.put("cardInfoClass", "card-info-block-byo");
    blockPersonalInfoMap.put("cardIcon", "<i class=\"fas fa-address-book\"></i>");
    blockPersonalInfoMap.put("cardName", "Персональная<br>информация");
    blockPersonalInfoMap.put("contentInfo", getPersonalContentHtml());
    blockPersonalInfoMap.put("additionalBlock", "<div class=\"card-right-block card-right-block-before\">" + getPhoto() + "</div>");
    String personalInfoSection = setSection(blockPersonalInfoMap);
    htmlPage.append(personalInfoSection);
  }

  private void setBlockTarget() throws AbstractGeneratorException, IOException {
    if (map.get("target") != null) {
      HashMap<String, String> blockTargetTemplate = new HashMap<>();
      blockTargetTemplate.put("template", BLOCK_SECTION_TEMPLATE);
      blockTargetTemplate.put("cardInfoClass", "card-info-block-target");
      blockTargetTemplate.put("cardIcon", "<i class=\"fas fa-info-circle\"></i>");
      blockTargetTemplate.put("cardName", "Цель");
      ParserData blockTargetData = (ParserData) map.get("target");
      blockTargetTemplate.put("contentInfo", getParagraphContentHtml(blockTargetData.getParserStorage()));
      blockTargetTemplate.put("additionalBlock", "");
      String targetTemplate = setSection(blockTargetTemplate);
      htmlPage.append(targetTemplate);
    }
  }

  private void setBlockExperience() throws AbstractGeneratorException, IOException {
    if (map.get("experience") != null) {
      HashMap<String, String> blockExperienceTemplate = new HashMap<>();
      blockExperienceTemplate.put("template", BLOCK_SECTION_TEMPLATE);
      blockExperienceTemplate.put("cardInfoClass", "card-info-block-experience");
      blockExperienceTemplate.put("cardIcon", "<i class=\"fas fa-briefcase\"></i>");
      blockExperienceTemplate.put("cardName", "Опыт");
      ParserData blockExperienceData = (ParserData) map.get("experience");
      blockExperienceTemplate.put("contentInfo", getParagraphContentHtml(blockExperienceData.getParserStorage()));
      blockExperienceTemplate.put("additionalBlock", "");
      String experienceTemplate = setSection(blockExperienceTemplate);
      htmlPage.append(experienceTemplate);
    }
  }

  private void setBlockEducation() throws AbstractGeneratorException, IOException {
    if (map.get("education") != null) {
      HashMap<String, String> blockEducationTemplate = new HashMap<>();
      blockEducationTemplate.put("template", BLOCK_SECTION_TEMPLATE);
      blockEducationTemplate.put("cardInfoClass", "card-info-block-education");
      blockEducationTemplate.put("cardIcon", "<i class=\"fas fa-book\"></i>");
      blockEducationTemplate.put("cardName", "Образование");
      ParserData blockEducationData = (ParserData) map.get("education");
      blockEducationTemplate.put("contentInfo", getParagraphContentHtml(blockEducationData.getParserStorage()));
      blockEducationTemplate.put("additionalBlock", "");
      String educationTemplate = setSection(blockEducationTemplate);
      htmlPage.append(educationTemplate);
    }
  }

  private void setBlockAdditionalEducation() throws AbstractGeneratorException, IOException {
    if (map.get("additionalEducation") != null) {
      HashMap<String, String> blockEducationTemplate = new HashMap<>();
      blockEducationTemplate.put("template", BLOCK_SECTION_TEMPLATE);
      blockEducationTemplate.put("cardInfoClass", "card-info-block-education");
      blockEducationTemplate.put("cardIcon", "<i class=\"fas fa-book\"></i>");
      blockEducationTemplate.put("cardName", "Дополнительно образование и курсы");
      ParserData blockEducationData = (ParserData) map.get("additionalEducation");
      blockEducationTemplate.put("contentInfo", getParagraphContentHtml(blockEducationData.getParserStorage()));
      blockEducationTemplate.put("additionalBlock", "");
      String additionalEducationTemplate = setSection(blockEducationTemplate);
      htmlPage.append(additionalEducationTemplate);
    }
  }

  private void setBlockSkills() throws AbstractGeneratorException, IOException {
    if (map.get("skills") != null) {
      HashMap<String, String> blockSkillsTemplate = new HashMap<>();
      blockSkillsTemplate.put("template", BLOCK_SECTION_TEMPLATE);
      blockSkillsTemplate.put("cardInfoClass", "card-info-block-skills");
      blockSkillsTemplate.put("cardIcon", "<i class=\"fas fa-keyboard\"></i>");
      blockSkillsTemplate.put("cardName", "Навыки");
      ParserData blockSkillsData = (ParserData) map.get("skills");
      blockSkillsTemplate.put("contentInfo", getParagraphContentHtml(blockSkillsData.getParserStorage()));
      blockSkillsTemplate.put("additionalBlock", "");
      String skillsTemplate = setSection(blockSkillsTemplate);
      htmlPage.append(skillsTemplate);
    }
  }

  private void setBlockCodeExample() throws AbstractGeneratorException, IOException {
    if (map.get("codeExample") != null) {
      HashMap<String, String> blockExampleCodeTemplate = new HashMap<>();
      blockExampleCodeTemplate.put("template", BLOCK_SECTION_TEMPLATE);
      blockExampleCodeTemplate.put("cardInfoClass", "card-info-block-code");
      blockExampleCodeTemplate.put("cardIcon", "<i class=\"fas fa-code\"></i>");
      blockExampleCodeTemplate.put("cardName", "Пример кода");
      blockExampleCodeTemplate.put("contentInfo", getExampleCodeList());
      blockExampleCodeTemplate.put("additionalBlock", "");
      String skillsTemplate = setSection(blockExampleCodeTemplate);
      htmlPage.append(skillsTemplate);
    }
  }

  private String getExampleCodeList() {
    StringBuilder exampleLinks = new StringBuilder();
    ParserData linkExampleData = (ParserData) map.get("codeExample");
    HashSet<String> linksMap = linkExampleData.getParserStorage();
    exampleLinks.append("<ul>");
    for (String link : linksMap) {
      exampleLinks.append("<li>");
      exampleLinks.append("<a href=\"");
      exampleLinks.append(link);
      exampleLinks.append("\">");
      exampleLinks.append(link);
      exampleLinks.append("<a>");
      exampleLinks.append("</li>");
    }
    exampleLinks.append("</ul>");
    return exampleLinks.toString();
  }

  private String getParagraphContentHtml(HashSet<String> paragraphs) {
    StringBuilder paragraphBuilder = new StringBuilder();
    for (String paragraph : paragraphs) {
      paragraphBuilder.append("<p>");
      paragraphBuilder.append(paragraph);
      paragraphBuilder.append("</p>");
    }
    return paragraphBuilder.toString();
  }

  private String getPhoto() {
    String avatar = "";
    if (map.get("avatar") != null) {
      ParserData avatarData = (ParserData) map.get("avatar");
      HashSet<String> avatarSet = avatarData.getParserStorage();
      for (String avatarList : avatarSet) {
        avatar = avatarList;
        break;
      }
    } else {
      avatar = "https://static1.squarespace.com/static/554b8146e4b00d559e90367f/t/591f91cab3db2b2e45f20061/1495241163408/NoPhoto.png?format=300w";
    }
    return "<a class=\"fancybox\" href=\"" + avatar + "\"><img src=\"" + avatar + "\" alt=\"\"class=\"img-relative\"></a>";
  }

  private String getPersonalContentHtml() {
    LinkedHashMap<String, HashSet> personalContentMap = new LinkedHashMap<>();
    if (map.get("fio") != null) {
      ParserData fio = (ParserData) map.get("fio");
      personalContentMap.put(translationKey("fio"), fio.getParserStorage());
    }
    if (map.get("dov") != null) {
      ParserData dob = (ParserData) map.get("dob");
      personalContentMap.put(translationKey("dob"), dob.getParserStorage());
    }
    if (map.get("email") != null) {
      ParserData email = (ParserData) map.get("email");
      personalContentMap.put(translationKey("email"), email.getParserStorage());
    }
    if (map.get("skype") != null) {
      ParserData skype = (ParserData) map.get("skype");
      personalContentMap.put(translationKey("skype"), skype.getParserStorage());
    }
    if (map.get("phone") != null) {
      ParserData phone = (ParserData) map.get("phone");
      personalContentMap.put(translationKey("phone"), phone.getParserStorage());
    }
    if (map.get("vk") != null) {
      ParserData vk = (ParserData) map.get("vk");
      personalContentMap.put(translationKey("vk"), vk.getParserStorage());
    }
    return getHtmlSectionInfoList(personalContentMap);
  }

  private String translationKey(String key) {
    return translate.get(key.toLowerCase()) != null ? translate.get(key.toLowerCase()) : key;
  }

  private String getHtmlSectionInfoList(LinkedHashMap<String, HashSet> list) {
    StringBuilder personalContentBuilder = new StringBuilder();
    personalContentBuilder.append("<ul>");
    for (Map.Entry entry : list.entrySet()) {
      HashSet<String> listMap = (HashSet<String>) entry.getValue();
      for (String listInfo : listMap) {
        personalContentBuilder.append("<li><span>");
        personalContentBuilder.append(entry.getKey());
        personalContentBuilder.append(":");
        personalContentBuilder.append("</span>");
        personalContentBuilder.append(setLink(listInfo));
        personalContentBuilder.append("</li>");
      }
    }
    personalContentBuilder.append("</ul>");
    return personalContentBuilder.toString();
  }

  private String setLink(String text) {
    return text.contains("http") ? "<a href=\"" + text + "\">" + text + "</a>" : text;
  }

  private String setSection(HashMap sectionParameters) throws AbstractGeneratorException, IOException {
    isTemplate((String) sectionParameters.get("template"));
    String blockPersonalInfoTemplate = getTemplate((String) sectionParameters.get("template"));
    blockPersonalInfoTemplate = replaceCardInfoClass(blockPersonalInfoTemplate, (String) sectionParameters.get("cardInfoClass"));
    blockPersonalInfoTemplate = replaceCardIcon(blockPersonalInfoTemplate, (String) sectionParameters.get("cardIcon"));
    blockPersonalInfoTemplate = replaceCardName(blockPersonalInfoTemplate, (String) sectionParameters.get("cardName"));
    blockPersonalInfoTemplate = replaceCardContentInfo(blockPersonalInfoTemplate, (String) sectionParameters.get("contentInfo"));
    blockPersonalInfoTemplate = replaceAdditionalBlock(blockPersonalInfoTemplate, (String) sectionParameters.get("additionalBlock"));
    return blockPersonalInfoTemplate;
  }

  private String replaceAdditionalBlock(String text, String additionalContent) {
    return replaceText(text, "{{additional-block}}", additionalContent);
  }

  private String replaceCardContentInfo(String text, String contentInfo) {
    return replaceText(text, "{{content-info}}", contentInfo);
  }

  private String replaceCardName(String text, String cardName) {
    return replaceText(text, "{{card-name}}", cardName);
  }

  private String replaceCardInfoClass(String text, String cardClass) {
    return replaceText(text, "{{card-info-class}}", cardClass);
  }

  private String replaceCardIcon(String text, String iconCard) {
    return replaceText(text, "{{card-icon}}", iconCard);
  }

  private String replaceTitle(String text, String title) {
    return replaceText(text, "{{title}}", title);
  }

  private String replaceText(String text, String oldValue, String newValue) {
    return text.replace(oldValue, newValue);
  }

  private void isTemplate(String templateName) throws AbstractGeneratorException {
    InputStream path = getTemplatePath(templateName);
    isExistsTemplate(path);
  }

  private void isExistsTemplate(InputStream file) throws AbstractGeneratorException {
    if (file == null) {
      throw new GeneratorUseException("Resource templates not found");
    }
  }

  private String getTemplate(String templateName) throws IOException {
    InputStream path = getTemplatePath(templateName);
    StringBuilder templateBuilder = new StringBuilder();
    InputStreamReader fileReader = getInputStreamReader(path);
    BufferedReader reader = new BufferedReader(fileReader);
    while (reader.ready()) {
      String line = reader.readLine();
      templateBuilder.append(line);
    }
    closeInputStreamReader(fileReader);
    closeBufferedReader(reader);
    return templateBuilder.toString();
  }

  private InputStream getTemplatePath(String templateName) {
    return getClass().getResourceAsStream("/html-templates/" + templateName);
  }

  private InputStreamReader getInputStreamReader(InputStream file) {
    return new InputStreamReader(file, StandardCharsets.UTF_8);
  }

  private void closeInputStreamReader(InputStreamReader stream) throws IOException {
    stream.close();
  }

  private void closeBufferedReader(BufferedReader reader) throws IOException {
    reader.close();
  }
}
