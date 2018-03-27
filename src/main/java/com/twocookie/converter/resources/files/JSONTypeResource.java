package com.twocookie.converter.resources.files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twocookie.converter.exception.SyntacticValidatorException;
import com.twocookie.converter.exception.ValidatorException;
import com.twocookie.converter.interfaces.LineParser;
import com.twocookie.converter.resources.FileResource;
import com.twocookie.converter.storage.JSONStorage;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONTypeResource extends AbstractTypeResource {

  private FileResource fileResource;
  private Pattern jsonPattern = Pattern.compile("^(\\s+)?\\{.*\\}(\\s+)?$", Pattern.MULTILINE|Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
  private ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
  private StringBuilder jsonBuilder = new StringBuilder();
  private List<JSONStorage> jsonStorageList = null;
  public JSONTypeResource(FileResource fileResource) {
    super(fileResource);
    this.fileResource = fileResource;
  }

  @Override
  public void isCorrectFormat() throws IOException, ValidatorException {
    getJSONContent();
    formatParse(jsonBuilder.toString());
  }

  @Override
  public HashSet<String> getDataByKey(String key) throws IOException {
    createJSONInstance();
    HashSet<String> values = new HashSet<>();
    for (JSONStorage list : jsonStorageList) {
      values.addAll(list.getValue(key));
    }
    return values;
  }

  private void createJSONInstance() throws IOException {
    if (jsonStorageList == null) {
      jsonStorageList = mapper.readValue(fileResource.getFile(), new TypeReference<List<JSONStorage>>() {
      });
    }
  }

  private void getJSONContent () throws IOException, ValidatorException {
    LineParser lineParser = this::getJson;
    fileResource.readFile(lineParser);
  }

  private void getJson(String line) {
    jsonBuilder.append(line);
  }

  private void formatParse(String line) throws ValidatorException {
    if (!isCorrectJSONFormat(line)) {
      throw new SyntacticValidatorException("Incorrect format from json file");
    }
  }

  private boolean isCorrectJSONFormat(String line) {
    Matcher matcher = jsonPattern.matcher(line);
    return matcher.find(0);
  }
}


