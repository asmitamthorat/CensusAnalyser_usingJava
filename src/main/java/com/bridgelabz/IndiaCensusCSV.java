package com.bridgelabz;
import com.opencsv.bean.CsvBindByName;
public class IndiaCensusCSV {

    //public String State;
    @CsvBindByName(column = "State")
    public String stateName;

    @CsvBindByName(column = "Population",required = true)
    public double population;

    @CsvBindByName(column = "AreaInSqKm")
    public double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public double densityPerSqKm;

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