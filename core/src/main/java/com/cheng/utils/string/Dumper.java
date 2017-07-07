package com.cheng.utils.string;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Dumper {

	private static Dumper dumper = null;

	private Dumper() {
	}

	public static Dumper instance (){
		if (null == dumper) {
			dumper = new Dumper();
		}
		return dumper;
	}

	static public void dump(Object object) {
		String name = object.getClass().getSimpleName();
		System.out.println("+++++++++++++++ " + name + " +++++++++++++++++");

		Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().setPrettyPrinting()
				.create();

		System.out.println(gson.toJson(object));

		System.out.println("+++++++++++++++ " + name + " +++++++++++++++++");
	}
}