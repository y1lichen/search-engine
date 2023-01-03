package utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Validator {
    static public boolean isValidURL(String url) {

        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    
        return true;
    }
}
