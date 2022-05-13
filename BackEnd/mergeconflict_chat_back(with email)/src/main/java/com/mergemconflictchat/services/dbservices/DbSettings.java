package com.mergemconflictchat.services.dbservices;

public class DbSettings {
    private final String user;
    private final String password;
    private final String url;
    private final String driver;

    public DbSettings(String user, String password, String url, String driver) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

}
