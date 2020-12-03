package com.tanveer.weather;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanveer.common.ErrorResponseModel;
import com.tanveer.common.Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface WeatherServiceInterface {
    boolean validateInput(Map<String, String[]> requestParamMap, HttpServletResponse response) throws IOException;
    void getWeather(Map<String, String[]> requestMap, HttpServletResponse response) throws IOException;
}
