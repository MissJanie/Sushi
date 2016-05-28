/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SushiIO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This is the class that defines the Sushi Package file for the most part.
 * God, I was hungry when I made this.
 * @author Admin
 */
public class definition {
    
    
    /* Basic header generator. */
    public static class Header {

        /**
         * File separator byte
         */
        public final static String FILE_SEPERATOR = "\u001C"; //File seperator byte (FS)
        /**
         * Data separator byte
         */
        public final static String DATA_SEPERATOR = "\u001D"; //Data seperator byte (GS)
        /**
         *  Header close byte
         */
        public final static String HEADER_CLOSE = "\u0004"; //Header close byte (EOT)
        /**
         * Header open byte
         */
        public final static String HEADER_OPEN = "\u0001"; //Header open byte (SOH)
        public String header; //This will be the header
        public String data; //This is the data.
        /**
         * Generates the header for the Sushi package.
         * @param f Path to files for packing
         * @throws SushiIO.definition.SushiIOException 
         */
        public Header(String[] f) throws SushiIOException{
            System.out.println("Generation header...");
            //Get data
            String[] tmp = new String[f.length];
            for (byte i = 0; i < f.length; ++i) {
                try {
                    tmp[i] = new String(Files.readAllBytes(Paths.get(f[i])), StandardCharsets.US_ASCII);
                } catch (IOException ex) {
                    Logger.getLogger(definition.class.getName()).log(Level.SEVERE, null, ex);
                    throw new SushiIOException("java.nio.file error.");
                }
            }
            this.data = "";
            int[] tmp_ = new int[f.length];
            for (byte i = 0; i < tmp.length; ++i) {
                tmp_[i] = tmp[i].length(); //This is for the byte position. 
                this.data = this.data + tmp[i]; //We need this for another class
            }
            //Build header
            this.header = "Sushi Package 0.1" + HEADER_OPEN;
            int cb = 0;
            int cb_ = 0;
            byte i = 0;
            for (i = 0; i < f.length-1; ++i) {
                cb_ = cb_ + tmp_[i]-1; //This is kinda drunk??
                this.header = this.header + f[i].split("/")[f[i].split("/").length - 1] + DATA_SEPERATOR + cb + DATA_SEPERATOR + cb_ + FILE_SEPERATOR;
                cb = cb + cb_ + 1;
            }
            cb_ = cb_ + tmp_[i];
            this.header = this.header + f[i].split("/")[f[i].split("/").length - 1] + DATA_SEPERATOR + cb + DATA_SEPERATOR + cb_;
            cb = cb + cb_ + 1;
            this.header = this.header + HEADER_CLOSE;
            System.out.println("Finished header generation.");
        }
    }
    
    public static class SushiData {
        String FileName;
        int StartByte;
        int EndByte;
        
        public SushiData(String s) {
            this.FileName = s.split(Header.DATA_SEPERATOR)[0];
            this.StartByte = Integer.parseInt(s.split(Header.DATA_SEPERATOR)[1]);
            this.EndByte = Integer.parseInt(s.split(Header.DATA_SEPERATOR)[2]);
        }
    }
    
    /* Exceptions for the library. Some may not be used until later. */
    public static class SushiFileException extends Exception{
        public SushiFileException(String s) {
            super(s);
        }
        public SushiFileException(Throwable c) {
            super(c);
        }
        public SushiFileException(String s, Throwable c) {
            super(s, c);
        }
    }
    
    public static class SushiEncryptionException extends Exception{
        public SushiEncryptionException(String s) {
            super(s);
        }
        public SushiEncryptionException(Throwable c) {
            super(c);
        }
        public SushiEncryptionException(String s, Throwable c) {
            super(s, c);
        }
    }
    
    public static class SushiChecksumException extends Exception{
        public SushiChecksumException(String s) {
            super(s);
        }
        public SushiChecksumException(Throwable c) {
            super(c);
        }
        public SushiChecksumException(String s, Throwable c) {
            super(s, c);
        }
    }
    
    public static class SushiIOException extends Exception{
        public SushiIOException(String s) {
            super(s);
        }
        public SushiIOException(Throwable c) {
            super(c);
        }
        public SushiIOException(String s, Throwable c) {
            super(s, c);
        }
    }
    
    public static class SuhsiNotImplimentedException extends Exception{
        public SuhsiNotImplimentedException(String s) {
            super(s);
        }
        public SuhsiNotImplimentedException(Throwable c) {
            super(c);
        }
        public SuhsiNotImplimentedException(String s, Throwable c) {
            super(s, c);
        }
    }
}
