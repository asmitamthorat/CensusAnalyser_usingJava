package com.bridgelabz;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;


public class CensusAnalyzerTest {
    private static final String  INDIA_CENSUS_CSVFILE="./src/test/resources/IndiaStateCensusData.csv";
    private static final String  STATE_CODES_CSVFILE="./src/test/resources/IndiaStateCode.csv";
    private static final String  WRONG_FILE_FORCSV_FILE="./src/test/resources/";
    private static final String  INDIA_CENSUS_CSVFILE_DELIMITOR="./src/test/resources/IndiaStateCensusData_delimitorIssue.csv";
    private static final String  STATE_CODES_CSVFILE_WITHDELIMITOR="./src/test/resources/IndiaStateCode1.csv";




    @Test
    public void givenStateCensusFile_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSVFILE);
            Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenStateCensusFile_withWrongPath_shouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(WRONG_FILE_FORCSV_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }
    }

    @Test
    public void givenRightCensusFile_withDelimiterIssue_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSVFILE_DELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }
    }


    @Test
    public void givenRightCensusFile_UnableToParse_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(WRONG_FILE_FORCSV_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }

    }

    @Test
    public void givenRightCensusFile_withNoHeader_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSVFILE_DELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }

    }


    @Test
    public void givenStateCode_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATE_CODES_CSVFILE);
        // System.out.println(numOfRecords);
        Assert.assertEquals(37, numOfRecords);

    }

    @Test
    public void givenSStateCodeFile_withWrongPath_shouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(WRONG_FILE_FORCSV_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }
    }


    @Test
    public void givenRightStateCodeFile_withDelimiter_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATE_CODES_CSVFILE_WITHDELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }

    }



    @Test
    public void givenRightStateCode_UnableToParse_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(WRONG_FILE_FORCSV_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }

    }

    @Test
    public void givenRightStateCodeFile_withNoHeader_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATE_CODES_CSVFILE_WITHDELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }

    }



}
