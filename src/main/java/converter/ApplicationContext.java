package converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class ApplicationContext {

  private static ApplicationContext instance;
  private ArrayList<String> input = new ArrayList<>();
  private LinkedHashMap<String, String> output = new LinkedHashMap<>();
  private HashMap<String, ParserData> parseData = new HashMap<>();

  public HashMap getParseData() {
    return parseData;
  }

  public void setData(String keySet, ParserData valSet) {
    this.parseData.put(keySet, valSet);
  }

  private ApplicationContext(){}

  public static ApplicationContext getInstance() {
    if (instance == null) {
      instance = new ApplicationContext();
    }
    return instance;
  }

  public void addInput(String[] inputArguments) {
    input.addAll(Arrays.asList(inputArguments));
  }

  public ArrayList<String> getInput() {
    return input;
  }

  public void addOutput(String modeArgument, String outputArgument ) {
    output.put(modeArgument, outputArgument);
  }

  public LinkedHashMap<String, String> getOutput() {
    return output;
  }

}
