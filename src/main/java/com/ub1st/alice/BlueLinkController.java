package com.ub1st.alice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@Validated
@RequestMapping("/api/v1/car/status")
public class BlueLinkController {

	private String url = "https://dev.kr-ccapi.hyundai.com/api/v1/fleet/status/";

	/**
	 * 주행가능 거리 조회
	 * 
	 * @param authorization
	 * @param adminKey
	 * @param contentType
	 * @param carId
	 * @return
	 * @throws RestClientException			
	 * @throws URISyntaxException
	 */
	@GetMapping("/{carId}/dte")
	public JSONObject getDte(@RequestHeader(required = true) Map<String, String> data, @PathVariable(value = "carId", required = true) String carId, @RequestParam MultiValueMap<String, String> param) {
		JSONObject requestMap = new JSONObject();
		System.out.println(data.toString());
		if(data.get("authorization")==null||data.get("adminkey")==null||data.get("content-type")==null) { // header null check
			
			requestMap.put("errCode","4016");
			requestMap.put("errId","a7a40c70-bab1-4957-9cb8-a165cd85a6d4");
			requestMap.put("errMsg","Unauthorized client");
			
		} else if(carId==null) { // carId null check
			String _resultMap = 
			"<!DOCTYPE html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n"+
				"<meta charset=\"utf-8\">\n" +
				"<title>Error</title>\n" +
			"</head>\n" +
			"<body>\n" +
				"<pre>Cannot GET /api/v1/fleet/status/odometer</pre>\n" +
			"</body>\n" +
			"</html>";
			requestMap.put("", _resultMap);
			
		} else if(param.get("agreeYn")==null) { // agreeYn null check
			
			requestMap.put("errCode","4002");
			requestMap.put("errId","1ca2023b-0a49-4606-9920-18e05c3eba94");
			requestMap.put("errMsg","Invalid Request Body");
			
		} else { 
		
			System.out.println(data.toString());
			System.out.println(data.get("authorization"));
			System.out.println(data.get("adminkey"));
			System.out.println(data.get("content-type"));

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", data.get("authorization"));
			headers.set("AdminKey", data.get("adminkey"));
			headers.set("Content-Type", data.get("content-type"));

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

			String _url = url + carId + "/dte";

//	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//	        factory.setConnectTimeout(10*1000);      //(연결시 타임) 10초
			// factory.setReadTimeout(10*1000); //(불러온 값 읽는 타임) 10초

			RestTemplate restTemplate = new RestTemplate();

			try {
				URI uri = UriComponentsBuilder.fromHttpUrl(_url).queryParams(param).build(true).toUri();
				ResponseEntity<JSONObject> response = restTemplate.exchange(uri, HttpMethod.GET, request, JSONObject.class);
				
				System.out.println(response.toString());
				requestMap = response.getBody();

				System.out.println(requestMap.toString());
				System.out.println(ResponseEntity.ok().body(data.toString()));

			} catch(Exception e) {
				requestMap.put("errCode","4051");
				requestMap.put("errId","452c328e-bab6-4e51-a6e3-11cdee8628af");
				requestMap.put("errMsg","Unregistered Test Vehicle");
			}

						
		}


		return requestMap;
	}

