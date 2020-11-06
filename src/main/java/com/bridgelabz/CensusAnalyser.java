package com.bridgelabz;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator=csvBuilder.getCSVFileIterator(reader,IndiaCensusCSV.class);
            int num_of_entries= this.getCount(censusCSVIterator);
            return num_of_entries;

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
            Iterator<IndiaStateCodeCSV> censusCSVIterator=csvBuilder.getCSVFileIterator(reader,IndiaStateCodeCSV.class);
            int num_of_entries= this.getCount(censusCSVIterator);
            return num_of_entries;

        } catch(IOException ioException){
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);

        }catch (IllegalStateException illegalStateException){
            throw new CensusAnalyserException(illegalStateException.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);

        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }
}
