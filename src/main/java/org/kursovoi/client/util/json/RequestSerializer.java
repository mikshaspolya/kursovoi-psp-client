package org.kursovoi.client.util.json;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestSerializer<T> implements Function<T, String> {

    @Override
    public String apply(T t){
        Gson gson = new Gson();
        return gson.toJson(t);
    }
}
