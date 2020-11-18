package com.tanveer.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class Utils {
    /**
     * Checks if string is null empty or just whitespace
     *
     * @param s string to check
     * @return true if is empty string or whitespace, false otherwise.
     */
    public static boolean isWhiteSpaceOrEmpty(String s) {
        return (s == null) || s.isEmpty() || s.trim().isEmpty();
    }

    /**
     * Serializes the given object value to json, then writes the json into the response body,
     * as well as setting the appropriate MIME type of the response.
     *
     * @param objectMapper
     * @param value
     * @param response
     * @throws IOException
     */
    public static void constructResponse(ObjectMapper objectMapper, Object value, HttpServletResponse response) throws IOException {
        response.setContentType(Constants.MIME_TYPE_JSON);
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
    }

    /**
     * Compares with the request body MIME type with the ones supported by the webapp
     * @param req
     * @return
     */
    public static boolean isInvalidMimeType(HttpServletRequest req) {
        return !req.getContentType().equals(Constants.MIME_TYPE_FORM_ENCODED);
    }

    /**
     * Retrieves the last value with the given key from the map. The comparision is case insensitive.
     *
     * @param key
     * @param requestMap
     * @return Optional that wraps the string
     */
    public static Optional<String> getValueFromRequestMap(String key, Map<String, String[]> requestMap) {
        return requestMap.entrySet()
                .stream()
                .filter(entrySet -> entrySet.getKey().trim().toLowerCase().equals(key.toLowerCase()) && entrySet.getValue() != null)
                .map(entrySet -> entrySet.getValue())
                .flatMap(strings -> Arrays.stream(strings).distinct())
                .reduce((first, last) -> last);
    }
}
