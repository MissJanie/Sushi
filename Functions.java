/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SushiIO;

import SushiIO.definition.Header;
import SushiIO.definition.SushiData;

/**
 *
 * @author Admin
 */
public class Functions {
    public static SushiData[] ReadHeader(String r) {
        String h = "";
        boolean f = false;
        for (int i = 0; i < r.length() || f; ++i) {
            if (!f) {
                h = h + r.charAt(i);
            }
            String tmp = "" + r.charAt(i);
            if (tmp == Header.HEADER_CLOSE) {
                f = true;
            }
        }
        String[] tmp = h.split(Header.HEADER_OPEN)[1].split(Header.FILE_SEPERATOR);
        SushiData[] d = new SushiData[tmp.length];
        for (byte i = 0; i < tmp.length; ++i) {
            System.out.println(tmp[i]);
            d[i] = new SushiData(tmp[i]);
        }
        return d;
    }
    public static String ReadBody(String r) {
        String b = "";
        boolean f = false;
        for (int i = 0; i < r.length(); ++i) {
            if (f) {
                b = b + r.charAt(i);
            }
            String tmp = "" + r.charAt(i);
            if (tmp == Header.HEADER_CLOSE) {
                f = true;
            }
        }
        return b;
    }
}
