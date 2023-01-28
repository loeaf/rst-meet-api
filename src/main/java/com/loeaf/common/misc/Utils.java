/**
 * 
 */
package com.loeaf.common.misc;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;

/**
 * 이것저것 유틸리티
 *
 */
public class Utils {
	
	/**
	 * result map 생성
	 * @return
	 */
	public static Map<String,Object> createResultMap(){
		return createResultMap(HttpStatus.OK, null, null);
	}
	
	/**
	 * result map 생성
	 * @param statusCode
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static Map<String,Object> createResultMap(HttpStatus statusCode, String errorCode, String message){
		Map<String,Object> map = new HashMap<>();
		
		//
		map.put(Const.STATUS_CODE, statusCode);
		map.put(Const.ERROR_CODE, errorCode);
		map.put(Const.MESSAGE, message);
		
		//
		return map;
	}

	/**
	 * @param uri
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpDelete(String uri) throws ClientProtocolException, IOException {
		return httpDelete(uri, null);
	}
	
	/**
	 * delete method로 호출
	 * @param uri
	 * @param param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpDelete(String uri, Map<String,Object> param) throws ClientProtocolException, IOException {
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			
			//
			if(Utils.isNotEmpty(param)) {
				uri += "?_=" + Utils.createShortUid("");
				
				//
				Iterator<String> iter = param.keySet().iterator();
				while(iter.hasNext()) {
					String k = "";
					uri +=  "&" + k + "=" + param.get(k);
				}
			}
			
			//	
			HttpDelete hd = new HttpDelete(uri);
			//
			try(CloseableHttpResponse response = httpClient.execute(hd)){
				HttpEntity entity = response.getEntity();
				if(null == entity) {
					return null;
				}
				
				//
				return EntityUtils.toString(entity);
			}
		}
		//
	}
	
	
	/**
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public static String httpGet(String uri) throws IOException {
		return httpGet(uri, null);
	}
	
	/**
	 * 
	 * @param uri
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String httpGet(String uri, Map<String,Object> param) throws IOException {
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			
			//
			if(Utils.isNotEmpty(param)) {
				uri += "?_=" + Utils.createShortUid("");
				
				//
				Iterator<String> iter = param.keySet().iterator();
				while(iter.hasNext()) {
					String k = "";
					uri +=  "&" + k + "=" + param.get(k);
				}
			}
			
			//	
			HttpGet g = new HttpGet(uri);
			//
			try(CloseableHttpResponse response = httpClient.execute(g)){
				HttpEntity entity = response.getEntity();
				if(null == entity) {
					return null;
				}
				
				//
				return EntityUtils.toString(entity);
			}
		}
		//
	}
	
	
	
	public static String httpPost(String uri, HttpEntity reqEntity) throws ClientProtocolException, IOException {
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			
			//
//			if(PpUtil.isNotEmpty(param)) {
//				uri += "?_=" + PpUtil.createShortUid("");
//				
//				//
//				Iterator<String> iter = param.keySet().iterator();
//				while(iter.hasNext()) {
//					String k = "";
//					uri +=  "&" + k + "=" + param.get(k);
//				}
//			}
			
			//
			
			//	
			HttpPost hp = new HttpPost(uri);
			hp.setEntity(reqEntity);
			
			//
			try(CloseableHttpResponse response = httpClient.execute(hp)){
				HttpEntity entity = response.getEntity();
				if(null == entity) {
					return null;
				}
				
				//
				return EntityUtils.toString(entity);
			}
		}
		//
	}
	
	/**
	 * create HttpClient
	 * @return
	 */
	private static HttpClient createHttpClient() {
		//
		return HttpClient.newBuilder()
				.version(Version.HTTP_1_1)
				.connectTimeout(Duration.ofSeconds(10))
				.build();
	}
	

