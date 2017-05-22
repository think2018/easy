package com.cheng.core.util;

import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonDateDeserializer implements JsonDeserializer<Date> {

public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
		throws JsonParseException {
	// TODO Auto-generated method stub
	 String s = json.getAsJsonPrimitive().getAsString();
     long l = Long.parseLong(s);
     Date d = new Date(l);
     return d; 
} 
}