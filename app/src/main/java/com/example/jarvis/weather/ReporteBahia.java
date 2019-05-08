package com.example.jarvis.weather;

public class ReporteBahia extends Forecast {

    public ReporteBahia() {
        super();
        url = url + "id=3865086" + apiKey + "&units=metric";
    }
}
