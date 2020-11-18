package com.tanveer.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanveer.common.ErrorResponseModel;
import com.tanveer.common.Utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet(
        name = "WeatherServlet",
        urlPatterns = {"/weather"}
)
public class WeatherServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private WeatherServiceInterface weatherService;

    @Override
    public void init() throws ServletException {
        super.init();
        objectMapper = new ObjectMapper();
        weatherService = new WeatherService();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
        weatherService = new WeatherService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<WeatherRequestModel> requestModel = WeatherRequestModel.fromRequestMap(req.getParameterMap());

        if(requestModel.isPresent()) {
            WeatherResponseModel responseBody = weatherService.GetWeatherResponse(requestModel.get());
            Utils.constructResponse(objectMapper, responseBody, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        ErrorResponseModel error = new ErrorResponseModel(statusCode, "This endpoint does not support POST method");

        Utils.constructResponse(objectMapper, error, resp);
        resp.setStatus(statusCode);
    }

}
