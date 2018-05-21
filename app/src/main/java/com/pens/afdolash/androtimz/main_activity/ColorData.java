package com.pens.afdolash.androtimz.main_activity;

/**
 * Created by afdol on 4/1/2018.
 */

public class ColorData {
    private String name;
    private String hexCode;
    private String textColor;
    private String sendCode;
    private String weight;

    public ColorData(String name, String hexCode, String textColor, String sendCode) {
        this.name = name;
        this.hexCode = hexCode;
        this.textColor = textColor;
        this.sendCode = sendCode;
    }

    public String getName() {
        return name;
    }

    public String getHexCode() {
        return hexCode;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getSendCode() {
        return sendCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
