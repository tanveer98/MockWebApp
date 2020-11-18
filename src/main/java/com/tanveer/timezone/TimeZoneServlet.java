package com.tanveer.timezone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanveer.common.Constants;
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
        name = "TimeZoneServlet",
        urlPatterns = {"/timezone"}
)
public class TimeZoneServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private TimeZoneServiceInterface timeZoneService;

    @Override
    public void init() throws ServletException {
        super.init();
        objectMapper = new ObjectMapper();
        timeZoneService = new TimeZoneService();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
        timeZoneService = new TimeZoneService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        ErrorResponseModel error = new ErrorResponseModel(statusCode, "This endpoint does not support GET method");

        Utils.constructResponse(objectMapper, error, resp);
        resp.setStatus(statusCode);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.isInvalidMimeType(req)) {
            int statusCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            ErrorResponseModel error = new ErrorResponseModel(statusCode,
                    "Unsupported MIME type; Please use MIME TYPE " + Constants.MIME_TYPE_FORM_ENCODED);
            Utils.constructResponse(objectMapper, error, resp);
            resp.setStatus(statusCode);
            return;
        }

        Optional<TimeZoneRequestModel> requestModel = TimeZoneRequestModel.fromRequestMap(req.getParameterMap());

        if(requestModel.isPresent()) {
            TimeZoneResponseModel responseBody = timeZoneService.GetTimeZoneResponse(requestModel.get());
            Utils.constructResponse(objectMapper, responseBody, resp);
        }
    }
}
