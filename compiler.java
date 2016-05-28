/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SushiIO;

import SushiIO.definition.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class compiler {
    public static class SushiC {
        /**
         * Compiles the Sushi file and writes it to the drive. Preferably a .sushi file.
         * @param f Files to add
         * @param o File to output to.
         * @throws SushiIO.definition.SushiIOException 
         */
        public static void Compile(String[] f, String o) throws SushiIOException {
            System.out.println("Begininning compile...");
            Header h = new Header(f);
            String head = h.header;
            String body = h.data;
            String content = head + body;
            String path = o;
            try {
                Files.write(Paths.get(path), content.getBytes(StandardCharsets.US_ASCII), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(compiler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Compile complete.");
        }
    }
}
