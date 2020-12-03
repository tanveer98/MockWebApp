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


@WebServlet(
        name = "WeatherServlet",
        urlPatterns = {"/weather"}
)
public class WeatherController extends HttpServlet {
    private WeatherServiceInterface weatherService;

    @Override
    public void init() throws ServletException {
        super.init();
        weatherService = new WeatherService();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        weatherService = new WeatherService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean validated = weatherService.validateParams(req.getParameterMap(), resp);
        if(validated) {
            weatherService.getWeather(req.getParameterMap(), resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        weatherService.constructErrorResponse(statusCode, "This endpoint does not support POST method", resp);
    }

}
