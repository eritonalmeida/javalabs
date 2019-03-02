package app.model.service;

import java.util.ResourceBundle;

public class ResourceBundleService {

    public static String getString(String baseName, String key) {

        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        return bundle.getString(key);
    }

    public static String getValidationMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages");
        return bundle.getString(key);
    }
}
