package com.denisenko.restdemo.utils;

import com.denisenko.restdemo.model.File;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class FileAdapter implements JsonSerializer<File> {


    @Override
    public JsonElement serialize(File file, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", file.getId());
        jsonObject.addProperty("location" , file.getLocation());
        jsonObject.addProperty("event", file.getEvent().toString());
        return jsonObject;
    }
}
