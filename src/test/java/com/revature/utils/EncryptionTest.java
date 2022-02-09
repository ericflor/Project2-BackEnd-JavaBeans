package com.revature.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    // the string 'password' encrypted to md5
    private final String password_md5 = "5f4dcc3b5aa765d61d8327deb882cf99";

    // Tests if we can generate a string that's equal to our password_md5
    @Test
    void TestPasswordTrue() {
        assertEquals(password_md5, Encryption.stringToMD5("password"));
    }

    // Tests if we verify when a string is not a md5, should not be equal
    @Test
    void TestPasswordFalse() {
        assertNotEquals(Encryption.stringToMD5("password"), "password");
    }
}