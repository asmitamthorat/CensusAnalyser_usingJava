package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CensusAnalyzer_CommonCsvtest {
    private static final String  INDIA_CENSUS_CSVFILE="./src/test/resources/IndiaStateCensusData.csv";
    private static final String  STATE_CODES_CSVFILE="./src/test/resources/IndiaStateCode.csv";
    @Test
    public void givenStateCensusFile_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaCensusDataUsingCommomCsv(INDIA_CENSUS_CSVFILE);
        Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenStateCode_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaCensusDataUsingCommomCsv(STATE_CODES_CSVFILE);
        Assert.assertEquals(37, numOfRecords);

    }

}
