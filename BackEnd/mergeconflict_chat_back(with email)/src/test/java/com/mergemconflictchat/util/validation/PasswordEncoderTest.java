package com.mergemconflictchat.util.validation;

import com.mergemconflictchat.services.dataservices.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PasswordEncoderTest {

    ApplicationProperties configGetter = new ApplicationProperties();
    PasswordEncoder cut = new PasswordEncoder();

    static Arguments[] encodePasswordTestArgs() {
        return new Arguments[]{
                Arguments.arguments("pasPlayer1", "bb395a740c68ec498aef6f117f0f7ac50334456e27c02a5d1a7e36a94fa846f5"),
                Arguments.arguments("pasPlayer2", "3a6b04c03837e75155922b6598321e52a03c6d13d21e2ac695f7182631267475")
        };
    }

    @ParameterizedTest
    @MethodSource("encodePasswordTestArgs")
    void encodePasswordTest(String password, String encodedPassword) {
        String actual = cut.encodePassword(password);
        String expected = encodedPassword;
        Assertions.assertEquals(expected, actual);
    }
}