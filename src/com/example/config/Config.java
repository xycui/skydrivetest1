package com.example.config;

final public class Config {
    public static final String CLIENT_ID = "000000004C0E0B40";

    public static final String[] SCOPES = {
        "wl.signin",
        "wl.basic",
        "wl.offline_access",
        "wl.skydrive_update",
    };

    private Config() {
        throw new AssertionError("Unable to create Config object.");
    }
}
