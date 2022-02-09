package com.revature;

import com.revature.config.Config;
import io.javalin.Javalin;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    private static Javalin app;

    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        /// JAVALIN STUFF TO BE DELETED LATER //
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
        /// JAVALIN STUFF TO BE DELETED LATER //

        /* Application Setup */
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

    }
}
