package com.bridgelabz;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try{
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder=new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean=csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator=csvToBean.iterator();
            Iterable<IndiaCensusCSV> csvIterator = () -> censusCSVIterator;
            int num_of_entries= (int) StreamSupport.stream(csvIterator.spliterator(),false).count();
            return num_of_entries;

        }catch(IOException ioException){
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);

        }catch (IllegalStateException illegalStateException){
            throw new CensusAnalyserException(illegalStateException.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);

        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }
    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try{
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder=new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCodeCSV> csvToBean=csvToBeanBuilder.build();
            Iterator<IndiaStateCodeCSV> censusCSVIterator=csvToBean.iterator();
            Iterable<IndiaStateCodeCSV> csvIterator = () -> censusCSVIterator;
            int num_of_entries= (int) StreamSupport.stream(csvIterator.spliterator(),false).count();
            return num_of_entries;

        }catch(IOException ioException){
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);

        }catch (IllegalStateException illegalStateException){
            throw new CensusAnalyserException(illegalStateException.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);

        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }




}
