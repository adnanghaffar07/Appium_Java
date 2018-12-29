package common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRail {
	static APIClient client;
	
	public static APIClient getClient() {
		if (client != null) {
			return client;
		}
		client = new APIClient("https://baseline.testrail.io/");
		client.setUser("farhan@baselinetelematics.com");
		client.setPassword("Test1234");
		return client;
	}
	
	public static JSONObject addRun(String projectName) throws MalformedURLException, IOException, APIException {
		client = getClient();
		Map data = new HashMap();
		data.put("name", projectName);
		data.put("suite_id", "24");
		JSONObject testRun = (JSONObject) client.sendPost("add_run/11", data);
		return testRun;
	}
	
	public boolean updateTestRail(String caseId, long currentTestRailRunId, boolean flag, String msg) throws MalformedURLException, IOException, APIException {
		client = getClient();
		Integer statusId;
		if (flag == true) {
			statusId = 1;
		} else {
			statusId = 5;
		}
		if (currentTestRailRunId != 0L) {
			Map data = new HashMap();
			data.put("status_id", statusId);
			data.put("comment", msg);
			JSONObject testRun = (JSONObject) client.sendPost("add_result_for_case/" + currentTestRailRunId + "/" + caseId, data);
			System.out.println("Updated test result for case " + caseId);
		}
		return false;
	}
}
