package com.tanveer.weather;

public class WeatherService implements WeatherServiceInterface {

    @Override
    public WeatherResponseModel GetWeatherResponse(WeatherRequestModel weatherRequestModel) {
        WeatherResponseModel responseModel = new WeatherResponseModel();

        responseModel.setCityCode(weatherRequestModel.getCityCode());
        responseModel.setCityName("Tallinn");
        responseModel.setTemp(-5.);
        responseModel.setTempUnit(TemperatureUnit.C);
        responseModel.setHumidity(30.3);

        return responseModel;
    }
}
