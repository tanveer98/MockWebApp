package com.tanveer.weather;

public class WeatherService implements WeatherServiceInterface {

    @Override
    public ResponseModel GetWeatherResponse(RequestModel requestModel) {
        ResponseModel responseModel = new ResponseModel();

        responseModel.setCityCode(requestModel.getCityCode());
        responseModel.setCityName("Tallinn");
        responseModel.setTemp(-5.);
        responseModel.setTempUnit(TemperatureUnit.C);
        responseModel.setHumidity(30.3);

        return responseModel;
    }
}
