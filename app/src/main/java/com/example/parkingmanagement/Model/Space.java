package com.example.parkingmanagement.Model;

import java.util.List;

public class Space {
    String ownerName, hourlyCharge, isCameraAvailable, phoneNumber;
    List allowedVehicles;

    public Space(String ownerName, String hourlyCharge, String isCameraAvailable, String phoneNumber, List allowedVehicles) {
        this.ownerName = ownerName;
        this.hourlyCharge = hourlyCharge;
        this.isCameraAvailable = isCameraAvailable;
        this.phoneNumber = phoneNumber;
        this.allowedVehicles = allowedVehicles;
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getHourlyCharge() {
        return hourlyCharge;
    }

    public void setHourlyCharge(String hourlyCharge) {
        this.hourlyCharge = hourlyCharge;
    }

    public String getIsCameraAvailable() {
        return isCameraAvailable;
    }

    public void setIsCameraAvailable(String isCameraAvailable) {
        this.isCameraAvailable = isCameraAvailable;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(List allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }
}
