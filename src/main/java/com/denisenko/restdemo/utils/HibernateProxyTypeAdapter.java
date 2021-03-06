package com.denisenko.restdemo.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

       @Override
       @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) new HibernateProxyTypeAdapter(gson) : null);
        }
    };
    private final Gson context;

    private HibernateProxyTypeAdapter(Gson context) {
        this.context = context;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void write(JsonWriter jsonWriter, HibernateProxy hibernateProxy) throws IOException {
        if (hibernateProxy == null) {
            jsonWriter.nullValue();
            return;
        }
        // Retrieve the original (not proxy) class
        Class<?> baseType = Hibernate.getClass(hibernateProxy);
        // Get the TypeAdapter of the original class, to delegate the serialization
        TypeAdapter delegate = context.getAdapter(TypeToken.get(baseType));
        // Get a filled instance of the original class
        Object unproxiedValue = ((HibernateProxy) hibernateProxy).getHibernateLazyInitializer()
                .getImplementation();
        // Serialize the value
        delegate.write(jsonWriter, unproxiedValue);
    }

    @Override
    public HibernateProxy read(JsonReader jsonReader) throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }
}
