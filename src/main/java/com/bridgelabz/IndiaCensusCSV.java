package com.bridgelabz;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    @CsvBindByName(column = "State")
    private String stateName;

    @CsvBindByName(column = "Population",required = true)
    private double population;

    @CsvBindByName(column = "AreaInSqKm")
    private double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private double densityPerSqKm;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "stateName='" + stateName + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}