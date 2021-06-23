package com.ykb.vacation.language;

import com.ykb.vacation.dto.VoidResponse;

import java.io.IOException;
import java.util.Properties;

public class LanguageFactory {

    public static VoidResponse convert(VoidResponse voidResponse, String lang) {
        try {
            Properties props = new Properties();
            if (lang.equals("en"))
                props.load(LanguageFactory.class.getResourceAsStream("/language/lang.en.properties"));
            else
                props.load(LanguageFactory.class.getResourceAsStream("/language/lang.tr.properties"));

            voidResponse.setDescription(props.get(voidResponse.getKey()).toString());
            return voidResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return voidResponse;
    }

}
