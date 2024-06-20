package kiwiapollo.fcgymbadges.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaseConverter {
    static public String snakeToCamel(String snakeCase) {
        Pattern pattern = Pattern.compile("([a-z])_([a-z])");
        Matcher matcher = pattern.matcher(snakeCase.toLowerCase());

        StringBuilder stringBuilder = new StringBuilder();
        while(matcher.find()) {
            matcher.appendReplacement(stringBuilder,
                    matcher.group(1) + matcher.group(2).toUpperCase());
        }
        matcher.appendTail(stringBuilder);

        return stringBuilder.toString();
    }

    static public String snakeToLower(String snakeCase) {
        return snakeCase.replaceAll("_", "").toLowerCase();
    }

    static public String snakeToDisplay(String snakeCase) {
        String underscoreToWhitespace = snakeCase.replaceAll("_", " ");

        Pattern pattern = Pattern.compile("(^|\\s)([a-z])");
        Matcher matcher = pattern.matcher(underscoreToWhitespace.toLowerCase());

        StringBuilder stringBuilder = new StringBuilder();
        while(matcher.find()) {
            matcher.appendReplacement(stringBuilder,
                    matcher.group(1) + matcher.group(2).toUpperCase());
        }
        matcher.appendTail(stringBuilder);

        return stringBuilder.toString();
    }
}
