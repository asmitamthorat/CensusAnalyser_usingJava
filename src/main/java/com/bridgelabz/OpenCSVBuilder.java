package com.bridgelabz;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder implements ICSVBuilder{
    @Override
    public <E>Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
            return this.getCSVBean(reader,csvClass).iterator();

    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        return this.getCSVBean(reader,csvClass).parse();
    }

    private <E>CsvToBean  getCSVBean(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try{
            CsvToBeanBuilder<E> csvToBeanBuilder=new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            //CsvToBean<E> csvToBean=
            //Iterator<E> censusCSVIterator=csvToBean.iterator();
            return csvToBeanBuilder.build();
        }catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }

    }
}
