package com.bridgelabz;
import com.google.gson.Gson;
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
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
       String sortedCensusData= censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSVFILE);
       IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh" ,censusCSV[0].stateName);
    }


    //sign should be changed
    @Test
    public void givenIndianCensusData_WhenSortedOnSatePopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        String sortedCensusData= censusAnalyser.getPopulationWiseSortedCensusData(INDIA_CENSUS_CSVFILE);
        IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(1.99812341E8 ,censusCSV[0].population,0.00001);
    }
//sign should be changed
    @Test
    public void givenIndianCensusData_WhenSortedOnSateArea_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        String sortedCensusData= censusAnalyser.getStateAreaWiseSortedCensusData(INDIA_CENSUS_CSVFILE);
        IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(6.8621012E7 ,censusCSV[0].population,0.00001);
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        String sortedCensusData= censusAnalyser.getPopulationDensityWiseSortedCensusData(INDIA_CENSUS_CSVFILE);
        IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
        Assert.assertEquals(52.0 ,censusCSV[0].densityPerSqKm,0.000001);
    }


    //--------------------------

    @Test
    public void givenIndianStateCodeData_WhenSorted_ShouldReturnSortedResult() throws CensusAnalyserException{
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        String sortedStateCodeData = censusAnalyser.getStateCodeWise_SortedCensusData(STATE_CODES_CSVFILE);
        IndiaStateCodeCSV[] censusCSV=new Gson().fromJson(sortedStateCodeData,IndiaStateCodeCSV[].class);
        Assert.assertEquals("AN" ,censusCSV[0].StateCode);
    }


    @Test
    public void givenStateCode_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATE_CODES_CSVFILE);
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
