package com.example.smartadns;

public class MapInfo {

    public String latitude;
    public String longitude;

    public String getAccidentStatus() {
        return accidentStatus;
    }

    public void setAccidentStatus(String accidentStatus) {
        this.accidentStatus = accidentStatus;
    }

    public String accidentStatus;

    public MapInfo() {
    }

    public MapInfo(String latitude, String longitude, String accidentStatus) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accidentStatus = accidentStatus;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}