package com.mergemconflictchat.services;

import java.security.SecureRandom;

public class SecureKey {

    public int getSecureKey() {
        SecureRandom secureRandom = new SecureRandom();
        int diff = 9999 - 1000;
        int secureKey = secureRandom.nextInt(diff + 1);
        return secureKey += 1000;
    }
}
