package com.bridgelabz;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {
    @CsvBindByName(column = "SrNo", required = true)
    public int SrNo;

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Name", required = true)
    public String Name;

    @CsvBindByName(column = "TIN", required = true)
    public String TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public int StateCode;

    @Override
    public String toString() {
        return "IndiaStateCodeCSV{" +
                "SrNo=" + SrNo +
                ", State='" + State + '\'' +
                ", Name='" + Name + '\'' +
                ", TIN='" + TIN + '\'' +
                ", StateCode=" + StateCode +
                '}';
    }
}
