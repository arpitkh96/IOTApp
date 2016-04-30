package com.example.iot;

/**
 * Created by arpitkh996 on 23-04-2016.
 */

public class DataIncomingHolder{
    String code;
    int pin,mode;
    public DataIncomingHolder(String code, int pin, int mode) {
        super();
        this.code = code;
        this.pin = pin;
        this.mode = mode;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        String a="{\"dataIncomingHolder\":{\"code\":\""+code+ "\",\"mode\":"+mode+ ",\"pin\":"+pin+"}}";
    return a;
    }
}

