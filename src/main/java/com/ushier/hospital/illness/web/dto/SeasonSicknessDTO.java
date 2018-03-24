package com.ushier.hospital.illness.web.dto;

public class SeasonSicknessDTO {
    private Integer sicknessId;
    private String sicknessName;
    private int number;

    public Integer getSicknessId() {
        return sicknessId;
    }

    public void setSicknessId(Integer sicknessId) {
        this.sicknessId = sicknessId;
    }

    public String getSicknessName() {
        return sicknessName;
    }

    public void setSicknessName(String sicknessName) {
        this.sicknessName = sicknessName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
