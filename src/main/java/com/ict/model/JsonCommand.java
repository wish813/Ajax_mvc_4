package com.ict.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonCommand {
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		JSONArray row = null;
		try {
			URL url = new URL("http://openapi.seoul.go.kr:8088/sample/json/SeoulLibraryTime/1/5/");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			result = br.readLine();
			System.out.println("result : " +result);
			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject) jsonParser.parse(result);
			System.out.println("obj : "+obj);
			JSONObject SeoulLibraryTime = (JSONObject) obj.get("SeoulLibraryTime");
			 row = (JSONArray) SeoulLibraryTime.get("row");
			System.out.println("row : "+row.toJSONString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return row.toJSONString();
		
	}
}
