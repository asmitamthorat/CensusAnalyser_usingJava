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

    public int loadIndiaCensusDataUsingCommomCsv(String indiaCensusCsvfile)  throws CensusAnalyserException{
        try(Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            //List<IndiaStateCodeCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaStateCodeCSV.class);
            Iterator<IndiaStateCodeCSV> censusCSVIterator=CommonCsv.CommonCSVBuilder(reader,IndiaStateCodeCSV.class);
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



    public String getStateWiseSortedCensusData(String indiaCensusCsvfile) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> censusCSVComparator=Comparator.comparing(census -> census.stateName);
            sort( censusCSVList,censusCSVComparator);
            String sortedStateCensusJson=new Gson().toJson(censusCSVList);
            System.out.println(sortedStateCensusJson);
            return sortedStateCensusJson;

        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }


    public String getStateCodeWise_SortedCensusData(String stateCodesCsvfile) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(stateCodesCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCodeCSV> StateCodeCSVList=csvBuilder.getCSVFileList(reader,IndiaStateCodeCSV.class);
            Comparator<IndiaStateCodeCSV> StateCodeCSVComparator=Comparator.comparing(census -> census.StateCode);
            sort(StateCodeCSVList,StateCodeCSVComparator);
            String sortedStateCensusJson=new Gson().toJson(StateCodeCSVList);

            return sortedStateCensusJson;

        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }

    private <E> void sort(List<E> csvList, Comparator<E> comparator) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                E census1 = csvList.get(j);
                E census2 = csvList.get(j + 1);
                if (comparator.compare(census1, census2) > 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }

    private <E> void sortDecending(List<E> csvList, Comparator<E> comparator) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                E census1 = csvList.get(j);
                E census2 = csvList.get(j + 1);
                if (comparator.compare(census1, census2) < 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }


    public String getPopulationWiseSortedCensusData(String indiaCensusCsvfile) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> censusCSVComparator=Comparator.comparing(census -> census.population);
            sortDecending( censusCSVList,censusCSVComparator);
            String sortedStateCensusJson=new Gson().toJson(censusCSVList);
            System.out.println(sortedStateCensusJson);
            return sortedStateCensusJson;

        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }

    }

    public String getPopulationDensityWiseSortedCensusData(String indiaCensusCsvfile) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> censusCSVComparator=Comparator.comparing(census -> census.densityPerSqKm);
            sort( censusCSVList,censusCSVComparator);
            String sortedStateCensusJson=new Gson().toJson(censusCSVList);
            System.out.println(sortedStateCensusJson);
            return sortedStateCensusJson;

        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException runtimeException){
            throw new CensusAnalyserException(runtimeException.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        }
    }

    public String getStateAreaWiseSortedCensusData(String indiaCensusCsvfile) throws CensusAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCsvfile))){
            ICSVBuilder  csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> censusCSVComparator=Comparator.comparing(census -> census.areaInSqKm);
            sortDecending( censusCSVList,censusCSVComparator);
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
