package com.ferenc.radio_sammlung.data;

import com.ferenc.radio_sammlung.radio.Radio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AssetReader {


    public List<Radio> ReadRadioList(InputStreamReader isr, String separator) throws IOException {

        List<Radio> radios = new ArrayList<Radio>();

        try (BufferedReader br = new BufferedReader(isr)){

            br.readLine();

            while (br.ready()){

                String[] row = br.readLine().split(separator);

                radios.add(new Radio(Integer.parseInt(row[0]), row[1], row[2], row[3]));

            }
            br.close();

        } catch (Exception e) {
            throw new IOException("Radioliste konnte nicht geladen werden! "+e.getMessage());
        }


        return radios;

    }
}
