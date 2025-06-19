package fr.cci.desktop.configurations;

public class ApiConstants {

    private static ApiConstants _instance = null;

    private ApiConstants() {}

    public static ApiConstants getInstance() {
        if (_instance == null) {
            _instance = new ApiConstants();
        }
        return _instance;
    }

    private String API_URL = "http://localhost:8080/api";

    public String getAPI_URL() {
        return API_URL;
    }

    public String getAPI_URL(String url) {
        return API_URL + "/" + url;
    }
}
