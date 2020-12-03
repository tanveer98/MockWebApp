package com.tanveer.timezone;

import com.tanveer.common.Constants;
import com.tanveer.common.Utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "TimeZoneServlet",
        urlPatterns = {"/timezone"}
)
public class TimeZoneController extends HttpServlet {
    private TimeZoneServiceInterface timeZoneService;

    @Override
    public void init() throws ServletException {
        super.init();
        timeZoneService = new TimeZoneService();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        timeZoneService = new TimeZoneService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        timeZoneService.constructErrorResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                "This endpoint does not support GET method", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Utils.isValidContentType(req)) {
            timeZoneService.constructErrorResponse(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                    "Unsupported MIME type; Please use MIME TYPE " + Constants.MIME_TYPE_FORM_ENCODED,
                    resp);
            return;
        }

        boolean validated = timeZoneService.validateParams(req.getParameterMap(), resp);
        if(validated) {
            timeZoneService.getTimezone(req.getParameterMap(), resp);
        }
    }
}
