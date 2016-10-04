package com.company;

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
    private List<String> types;
    private List<Object> values;

    public Command() {
        types = new ArrayList<>();
        values = new ArrayList<>();
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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> object) {
        this.values = object;
    }

    public void addTypes(String s) {
        if(null != this.types) {
            this.types.add(s);
        }
    }

    public void addValues(Object s) {
        if(null != this.values) {
            this.values.add(s);
        }
    }
}