	/**
	 * 누적 운행 거리 조회
	 * 
	 * @param authorization
	 * @param adminKey
	 * @param contentType
	 * @param carId
	 * @return
	 */
	@GetMapping("/{carId}/odometer")
	public JSONObject odometer(@RequestHeader(required = true) Map<String, String> data, @PathVariable(value = "carId", required = true) String carId, @RequestParam MultiValueMap<String, String> param) {

		JSONObject requestMap = new JSONObject();
		System.out.println(data.toString());
		if(data.get("authorization")==null||data.get("adminkey")==null||data.get("content-type")==null) { // header null check
			
			requestMap.put("errCode","4016");
			requestMap.put("errId","a7a40c70-bab1-4957-9cb8-a165cd85a6d4");
			requestMap.put("errMsg","Unauthorized client");
			
		} else if(carId==null) { // carId null check
			String _resultMap = 
			"<!DOCTYPE html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n"+
				"<meta charset=\"utf-8\">\n" +
				"<title>Error</title>\n" +
			"</head>\n" +
			"<body>\n" +
				"<pre>Cannot GET /api/v1/fleet/status/odometer</pre>\n" +
			"</body>\n" +
			"</html>";
			requestMap.put("", _resultMap);
			
		} else if(param.get("agreeYn")==null) { // agreeYn null check
			
			requestMap.put("errCode","4002");
			requestMap.put("errId","1ca2023b-0a49-4606-9920-18e05c3eba94");
			requestMap.put("errMsg","Invalid Request Body");
			
		} else {
			
			System.out.println(data.toString());
			System.out.println(data.get("authorization"));
			System.out.println(data.get("adminkey"));
			System.out.println(data.get("content-type"));

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", data.get("authorization"));
			headers.set("AdminKey", data.get("adminkey"));
			headers.set("Content-Type", data.get("content-type"));

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

			String _url = url + carId + "/odometer";

//	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//	        factory.setConnectTimeout(10*1000);      //(연결시 타임) 10초
			// factory.setReadTimeout(10*1000); //(불러온 값 읽는 타임) 10초

			RestTemplate restTemplate = new RestTemplate();
			
			try {
				URI uri = UriComponentsBuilder.fromHttpUrl(_url).queryParams(param).build(true).toUri();
				ResponseEntity<JSONObject> response = restTemplate.exchange(uri, HttpMethod.GET, request, JSONObject.class);
				
				System.out.println(response.toString());
				requestMap = response.getBody();

				System.out.println(requestMap.toString());
				System.out.println(ResponseEntity.ok().body(data.toString()));

			} catch(Exception e) {
				requestMap.put("errCode","4051");
				requestMap.put("errId","452c328e-bab6-4e51-a6e3-11cdee8628af");
				requestMap.put("errMsg","Unregistered Test Vehicle");
			}
		
		}


		return requestMap;
	}

	/**
	 * 저전압 배터리 잔량
	 * 
	 * @param authorization
	 * @param adminKey
	 * @param contentType
	 * @param vin
	 * @return
	 */
	@GetMapping("/{vin}/batteryLevel")
	public JSONObject bettery(@RequestHeader(required = true) Map<String, String> data, @RequestParam MultiValueMap<String, String> param, @PathVariable(value = "vin") String vin) {
		
		JSONObject requestMap = new JSONObject();
		System.out.println(data.toString());
		if(data.get("authorization")==null||data.get("adminkey")==null||data.get("content-type")==null) { // header null check
			
			requestMap.put("errCode","4016");
			requestMap.put("errId","a7a40c70-bab1-4957-9cb8-a165cd85a6d4");
			requestMap.put("errMsg","Unauthorized client");
			
		} else if(vin==null) { // carId null check
			String _resultMap = 
			"<!DOCTYPE html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n"+
				"<meta charset=\"utf-8\">\n" +
				"<title>Error</title>\n" +
			"</head>\n" +
			"<body>\n" +
				"<pre>Cannot GET /api/v1/fleet/status/odometer</pre>\n" +
			"</body>\n" +
			"</html>";
			requestMap.put("", _resultMap);
			
		} else if(param.get("agreeYn")==null) { // agreeYn null check
			
			requestMap.put("errCode","4002");
			requestMap.put("errId","1ca2023b-0a49-4606-9920-18e05c3eba94");
			requestMap.put("errMsg","Invalid Request Body");
			
		} else {
			
			System.out.println(data.toString());
			System.out.println(data.get("authorization"));
			System.out.println(data.get("adminkey"));
			System.out.println(data.get("content-type"));

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", data.get("authorization"));
			headers.set("AdminKey", data.get("adminkey"));
			headers.set("Content-Type", data.get("content-type"));

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

			String _url = url + vin + "/batteryLevel";

//	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//	        factory.setConnectTimeout(10*1000);      //(연결시 타임) 10초
			// factory.setReadTimeout(10*1000); //(불러온 값 읽는 타임) 10초

			RestTemplate restTemplate = new RestTemplate();
			
			try {
				URI uri = UriComponentsBuilder.fromHttpUrl(_url).queryParams(param).build(true).toUri();
				ResponseEntity<JSONObject> response = restTemplate.exchange(uri, HttpMethod.GET, request, JSONObject.class);
				
				System.out.println(response.toString());
				requestMap = response.getBody();

				System.out.println(requestMap.toString());
				System.out.println(ResponseEntity.ok().body(data.toString()));

			} catch(Exception e) {
				requestMap.put("errCode","4051");
				requestMap.put("errId","452c328e-bab6-4e51-a6e3-11cdee8628af");
				requestMap.put("errMsg","Unregistered Test Vehicle");
			}			
		}
		
		return requestMap;

	}

