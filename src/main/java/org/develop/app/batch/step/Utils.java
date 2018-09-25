package org.develop.app.batch.step;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utils {

	private Utils() {};
	
	synchronized
	public static Object searchInJson(JSONObject json, Enums.StepEnums stepEnum) throws JSONException {
		
		Object obj = null;
		
		try {
			obj = json.get(stepEnum.getValue());
		}
		catch(Exception e) {
			return null;
		}
		return obj;
	}
	
}
