package com.tanveer.timezone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanveer.common.ErrorResponseModel;
import com.tanveer.common.Utils;
import com.tanveer.weather.TemperatureUnit;
import com.tanveer.weather.WeatherResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


public class TimeZoneService implements TimeZoneServiceInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(TimeZoneService.class);
    private static final String KEY_ZIP_CODE = "zipcode";
    private static final String KEY_CITY_NAME = "cityname";

    private static final long[] LEGAL_ZIP_CODES = new long[]{
            1206, 12611, 90210, 10101
    };
    private static final String[] LEGAL_CITY_NAMES = new String[]{
            "Dhaka", "Tallinn", "Los Angeles", "Old York"
    };


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean validateParams(Map<String, String[]> requestParamMap, HttpServletResponse response) throws IOException {
        Optional<String> zipCodeValue = Utils.getValueFromRequestMap(KEY_ZIP_CODE, requestParamMap);
        Optional<String> cityNameValue = Utils.getValueFromRequestMap(KEY_CITY_NAME, requestParamMap);

        if (zipCodeValue.isPresent()) {
            try {
                Long.parseLong(zipCodeValue.get());
            } catch (NumberFormatException e) {
                LOGGER.error("invalid value from {} {}", KEY_ZIP_CODE, zipCodeValue.get());
                constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Value for request parameter `" + KEY_ZIP_CODE + "`"
                        + "Should be of type Long", response);
                return false;
            }

        } else if (!cityNameValue.isPresent()) {
            LOGGER.error("Missing request parameter {} and {}", KEY_ZIP_CODE, KEY_CITY_NAME);
            constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Request parameter: `" + KEY_ZIP_CODE + '`'
                            + " or `" + KEY_CITY_NAME + "` missing", response);
            return false;
        }

        return true;
    }

    @Override
    public void getTimezone(Map<String, String[]> requestParamMap, HttpServletResponse response) throws IOException {
        Optional<String> zipCodeValue = Utils.getValueFromRequestMap(KEY_ZIP_CODE, requestParamMap);
        Optional<String> cityNameValue = Utils.getValueFromRequestMap(KEY_CITY_NAME, requestParamMap);
        TimeZoneResponseModel responseModel = new TimeZoneResponseModel();
        Long zipCode = 0L;
        String cityName = "";

        if (zipCodeValue.isPresent()) {
            zipCode = Long.parseLong(zipCodeValue.get());
            if (Arrays.stream(LEGAL_ZIP_CODES).noneMatch(zipCode::equals)) {
                LOGGER.error("Value of `{}`: {} not in the correct range", KEY_ZIP_CODE, zipCode);
                constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST
                        , "City code value not inside the correct range", response);
                return;
            }

        } else if (cityNameValue.isPresent()) {
            cityName = cityNameValue.get();
            if (Arrays.stream(LEGAL_CITY_NAMES).noneMatch(cityName::equals)) {
                LOGGER.error("Value of `{}`: {} not in the correct range", KEY_CITY_NAME, cityName);
                constructErrorResponse(HttpServletResponse.SC_BAD_REQUEST
                        , "City code value not inside the correct range", response);
                return;
            }
        }

       if (zipCode.equals(LEGAL_ZIP_CODES[0]) || cityName.equals(LEGAL_CITY_NAMES[0])) {
            responseModel.setZipCode(LEGAL_ZIP_CODES[0]);
            responseModel.setCityName(LEGAL_CITY_NAMES[0]);
            responseModel.setUtcOffset(6);
            responseModel.setTimeZoneString("BST");
        } else if(zipCode.equals(LEGAL_ZIP_CODES[1]) || cityName.equals(LEGAL_CITY_NAMES[1])) {
            responseModel.setZipCode(LEGAL_ZIP_CODES[1]);
            responseModel.setCityName(LEGAL_CITY_NAMES[1]);
            responseModel.setUtcOffset(2);
            responseModel.setTimeZoneString("EEST");
        } else if (zipCode.equals(LEGAL_ZIP_CODES[2]) || cityName.equals(LEGAL_CITY_NAMES[2])) {
            responseModel.setZipCode(LEGAL_ZIP_CODES[2]);
            responseModel.setCityName(LEGAL_CITY_NAMES[2]);
            responseModel.setUtcOffset(-8);
            responseModel.setTimeZoneString("PST");
        } else if (zipCode.equals(LEGAL_ZIP_CODES[3]) || cityName.equals(LEGAL_CITY_NAMES[3])) {
            responseModel.setZipCode(LEGAL_ZIP_CODES[3]);
            responseModel.setCityName(LEGAL_CITY_NAMES[3]);
            responseModel.setUtcOffset(-6);
            responseModel.setTimeZoneString("EST");
        }
        Utils.constructResponse(objectMapper, responseModel, response);
        response.setStatus(responseModel.statusCode);
    }

    @Override
    public void constructErrorResponse(int errorCode, String errorMessage, HttpServletResponse response) throws IOException {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(errorCode, errorMessage);
        Utils.constructResponse(objectMapper, errorResponseModel, response);
        response.setStatus(errorResponseModel.statusCode);
    }
}