	/**
	 * 고장코드 조회
	 * 
	 * @param authorization
	 * @param adminKey
	 * @param contentType
	 * @param vin
	 * @return
	 */
	@GetMapping("/{vin}/dtc")
	public JSONObject battery(@RequestHeader(required = true) Map<String, String> data, @RequestParam MultiValueMap<String, String> param, @PathVariable(value = "vin") String vin) {

		JSONObject requestMap = new JSONObject();
//		System.out.println(data.toString());
		if(data.get("authorization")==null||data.get("adminkey")==null||data.get("content-type")==null) { // header null check
			
			requestMap.put("errCode","4016");
			requestMap.put("errId","a7a40c70-bab1-4957-9cb8-a165cd85a6d4");
			requestMap.put("errMsg","Unauthorized client");
			
		} else if(vin==null) { // carId null check
			String _resultMap = 
			"<!DOCTYPE html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n"+
				"<meta charset=\"utf-8\">\n" +
				"<title>Error</title>\n" +
			"</head>\n" +
			"<body>\n" +
				"<pre>Cannot GET /api/v1/fleet/status/odometer</pre>\n" +
			"</body>\n" +
			"</html>";
			requestMap.put("", _resultMap);
			
		} else if(param.get("agreeYn")==null) { // agreeYn null check
			
			requestMap.put("errCode","4002");
			requestMap.put("errId","1ca2023b-0a49-4606-9920-18e05c3eba94");
			requestMap.put("errMsg","Invalid Request Body");
			
		} else {
			
			System.out.println(data.toString());
			System.out.println(data.get("authorization"));
			System.out.println(data.get("adminkey"));
			System.out.println(data.get("content-type"));

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", data.get("authorization"));
			headers.set("AdminKey", data.get("adminkey"));
			headers.set("Content-Type", data.get("content-type"));

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

			String _url = url + vin + "/dtc";

//	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//	        factory.setConnectTimeout(10*1000);      //(연결시 타임) 10초
			// factory.setReadTimeout(10*1000); //(불러온 값 읽는 타임) 10초

			RestTemplate restTemplate = new RestTemplate();
			
			try {
				URI uri = UriComponentsBuilder.fromHttpUrl(_url).queryParams(param).build(true).toUri();
				ResponseEntity<JSONObject> response = restTemplate.exchange(uri, HttpMethod.GET, request, JSONObject.class);
				
				System.out.println(response.toString());
				requestMap = response.getBody();

				System.out.println(requestMap.toString());
				System.out.println(ResponseEntity.ok().body(data.toString()));

			} catch(Exception e) {
				requestMap.put("errCode","4051");
				requestMap.put("errId","452c328e-bab6-4e51-a6e3-11cdee8628af");
				requestMap.put("errMsg","Unregistered Test Vehicle");
			}			
		}


		return requestMap;
	}
}
