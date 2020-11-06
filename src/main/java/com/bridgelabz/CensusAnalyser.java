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
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            Iterator<IndiaCensusCSV> censusCSVIterator=this.getCSVFileIterator(reader,IndiaCensusCSV.class);
            //Iterable<IndiaCensusCSV> csvIterator=()-> censusCSVIterator;
            int num_of_entries= this.getCount(censusCSVIterator);
            //int num_of_entries= (int) StreamSupport.stream(csvIterator.spliterator(),false).count();
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


    private <E> Iterator <E> getCSVFileIterator(Reader reader,Class csvClass) throws CensusAnalyserException{

        try{
            CsvToBeanBuilder<E> csvToBeanBuilder=new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean=csvToBeanBuilder.build();
            Iterator<E> censusCSVIterator=csvToBean.iterator();
            return censusCSVIterator;
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }

    }



    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {

        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            Iterator<IndiaStateCodeCSV> censusCSVIterator=this.getCSVFileIterator(reader,IndiaStateCodeCSV.class);
            //Iterable<IndiaStateCodeCSV> csvIterator=()-> censusCSVIterator;
            int num_of_entries= this.getCount(censusCSVIterator);
                  //  (int) StreamSupport.stream(csvIterator.spliterator(),false).count();
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
