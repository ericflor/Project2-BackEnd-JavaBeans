package com.revature;

import com.revature.config.Config;
import com.revature.services.UserService;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        /// JAVALIN STUFF TO BE DELETED LATER //
        Javalin app;

        app = Javalin.create(JavalinConfig::enableCorsForAllOrigins);

        app.get("hello", (ctx -> {
            ctx.html("<h1> Hello World Java Beanzz </h1>");
            log.info("Someone visited our hello web!");
            ctx.status(200);
        }));

        app.start(7000);
        log.info("Started nextUp");
        /// JAVALIN STUFF TO BE DELETED LATER //

        /* Application Setup */
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

        UserService testService = ac.getBean(UserService.class);

        if (testService != null) {
            log.info("Our bean is working");
        }

    }
}
