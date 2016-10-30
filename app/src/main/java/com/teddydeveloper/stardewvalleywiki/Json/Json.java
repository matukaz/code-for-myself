package com.teddydeveloper.stardewvalleywiki.Json;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Matu on 25.10.2016.
 */

public class Json {

    public static String JSONFileToString(Context context, String fileLocation){
        // Read JSON file from assets
        StringBuilder string = new StringBuilder();
        BufferedReader reader = null;
        try {

            String jsonFileToString;
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileLocation)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                string.append(mLine);

            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return string.toString();
    }
}
