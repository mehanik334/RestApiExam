package com.denisenko.restdemo.utils;

import com.denisenko.restdemo.model.Event;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EventAdapter implements JsonSerializer<Event> {
    @Override
    public JsonElement serialize(Event event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",event.getId());
        jsonObject.addProperty("value",event.getValue());
        jsonObject.addProperty("file",event.getFile().toString());
        jsonObject.addProperty("user", event.getUser().toString());
        return jsonObject;

    }
}
