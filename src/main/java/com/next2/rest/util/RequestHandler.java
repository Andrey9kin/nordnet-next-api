package com.next2.rest.util;

import com.next2.rest.api.Countries;
import org.apache.log4j.Logger;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

public class RequestHandler {

    public static Response GET(Invocation.Builder invocation) {
        final Logger LOG = Logger.getLogger(Response.class);

        Response response = invocation.get();
        if (response.getStatus() != 200) {
            LOG.error("Failed to request the source. Implement retry logic here");
            LOG.error("Error: " + response.toString());
            return null;
        }
        return response;
    }
}
