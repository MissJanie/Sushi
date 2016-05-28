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
import SushiIO.definition.*;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Admin
 */
public class decompiler {
    public static class SushiD {
        /**
         * Unpacks files.
         * @param in Path to Sushi package.
         * @param out Output folder
         * @throws SushiIO.definition.SushiIOException 
         */
        public static void DecompileToFiles(String in, String out) throws SushiIOException {
            System.out.println("Begining unpack...");
            String raw;
            try {
                raw = new String(Files.readAllBytes(Paths.get(in)), StandardCharsets.US_ASCII);
            } catch (IOException ex) {
                Logger.getLogger(decompiler.class.getName()).log(Level.SEVERE, null, ex);
                raw = null;
            }
            String h = raw.split(Header.HEADER_OPEN)[1].split(Header.HEADER_CLOSE)[0];
            String b = raw.split(Header.HEADER_OPEN)[1].split(Header.HEADER_CLOSE)[1];
            /*for (int i = 1; i < raw.split(Header.HEADER_OPEN)[1].split(Header.HEADER_CLOSE).length; ++i) {
                b = b + 
            }*/
            System.out.println(b);
            SushiData[] d;
            String[] tmp = h.split(Header.FILE_SEPERATOR);
            d = new SushiData[tmp.length];
            for (byte i = 0; i < tmp.length; ++i) {
                System.out.println(tmp[i]);
                d[i] = new SushiData(tmp[i]);
            }
            String[] wd = new String[d.length];
            for (byte i = 0; i < d.length; ++i) {
                String t = "";
                for (int x = d[i].StartByte; x < d[i].EndByte; ++x) {
                    t = t + b.charAt(x);
                }
                wd[i] = t;
            }
            for (byte i = 0; i < wd.length; ++i) {
                String path = out + "/" + d[i].FileName;
                String content = wd[i];
                try {
                    Paths.get(path).toFile().createNewFile();
                    Files.write(Paths.get(path), content.getBytes(StandardCharsets.US_ASCII), StandardOpenOption.CREATE);
                } catch (IOException ex) {
                    Logger.getLogger(decompiler.class.getName()).log(Level.SEVERE, null, ex);
                    throw new SushiIOException("Error writing files");
                }
            }
            System.out.println("Decompile complete.");
        }
        
        /*public static void ExtractFile(String in, String f, String out) throws SuhsiNotImplimentedException {
            throw new SuhsiNotImplimentedException("Not implimented");
        }*/
    }
}
