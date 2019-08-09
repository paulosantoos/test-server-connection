package main.java.com.paulosantos.constants;

import main.java.com.paulosantos.util.Config;

public final class ApplicationConstant {

    public static final Boolean USE_PROXY = Boolean.valueOf(Config.getProperty("custom.use.proxy", "false"));
    public static final String PROXY_URL = Config.getProperty("custom.proxy.url");
    public static final String PROXY_SCHEME = Config.getProperty("custom.proxy.http.scheme", "https");
    public static final Integer PROXY_PORT = Integer.valueOf(Config.getProperty("custom.proxy.port", "8080"));
    public static final String USERNAME = Config.getProperty("custom.username");
    public static final String PASSWORD = Config.getProperty("custom.password");
    public static final String CLIENT_ID = Config.getProperty("custom.oauth.client.id");
    public static final String CLIENT_SECRET = Config.getProperty("custom.oauth.client.secret");
    public static final String GRANT_TYPE = Config.getProperty("custom.oauth.grant.type");
    public static final String REQUEST_URL = Config.getProperty("custom.request.url");
    public static final String REQUEST_PROTOCOL = Config.getProperty("custom.request.protocol", "HTTP");
    public static final Integer REQUEST_MAJOR_VERSION = Integer.valueOf(Config.getProperty("custom.request.major", "1"));
    public static final Integer REQUEST_MINOR_VERSION = Integer.valueOf(Config.getProperty("custom.request.minor", "1"));


}
