package com.bridgelabz;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
           // Iterator<IndiaCensusCSV> censusCSVIterator=csvBuilder.getCSVFileIterator(reader,IndiaCensusCSV.class);
            //int num_of_entries= this.getCount(censusCSVIterator);
            return censusCSVList.size();

        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }

    private <E> int  getCount(Iterator<E> iterator){
        Iterable<E> csvIterable=()-> iterator;
        int numOfEntries=(int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return numOfEntries;
    }


    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCodeCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaStateCodeCSV.class);
            //Iterator<IndiaStateCodeCSV> censusCSVIterator=csvBuilder.getCSVFileIterator(reader,IndiaStateCodeCSV.class);
            //int num_of_entries= this.getCount(censusCSVIterator);
            return censusCSVList.size();

        } catch(IOException ioException){
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);

        }catch (IllegalStateException illegalStateException){
            throw new CensusAnalyserException(illegalStateException.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);

        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }

    private void sort(Comparator<IndiaCensusCSV> censusCSVComparator, List<IndiaCensusCSV> censusCSVList) {
        for(int i=0;i<censusCSVList.size()-1;i++){
            for(int j=0;j<censusCSVList.size()-i-1;j++){
                IndiaCensusCSV censusCSV1=censusCSVList.get(j);
                IndiaCensusCSV censusCSV2=censusCSVList.get(j+1);
                if(censusCSVComparator.compare(censusCSV1,censusCSV2)>0){
                    censusCSVList.set(j,censusCSV2);
                    censusCSVList.set(j+1,censusCSV2);
                }

            }
        }
    }


    public String getStateWiseSortedCensusData(String indiaCensusCsvfile) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> censusCSVComparator=Comparator.comparing(census -> census.stateName);
            sort(censusCSVComparator,censusCSVList);
            String sortedStateCensusJson=new Gson().toJson(censusCSVList);
            System.out.println(sortedStateCensusJson);
            return sortedStateCensusJson;

        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }


}
