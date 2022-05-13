package com.mergemconflictchat.util.validation;

import com.google.common.hash.Hashing;
import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import java.nio.charset.StandardCharsets;

public class PasswordEncoder {
    private String salt;

    public PasswordEncoder() {
        this.salt = ApplicationProperties.getSalt();
    }

    public String encodePassword(String password) {
        return Hashing.sha256().hashString(password + salt, StandardCharsets.UTF_8).toString();
    }

}
