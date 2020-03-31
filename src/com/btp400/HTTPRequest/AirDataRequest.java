package com.btp400.HTTPRequest;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.btp400.controller.FeaturesList;

public class AirDataRequest {
	private static String url = "https://api.breezometer.com/air-quality/v2/current-conditions?";
	
	private static String key = "f744d3eea65b4e05b100c1c52c74e862";
	
	private ArrayList<String> features = new ArrayList<String>();
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private JSONObject jo = new JSONObject();
	
	public AirDataRequest() {
		
	}
	
	public static void main(String[] args) throws Exception {

        AirDataRequest obj = new AirDataRequest();

        try {
            System.out.println("Testing 1 - Send Http GET request");
            obj.setFeatures(FeaturesList.pollutants_concentrations, FeaturesList.sources_and_effects);
            obj.sendGet(48, 2.3);
            obj.sendGet(-50, 23.45);
        } finally {
            obj.close();
        }
    }

    public void close() throws IOException {
        httpClient.close();
    }
	
	public void sendGet(double _lat, double _long) throws Exception {
		HttpGet getRequest = new HttpGet(url + createParams(_lat, _long));
		
		try (CloseableHttpResponse response = httpClient.execute(getRequest)) {

            // Get HttpResponse Status
            //System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            //Header headers = entity.getContentType();
            //System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                //System.out.println(result);
                Object obj = new JSONParser().parse(result);
                jo = (JSONObject) obj;
                //System.out.println(jo);
            }

        }
	}
	
	public void setFeatures(FeaturesList... feature) {
		features.clear();
		for(FeaturesList f : feature) {
			features.add(f.toString());
		}
	}
	
	public JSONObject getJsonResult() {
		return jo;
	}
	
	private String createParams(double _lat, double _long) {
		String params = "";
		params += "lat=" + _lat + "&lon=" + _long + "&key=" + key;
		if (features != null && features.size() != 0) {
			params += "&features=" + features.get(0);
			if (features.size() > 1) {
				for(int i = 1; i < features.size(); ++i) {
					params+= "," + features.get(i);
				}
			}
		}
		return params;
	}
}
