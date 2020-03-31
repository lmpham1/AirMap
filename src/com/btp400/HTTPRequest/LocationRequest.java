package com.btp400.HTTPRequest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LocationRequest {
	private static String url = "https://maps.googleapis.com/maps/api/geocode/json?";
	
	private static String key = "AIzaSyBBMaU5rRU81rOcxVXoGI7RU22a3DVv5nM";
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private JSONObject jo = new JSONObject();
	
	public LocationRequest() {
		
	}
	
	public void sendGet(String address) throws Exception{
		HttpGet getRequest = new HttpGet(url + "address=" + address.replace(" ", "+") + "&key=" + key);
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
	
	public void sendGet(double _lat, double _long) throws Exception{
		HttpGet getRequest = new HttpGet(url + "latlng=" + _lat + "," + _long + "&key=" + key);
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
	
	public JSONObject getJsonResult() {
		return jo;
	}
	
	
	public void close() throws IOException {
        httpClient.close();
    }
}
