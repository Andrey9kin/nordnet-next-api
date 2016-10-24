package com.next2.rest.api;

import com.next2.rest.util.RequestHandler;
import com.next2.rest.util.ResponseHandler;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.Properties;

public class Instruments extends Session {

    private final static Logger LOG = Logger.getLogger(com.next2.rest.api.Instruments.class);
    private final static String API = "instruments";

    public Instruments() {
        super();
    }

    public Instruments(Properties properties) {
        super(properties);
    }

    // https://api.test.nordnet.se/api-docs/index.html#!/instruments/get_instruments
    public JSONArray findInstrument(final String name) {
        LOG.info(String.format("Search for an instrument with name=%s", name));
        Invocation.Builder invocation = webTarget.path(API).queryParam("query", name).request(responseType);
        Response response = RequestHandler.GET(invocation);
        return ResponseHandler.asJsonArray(response);
    }

    // https://api.test.nordnet.se/api-docs/index.html#!/instruments/get_instrument
    public JSONArray lookup(final int instrumentId) {
        LOG.info(String.format("Lookup an instrument with id=%d", instrumentId));
        Invocation.Builder invocation = webTarget.path(API).path(Integer.toString(instrumentId)).request(responseType);
        Response response = RequestHandler.GET(invocation);
        return ResponseHandler.asJsonArray(response);
    }
}


