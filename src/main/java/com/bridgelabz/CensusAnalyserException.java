package com.bridgelabz;

public class CensusAnalyserException extends Exception {
    public CensusAnalyserException(ExceptionType censusFileProblem) {
    }

    enum ExceptionType{
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE,DELIMITER_ISSUE;
    }
    ExceptionType type;


    public CensusAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }


}
