package com.bridgelabz;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.io.IOException;
import java.rmi.server.ExportException;

public class CensusAnalyzerTest {
    private static final String  STATECENSUS_CSVFILE="./src/test/resources/IndiaStateCensusData.csv";
    private static final String  STATECODES_CSVFILE="./src/test/resources/IndiaStateCode.csv";
    private static final String  WRONG_FILE="C:\\Users\\com\\StateCode";
    private static final String  STATECODES_CSVFILE_withDELIMITOR="./src/test/resources/IndiaStateCensusData_delimitorIssue.csv";
    private static final String  STATECODES_CSVFILEIndiaState_CensusData_unableToParse="./src/test/resources/IndiaStateCensusData_unableToParse.csv";



    @Test
    public void givenStateCensusFile_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(STATECENSUS_CSVFILE);
            Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenStateCensusFile_withWrongPath_shouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(WRONG_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }
    }

    @Test
    public void givenRightCensusFile_withDelimiterIssue_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(STATECODES_CSVFILE_withDELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }
    }


    @Test
    public void givenRightCensusFile_UnableToParse_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(WRONG_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }

    }

    @Test
    public void givenRightCensusFile_withNoHeader_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(STATECODES_CSVFILE_withDELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }

    }

    //=--------------------------

/*
    @Test
    public void givenStateCode_IfHasCorrectNumberOFRecord_shouldReturnTrue() throws IOException, CensusAnalyserException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATECODES_CSVFILE);
        // System.out.println(numOfRecords);
        Assert.assertEquals(37, numOfRecords);

    }


 */

    @Test
    public void givenSStateCodeFile_withWrongPath_shouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(WRONG_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }
    }


    @Test
    public void givenRightStateCodeFile_withDelimiter_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATECODES_CSVFILE_withDELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }

    }



    @Test
    public void givenRightStateCode_UnableToParse_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(WRONG_FILE);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,censusAnalyserException.type);
        }

    }

    @Test
    public void givenRightStateCodeFile_withNoHeader_whenReturnWrongOutput_ShouldThrowException(){
        try{
            CensusAnalyser censusAnalyser=new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeData(STATECODES_CSVFILE_withDELIMITOR);
        }catch (CensusAnalyserException  censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ISSUE,censusAnalyserException.type);
        }

    }



}
