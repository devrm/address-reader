package br.com.valid.location;

import com.google.appengine.repackaged.com.google.gson.FieldNamingPolicy;

public class ApiConfig {
    public String path;
    public FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
    public String hostName = "https://maps.googleapis.com";
    public boolean supportsClientId = true;
    public String requestVerb = "GET";

    public ApiConfig(String path) {
        this.path = path;
    }

    public ApiConfig fieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        this.fieldNamingPolicy = fieldNamingPolicy;
        return this;
    }

    public ApiConfig hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public ApiConfig supportsClientId(boolean supportsClientId) {
        this.supportsClientId = supportsClientId;
        return this;
    }

    public ApiConfig requestVerb(String requestVerb) {
        this.requestVerb = requestVerb;
        return this;
    }
}