package com.revature;

import io.javalin.Javalin;

public class App {

    private static Javalin app;

    public static void main(String[] args) {

        app = Javalin.create();

        app.get("hello", (ctx -> {
            String url = ctx.url();
            System.out.println(url);
            ctx.html("<h1> Hello World Java Beanzz </h1>");
            ctx.status(200);
        }));

        app.start(7000);

    }
}
