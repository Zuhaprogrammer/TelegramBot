package com.zuhriddin.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.List;

@UtilityClass
public class UtilityJson {
    private static final ObjectMapper objectmapper = new ObjectMapper();

    public <T> void write(String path, T t) {
        objectmapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectmapper.writeValue(new File(path), t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> read(String path, TypeReference<List<T>> type) {
        objectmapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectmapper.readValue(new File(path), type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while reading from the file!");
        }
    }
}
