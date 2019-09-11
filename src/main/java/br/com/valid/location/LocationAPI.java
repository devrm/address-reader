package br.com.valid.location;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author rodmafra
 */
public class LocationAPI {

    private static ObjectMapper mapper = new ObjectMapper();
    private final String baseUri;

    public LocationAPI(String baseUri) {
        this.baseUri = baseUri;
    }


    public LocationResponse executeLocationApiCall(LocationRequest locationRequest) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUri).newBuilder();

        StringBuilder address = new StringBuilder(locationRequest.getAddress());
        address.append(",").append(locationRequest.getCity()).append(",").append(locationRequest.getState());

        urlBuilder.addQueryParameter("address", address.toString());
        urlBuilder.addQueryParameter("key", "AIzaSyBAXreYDgwxeUaP9D1-AdLydJxr1Cznup0");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlBuilder.toString()).build();

        final Call call = client.newCall(request);

        final Response execute = call.execute();

        final JsonNode jsonNode = mapper.readTree(execute.body().source().readString(Charset.defaultCharset()));

        final String lat = jsonNode.path("results").path(0).path("geometry").path("location").path("lat").toString();
        final String lng = jsonNode.path("results").path(0).path("geometry").path("location").path("lng").toString();

        LocationResponse response = new LocationResponse();
        response.setLat(lat);
        response.setLon(lng);
        return response;
    }


}
