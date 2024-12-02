package com.gft.training.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.PrintStream;

@Slf4j
public class LoggerExample {
    public static void setupLogging() {
        try {
            System.setOut(new PrintStream(new FileOutputStream("application.log", true)));
            System.setErr(new PrintStream(new FileOutputStream("error.log", true)));
            log.info("Logging redirected to application.log");
        } catch (Exception e) {
            log.error("Failed to set up logging: {}", e.getMessage());
        }
    }
}
