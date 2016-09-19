import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 19/09/2016.
 *
 * fonction#c1234#setNomProfesseur#java.lang.String:Labonté
 * Command#identificator#function#type:value
 */
public class Command implements Serializable {
    private  static  final  long serialVersionUID =  1350092881346723535L;
    private String command;
    private String identificator;
    private String function;
    private List<Map<String,String>> typeValue;

    public Command() {
        typeValue = new ArrayList<>();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public List<Map<String, String>> getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(List<Map<String, String>> typeValue) {
        this.typeValue = typeValue;
    }

    public void addTypeValue(String type,String value) {
        Map<String,String> map = new HashMap<>();
        map.put("type",type);
        map.put("value",value);
        typeValue.add(map);
    }
}
