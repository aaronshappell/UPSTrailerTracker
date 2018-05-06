package com.ups.UPSTrailerTracker;

//Manages information for each Trailer object.
public class Trailer {
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
}

