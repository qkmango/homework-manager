package cn.qkmango.hm.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * PrintJson
 * 将要响应到前端的信息使用此类转换为JSON，并进行响应
 */
public class PrintJson {

	/**
	 * 向前端响应 标志位 与 信息
	 * @param response 响应对象
	 * @param flag 标志位值
	 * @param msg 信息
	 */
	public static void printFlagAndMsg(HttpServletResponse response,boolean flag,String msg) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("success",flag);
		map.put("msg",msg);

		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(map);
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 向前端响应标志位success
	 * @param response 响应对象
	 * @param flag 标志位值
	 */
	public static void printJsonFlag(HttpServletResponse response,boolean flag){
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("success",flag);
		
		ObjectMapper om = new ObjectMapper();
		try {
			//{"success":true}
			String json = om.writeValueAsString(map);
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

	/**
	 * 向前端响应一个对象
	 * @param response 响应对象
	 * @param obj 要响应到前端的对象
	 */
	public static void printJsonObj(HttpServletResponse response,Object obj){
		
		/*
		 * 
		 * Person p
		 * 	id name age
		 * {"id":"?","name":"?","age":?}
		 * 
		 * List<Person> pList
		 * [{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?}...]
		 * 
		 * Map
		 * 	key value
		 * {key:value}
		 * 
		 * 
		 */
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(obj);
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}























