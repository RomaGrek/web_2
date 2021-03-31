package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Information implements Serializable {
    private double coordinateX;
    private double coordinateY;
    private double valueR;
    private String runTime;
    private String localTime;
    private String result;

    public Information() {
        this(0.0, 0.0, 0.0, "", "", "");
    }

    public Information(double coordinateX, double coordinateY, double valueR, String runTime, String localTime, String result) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.valueR = valueR;
        this.runTime = runTime;
        this.localTime = localTime;
        this.result = result;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public double getValueR() {
        return valueR;
    }

    public String getLocalTime() {
        return localTime;
    }

    public String getResult() {
        return result;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public void setValueR(double valueR) {
        this.valueR = valueR;
    }


}