	/**
	 * create HttpRequest
	 * @param httpMethod
	 * @param url
	 * @param param
	 * @param requestProperty
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static HttpRequest createHttpRequest(HttpMethod httpMethod,  String url, Map<String,Object> param, Map<String,Object> requestProperty) throws UnsupportedEncodingException {
		//
		HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
				.setHeader("User-Agent", "java 11 HttpClient Bot")
				.uri(URI.create(getFullUrl(httpMethod, url, param)));
		
		//
		switch (httpMethod) {
		case GET:
			requestBuilder.GET();
			break;

		default:
			requestBuilder.POST(ofFormData(param));
			break;
		}
		
		//
		if(Utils.isNotEmpty(requestProperty)) {
			for(Map.Entry<String, Object> entry : requestProperty.entrySet()) {
				requestBuilder.header(entry.getKey(), entry.getValue().toString());				
			}
		}
		
		//
		return requestBuilder.build();
	}
	
	
	
	
	/**
	 * url뒤에 파라미터 추가하여 full url생성
	 * @param httpMethod 
	 * @param url
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getFullUrl(HttpMethod httpMethod, String url, Map<String,Object> param) throws UnsupportedEncodingException {
		//
		String fullUrl = url + "?_" + DateUtils.getYmdhms();
		
		//
		if(Utils.isEmpty(param) || HttpMethod.POST == httpMethod){
			return fullUrl;
		}
		
		//
		for(Map.Entry<String, Object> d : param.entrySet()) {
			fullUrl += "&" + d.getKey() + "=" + URLEncoder.encode(""+d.getValue(), "UTF-8");
		}
		
		//
		return fullUrl;
	}
	
	
	/**
	 * post방식으로 데이터를 전송하기 위해 데이터를 변환
	 * @param param
	 * @return
	 */
	private static HttpRequest.BodyPublisher ofFormData(Map<String,Object> param){
		
		//
		if(Utils.isEmpty(param)) {
			return HttpRequest.BodyPublishers.ofString("");
		}
		
		//
		StringBuffer sb = new StringBuffer();
		
		for(Map.Entry<String, Object> entry : param.entrySet()) {
			if(0 < sb.length() ) {
				sb.append("&");
			}
			
			//
			sb.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
			sb.append("=");
			sb.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
		}
		
		//
		return HttpRequest.BodyPublishers.ofString(sb.toString());
	}


	/**
	 * 공백 여부
	 * @param obj 오브젝트. String|Collection|Map|Set|List|배열
	 * @return 공백이면 true
	 * @since
	 * 	20180322	배열, 리스트 처리 추가
	 * 	20200221	Map관련 추가
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if(isNull(obj)){
			return true;
		}
		
		//문자열
		if(String.class == obj.getClass() ) {
			return (0 == obj.toString().trim().length());
		}
		
		//
		if(obj instanceof Collection) {
			return (0 ==((Collection)obj).size());
		}
		
		//
		if(obj instanceof Map) {
			return (0 == ((Map)obj).size());
		}
		
		//
		if(Set.class == obj.getClass()) {
			return (0 == ((Set)obj).size());
		}
		
		//리스트
		if(List.class == obj.getClass() || (ArrayList.class == obj.getClass())) {
			return (0 == ((List)obj).size());
		}
		
		
		//배열
//		if(obj.getClass().toString().contains("[L")) {
//			return (0 == Array.getLength(obj));
//		}
		
		//
		return (0 == obj.toString().length());
	}

	/**
	 * 파일, 디렉터리 모두 삭제 가능
	 * @param path 경로
	 * @throws IOException  예외
	 */
	public static void forceDelete(Path path) throws IOException {

		if(!Files.exists(path)) {
			return;
		}

		//
		FileUtils.forceDelete(path.toFile());
	}
	/**
	 * isEmpty의 반대
	 * @param obj 문자열
	 * @return true / false
	 * 	true 조건
	 * 		문자열인 경우 공백이 아니면
	 * 		collection(Set, List,...)인 경우 0 &lt; size
	 * 		배열인 경우 0 &lt; length
	 * 		Map인 경우 0 &lt; size
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * !널여부
	 * @param obj 오브젝트
	 * @return 널이 아니면 true
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}


	/**
	 * 널여부 검사
	 * @param obj 오브젝트
	 * @return 널이면 true
	 */
	public static boolean isNull(Object obj){
		return (null == obj);
	}

	/**
	 * nanotime 으로 유니크한 문자열 생성
	 * @param prefix 리턴값 앞에 붙일 접두어
	 * @return 유니크한 문자열
	 * @since
	 * 	20180215	prefix 추가
	 */
	public static String createShortUid(String prefix){
		return (isEmpty(prefix) ? "UID" : prefix) + System.nanoTime();
//		return (isEmpty(prefix) ? "UID" : prefix)
//				+ (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date())
//				+ (new Random()).nextInt(10);
	}

	/**
	 * 첫 글자만 대문자로 변환
	 * @param str 문자열
	 * @return 변환된 문자열
	 * @since
	 * 	20200810	init
	 */
	public static String capitalize(String str) {
		if(isEmpty(str)) {
			return "";
		}

		//
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
