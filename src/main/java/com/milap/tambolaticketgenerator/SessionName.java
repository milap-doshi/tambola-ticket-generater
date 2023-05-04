package com.milap.tambolaticketgenerator;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SessionName {
    private static List<String> sessionNameList;
    private static SessionName sessionName;
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionName.class);

    private SessionName() {
        try {
            String sessionNames = FileUtils.readFileToString(ResourceUtils.getFile("classpath:verbList.txt"),
                    StandardCharsets.UTF_8);
            sessionNameList = Arrays.stream(sessionNames.split("\n")).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error while loading Session Name Cache", e);
        }
    }

    public static String generateSessionName() {
        if (sessionName == null) {
            sessionName = new SessionName();
        }
        return sessionName.getRandomWord() + "-" +
                sessionName.getRandomWord() + "-" +
                sessionName.getRandomWord();
    }

    private String getRandomWord() {
        return sessionNameList.get(new Random().nextInt(sessionNameList.size()));
    }
}
