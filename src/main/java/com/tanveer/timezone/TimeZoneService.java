package com.tanveer.timezone;

import com.tanveer.common.ErrorResponseModel;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Optional;


public class TimeZoneService implements TimeZoneServiceInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(TimeZoneService.class);

    @Override
    public Optional<ErrorResponseModel> validateInput(Map<String, String[]> map) {
        return Optional.empty();
    }


    @Override
    public TimeZoneResponseModel getTimeZoneResponse(TimeZoneRequestModel timeZoneRequestModel) {
        LOGGER.info("Time zone service");
        TimeZoneResponseModel timeZoneResponseModel = new TimeZoneResponseModel();

        timeZoneResponseModel.setZipCode(timeZoneRequestModel.getZipCode());
        timeZoneResponseModel.setTimeZoneString("EEST");
        timeZoneResponseModel.setUtcOffset(2);

        return timeZoneResponseModel;
    }
}
