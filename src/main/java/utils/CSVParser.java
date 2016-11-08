package main.java.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by ncapasso on 11/8/2016.
 */
public class CSVParser {
    public static final char SEMICOLON_SEP = ';';
    public static final char COMMA_SEP = ',';
    public static final char TAB_SEP = '\t';
    public static final char BAR_SEP = '|';
    public static final char SINGLE_QUOTE = '\'';
    public static final char DOUBLE_QUOTE = '\"';

    private char givenSeperatorChar;
    private char givenQuoteChar;

    private Path path = null;
    private File file = null;

    public CSVParser(){
        this(COMMA_SEP, DOUBLE_QUOTE);
    }

    public CSVParser(char fieldSep, char quoteSep){
        this.givenSeperatorChar = fieldSep;
        this.givenQuoteChar = quoteSep;
    }

    public CSVParser(char fieldSep){
        this(fieldSep, DOUBLE_QUOTE);

    }

    public CSVParser(File file){

    }

    public char getFieldSep(){
        return givenQuoteChar;
    }

    public char getSepChar(){
        return givenSeperatorChar;
    }

    public void setCSVFile(File givenFile){
        this.file = givenFile;
    }

    public File getCSVFile(){
        return this.file;
    }





}
