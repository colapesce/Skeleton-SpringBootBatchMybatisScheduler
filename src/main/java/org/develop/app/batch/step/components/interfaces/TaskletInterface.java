package org.develop.app.batch.step.components.interfaces;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.core.step.tasklet.Tasklet;

public interface TaskletInterface {
	
	public Tasklet build(JSONObject json) throws Exception;
	
}
