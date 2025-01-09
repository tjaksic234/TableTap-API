package TableTap.security.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class Helper {

    public static String generateRandomString() {
        return RandomStringUtils.random(15, 0, 0, true, true, null, new Random());
    }

    public static Duration getDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }
}
