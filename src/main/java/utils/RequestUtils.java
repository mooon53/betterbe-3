package utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils extends HttpServlet {
    private RequestUtils() {
        throw new IllegalStateException("Utility class");
    }

    public Map<String, String[]> getHeadersMap(HttpServletRequest request) {
        request.getHeader("");
        Map<String, String[]> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            headers.put(headerName, new String[0]);
            ArrayList<String> headerValueStrings = new ArrayList<>();
            while (headerValues.hasMoreElements()) {
                String headerValue = headerValues.nextElement();
                headerValueStrings.add(headerValue);
            }
            String[] header = headerValueStrings.toArray(new String[0]);
            headers.put(headerName, header);
        }
        return headers;
    }
}
