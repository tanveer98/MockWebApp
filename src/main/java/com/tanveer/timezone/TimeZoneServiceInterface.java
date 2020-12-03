package com.tanveer.timezone;

import com.tanveer.common.ErrorResponseModel;

import java.util.Map;
import java.util.Optional;

public interface TimeZoneServiceInterface {
    public Optional<ErrorResponseModel> validateInput(Map<String, String[]> map);
    public TimeZoneResponseModel getTimeZoneResponse(TimeZoneRequestModel timeZoneRequestModel);
}
