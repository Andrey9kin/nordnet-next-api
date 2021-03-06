package com.next2.rest.api;

import com.next2.rest.object.Indicator;
import com.next2.rest.object.IndicatorList;
import com.next2.rest.object.TTL;
import com.next2.rest.util.RequestHandler;
import com.next2.rest.util.ResponseHandler;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

public class Indicators extends Session {

    private final static Logger LOG = Logger.getLogger(Indicators.class);
    private final static String API = "indicators";

    IndicatorList indicatorList;
    TTL indicatorListTTL = new TTL(1000);

    public Indicators() {
        super();
    }

    public Indicators(Properties properties) {
        super(properties);
    }

    public IndicatorList getIndicators() {
        LOG.info("Entering getIndicators");

        if (indicatorListTTL.isStale()) {
            indicatorListTTL.resetRequestTime();

            Invocation.Builder invocation = webTarget.path(API).request(responseType);

            Response response = RequestHandler.GET(invocation);
            JSONArray jsonArray = ResponseHandler.asJsonArray(response);
            this.indicatorList = new IndicatorList(jsonArray);
        }
        return this.indicatorList;
    }

    @GET
    public void getIndicator(Indicator indicator) {
        indicator.print();
        Invocation.Builder invocation = webTarget.path(API + "?indicator=[SIX]:[FXR-SSV-03M]").request(responseType);

        Response response = RequestHandler.GET(invocation);
        JSONArray jsonArray = ResponseHandler.asJsonArray(response);
    }

    public void getIndicator(List<Indicator> indicator) {
    }

    public void getIndicator(IndicatorList indicator) {

    }
}
