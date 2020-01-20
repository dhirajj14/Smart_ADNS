package com.example.smartadns;

public class ProductInfo {
    private long latitude, longitude;
    private int accidentStatus;

    public ProductInfo(long latitude, long longitude, int accidentStatus) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accidentStatus = accidentStatus;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public int getAccidentStatus() {
        return accidentStatus;
    }

    public void setAccidentStatus(int accidentStatus) {
        this.accidentStatus = accidentStatus;
    }
}
