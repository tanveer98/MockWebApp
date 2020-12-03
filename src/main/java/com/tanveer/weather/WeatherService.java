package com.tanveer.weather;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanveer.common.ErrorResponseModel;
import com.tanveer.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class WeatherService implements WeatherServiceInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);
    private static final String KEY_CITY_CODE = "citycode";
    private static final String KEY_CITY_NAME = "cityname";

    private static final long[] LEGAL_CITY_CODES = new long[]{
            10001L, 20001L, 30001L, 404L
    };
    private static final String[] LEGAL_CITY_NAMES = new String[]{
            "Tallinn", "Tartu", "Los Angeles", "Old York"
    };

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean validateParams(Map<String, String[]> requestParamMap, HttpServletResponse response) throws IOException {
        Optional<String> cityCodeValue = Utils.getValueFromRequestMap(KEY_CITY_CODE, requestParamMap);
        Optional<String> cityNameValue = Utils.getValueFromRequestMap(KEY_CITY_NAME, requestParamMap);

        if (cityCodeValue.isPresent()) {
            try {
                Long.parseLong(cityCodeValue.get());
            } catch (NumberFormatException e) {
                LOGGER.error("invalid value from {} {}", KEY_CITY_CODE, cityCodeValue.get());
                constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST
                        , "Value for request parameter `" + KEY_CITY_CODE + "`"
                        + "Should be of type Long", response);
                return false;
            }

        } else if (!cityNameValue.isPresent()) {
            LOGGER.error("Missing request parameter {} and {}", KEY_CITY_CODE, KEY_CITY_NAME);
            constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Request parameter: `" + KEY_CITY_CODE + '`'
                            + " or `" + KEY_CITY_NAME + "` missing", response);
            return false;
        }

        return true;
    }

    @Override
    public void getWeather(Map<String, String[]> requestParamMap, HttpServletResponse response) throws IOException {
        Optional<String> cityCodeValueString = Utils.getValueFromRequestMap(KEY_CITY_CODE, requestParamMap);
        Optional<String> cityNameValueString = Utils.getValueFromRequestMap(KEY_CITY_NAME, requestParamMap);
        WeatherResponseModel responseModel = new WeatherResponseModel();
        Long cityCode = 0L;
        String cityName = "";

        if (cityCodeValueString.isPresent()) {
            cityCode = Long.parseLong(cityCodeValueString.get());
            if (Arrays.stream(LEGAL_CITY_CODES).noneMatch(cityCode::equals)) {
                LOGGER.error("Value of `{}`: {} not in the correct range", KEY_CITY_CODE, cityCode);
                constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST
                        , "City code value not inside the correct range", response);
                return;
            }

        } else if (cityNameValueString.isPresent()) {
            cityName = cityNameValueString.get();
            if (Arrays.stream(LEGAL_CITY_NAMES).noneMatch(cityName::equals)) {
                LOGGER.error("Value of `{}`: {} not in the correct range", KEY_CITY_NAME, cityName);
                constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST
                        , "City code value not inside the correct range", response);
                return;
            }
        }

        if (cityCode.equals(LEGAL_CITY_CODES[0]) || cityName.equals(LEGAL_CITY_NAMES[0])) {
            responseModel.setCityCode(LEGAL_CITY_CODES[0]);
            responseModel.setCityName(LEGAL_CITY_NAMES[0]);
            responseModel.setHumidity(30.4);
            responseModel.setTemp(273.5);
            responseModel.setTempUnit(TemperatureUnit.KELVIN);
        } else if (cityCode.equals(LEGAL_CITY_CODES[1]) || cityName.equals(LEGAL_CITY_NAMES[1])) {
            responseModel.setCityCode(LEGAL_CITY_CODES[1]);
            responseModel.setCityName(LEGAL_CITY_NAMES[1]);
            responseModel.setHumidity(70.);
            responseModel.setTemp(8.);
            responseModel.setTempUnit(TemperatureUnit.CELSIUS);
        } else if (cityCode.equals(LEGAL_CITY_CODES[2]) || cityName.equals(LEGAL_CITY_NAMES[2])) {
            responseModel.setCityCode(LEGAL_CITY_CODES[2]);
            responseModel.setCityName(LEGAL_CITY_NAMES[2]);
            responseModel.setHumidity(50.66);
            responseModel.setTemp(85.01);
            responseModel.setTempUnit(TemperatureUnit.FAHRENHEIT);
        } else if (cityCode.equals(LEGAL_CITY_CODES[3]) || cityName.equals(LEGAL_CITY_NAMES[3])) {
            responseModel.setCityCode(LEGAL_CITY_CODES[3]);
            responseModel.setCityName(LEGAL_CITY_NAMES[3]);
            responseModel.setHumidity(70.);
            responseModel.setTemp(80.);
            responseModel.setTempUnit(TemperatureUnit.FAHRENHEIT);
        }

        Utils.constructResponse(objectMapper, responseModel, response);
        response.setStatus(responseModel.statusCode);
    }

    public void constructErrorResponse(int errorCode, String errorMessage, HttpServletResponse response) throws IOException {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(errorCode, errorMessage);
        Utils.constructResponse(objectMapper, errorResponseModel, response);
        response.setStatus(errorResponseModel.statusCode);
    }
}
