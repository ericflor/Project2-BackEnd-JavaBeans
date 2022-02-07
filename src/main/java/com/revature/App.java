package com.revature;

import io.javalin.Javalin;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class App {

    private static Javalin app;

    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        app = Javalin.create( c -> {
            c.enableCorsForAllOrigins();
        });

        app.get("hello", (ctx -> {
            String url = ctx.url();
            System.out.println(url);
            ctx.html("<h1> Hello World Java Beanzz </h1>");
            ctx.status(200);
        }));

        app.start(7000);
        log.info("Started nextUp");

    }
}
