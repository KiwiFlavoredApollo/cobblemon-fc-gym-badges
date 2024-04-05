package kiwiapollo.fcgymbadges.exceptions;

import com.google.gson.JsonObject;

public class LegacyJsonFileException extends Exception {
    JsonObject jsonObject;
    public LegacyJsonFileException(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}
