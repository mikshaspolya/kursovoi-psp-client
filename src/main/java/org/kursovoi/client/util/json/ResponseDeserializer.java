package org.kursovoi.client.util.json;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class ResponseDeserializer<R> {

    public R apply(String t, Class<R> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(t, clazz);
    }
}
