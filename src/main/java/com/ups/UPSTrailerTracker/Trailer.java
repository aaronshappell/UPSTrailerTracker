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



    public Trailer(int[] trailerInfo, int[] volumeInfo, double[] planTimes) {

    }
}

