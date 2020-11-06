package com.bridgelabz;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


class CommonCsv {

    public static <E> Iterator<E> CommonCSVBuilder(Reader reader, Class<E> csvClass) throws IOException {
        try{
            CSVParser csvParser= CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
            return (Iterator<E>) csvParser.iterator();
        } catch (Exception e) {
                throw new IOException();
        }
    }
}



