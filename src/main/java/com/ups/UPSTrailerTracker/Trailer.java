package com.ups.UPSTrailerTracker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Manages information for each Trailer object.
@Entity
public class Trailer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public static final double UNLOAD_RATE = 0.33; //packages per second.

    //*******************************************************//
    //The following fields are determined from the excel file//
    //*******************************************************//

    //Trailer Identification.
    private int identificationNumber; //Trailer ID number.
    private int originNumber; //Origin center number.

    //Volume Distribution.
    private int volume; //Total amount of pieces.
    private int smalls; //Total amount of small bagged packages.
    private int bags; //Total amount of bags.
    private int handles; //How many handles the unloader will have.

    //Pre-determined plan times.
    private double planHours; //Amount of times set for unloader to finish trailer.
    private double planStart; //Planned start time.
    private double planFinish; //Planned finish time.

    //*************************************************//
    //The following fields are determined by the client//
    //*************************************************//

    //Door Information
    private int bayDoorNumber; //Bay door on which the trailer is parked.
    private double currentPercentage; //Percentage upon opening.

    //Actual plan times.
    private double actualStart;
    private boolean warningReady;
    private double actualFinish;

    //Progress percentage intervals.
    private double minute30;
    private double minute60;
    private double minute90;
    private double timeCalledClear;



    public Trailer(double[] trailerInfo) {
        identificationNumber = (int) trailerInfo[0];
        originNumber = (int) trailerInfo[1];
        volume = (int) trailerInfo[2];
        smalls = (int) trailerInfo[3];
        bags = (int) trailerInfo[4];
        handles = (int) trailerInfo[5];
        planHours = trailerInfo[6];

        //Column 0 : Trailer Number : String
        //Column 1 : Origin Number : Double
        //Column 2 : Volume Number : Double
        //Column 3 : Smalls Number : Double
        //Column 4 : Number of Bags : Double
        //Column 5 : Number of Handles : Double
        //Column 6 : Planned Hours : Double
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public int getOriginNumber() {
        return originNumber;
    }

    public int getVolume() {
        return volume;
    }

    public int getSmalls() {
        return smalls;
    }

    public int getBags() {
        return bags;
    }

    public int getHandles() {
        return handles;
    }

    public double getPlanHours() {
        return planHours;
    }

    public double getPlanStart() {
        return planStart;
    }

    public double getPlanFinish() {
        return planFinish;
    }

    public int getBayDoorNumber() {
        return bayDoorNumber;
    }

    public double getCurrentPercentage() {
        return currentPercentage;
    }

    public double getActualStart() {
        return actualStart;
    }

    public boolean isWarningReady() {
        return warningReady;
    }

    public double getActualFinish() {
        return actualFinish;
    }

    public double getMinute30() {
        return minute30;
    }

    public double getMinute60() {
        return minute60;
    }

    public double getMinute90() {
        return minute90;
    }

    public double getTimeCalledClear() {
        return timeCalledClear;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void setOriginNumber(int originNumber) {
        this.originNumber = originNumber;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setSmalls(int smalls) {
        this.smalls = smalls;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public void setHandles(int handles) {
        this.handles = handles;
    }

    public void setPlanHours(double planHours) {
        this.planHours = planHours;
    }

    public void setPlanStart(double planStart) {
        this.planStart = planStart;
    }

    public void setPlanFinish(double planFinish) {
        this.planFinish = planFinish;
    }

    public void setBayDoorNumber(int bayDoorNumber) {
        this.bayDoorNumber = bayDoorNumber;
    }

    public void setCurrentPercentage(double currentPercentage) {
        this.currentPercentage = currentPercentage;
    }

    public void setActualStart(double actualStart) {
        this.actualStart = actualStart;
    }

    public void setWarningReady(boolean warningReady) {
        this.warningReady = warningReady;
    }

    public void setActualFinish(double actualFinish) {
        this.actualFinish = actualFinish;
    }

    public void setMinute30(double minute30) {
        this.minute30 = minute30;
    }

    public void setMinute60(double minute60) {
        this.minute60 = minute60;
    }

    public void setMinute90(double minute90) {
        this.minute90 = minute90;
    }

    public void setTimeCalledClear(double timeCalledClear) {
        this.timeCalledClear = timeCalledClear;
    }
}

