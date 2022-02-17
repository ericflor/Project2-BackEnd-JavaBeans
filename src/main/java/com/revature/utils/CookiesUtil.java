package com.revature.utils;

import com.revature.models.User;
import org.springframework.http.ResponseCookie;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class CookiesUtil {


    // Converts User Into a cookie object
    public static ResponseCookie buildResponseCookie(User user){

        try{

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.close();

            return ResponseCookie.from("upNext_user",
                            Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()))
                    .maxAge(7 * 24 * 60 * 60)
                    .build();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static User isCookieValid(String cookie){
        if (cookie.isEmpty()) {
            return null;
        }
        System.out.println(cookie);
        byte[] data = Base64.getDecoder().decode(cookie);
        System.out.println(data);

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            System.out.println(inputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User obj = (User) objectInputStream.readObject();
            objectInputStream.close();
            if (obj != null) {
                return obj;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public static ResponseCookie nullResponseCookie(){
        // Returns Empty Cookie
        return ResponseCookie.from("upNext_user","").maxAge(7 * 24 * 60 * 60).build();
    }
}
