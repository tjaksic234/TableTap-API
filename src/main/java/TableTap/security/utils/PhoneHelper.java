package TableTap.security.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class PhoneHelper {

    // Pattern for:
    // 0951234567 (10 digits straight)
    // 095-123-4567 (with hyphens)
    // (095)123-4567 or (095)1234567 (with parentheses)
    public static String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    public static boolean validatePhoneNumber(String input) {
        return input.matches(pattern);
    }
}
