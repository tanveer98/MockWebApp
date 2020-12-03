package com.tanveer.weather;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface WeatherServiceInterface {
    boolean validateParams(Map<String, String[]> requestParamMap, HttpServletResponse response) throws IOException;
    void getWeather(Map<String, String[]> requestMap, HttpServletResponse response) throws IOException;
    void constructErrorResponse(int errorCode, String errorMessage, HttpServletResponse response) throws IOException;
}
