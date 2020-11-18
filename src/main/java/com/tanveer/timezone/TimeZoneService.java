package com.tanveer.timezone;

public class TimeZoneService implements TimeZoneServiceInterface {

    @Override
    public ResponseModel GetTimeZoneResponse(RequestModel requestModel) {
        ResponseModel responseModel = new ResponseModel();

        responseModel.setZipCode(requestModel.getZipCode());
        responseModel.setTimeZoneString("EEST");
        responseModel.setUtcOffset(2);

        return responseModel;
    }
}
