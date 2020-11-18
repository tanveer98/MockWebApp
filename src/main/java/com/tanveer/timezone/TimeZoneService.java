package com.tanveer.timezone;

public class TimeZoneService implements TimeZoneServiceInterface {

    @Override
    public TimeZoneResponseModel GetTimeZoneResponse(TimeZoneRequestModel timeZoneRequestModel) {
        TimeZoneResponseModel timeZoneResponseModel = new TimeZoneResponseModel();

        timeZoneResponseModel.setZipCode(timeZoneRequestModel.getZipCode());
        timeZoneResponseModel.setTimeZoneString("EEST");
        timeZoneResponseModel.setUtcOffset(2);

        return timeZoneResponseModel;
    }
}
