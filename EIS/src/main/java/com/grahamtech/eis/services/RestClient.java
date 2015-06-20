package com.grahamtech.eis.services;

//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriTemplate;


import com.grahamtech.eis.controllers.RestURIConstants;
import com.grahamtech.eis.pojos.AdverseDrugEvent;
import com.grahamtech.eis.utilities.ClientErrorHandler;

public class RestClient implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = LoggerFactory
      .getLogger(RestClient.class);

  public RestClient() {
    // default constructor
  }

    public AdverseDrugEvent getDrugEvents(String uri) {
	RestTemplate restTemplate = new RestTemplate();

	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	messageConverters.add(new MappingJackson2HttpMessageConverter());
	restTemplate.setMessageConverters(messageConverters);

	restTemplate.setErrorHandler(new ClientErrorHandler());
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<String> entity = new HttpEntity<String>("parameters",
		headers);

	AdverseDrugEvent responseEntity = null;
	try {
	    responseEntity = restTemplate.getForObject(uri,
		    AdverseDrugEvent.class);
	    System.out.println(responseEntity);

	    // MediaType contentType = responseEntity.getHeaders()
	    // .getContentType();
	    // HttpStatus statusCode = responseEntity.getStatusCode();
	} catch (Exception ex) {
	    logger.error("Exception thrown for the restTemplate exchange: "
		    + ex.toString());
	}

	return responseEntity;
  }

    // use API key if making 40 or more requests per minute or > 1000 requests
    // per
    // day / per IP
    public ResponseEntity<AdverseDrugEvent> getDrugEvents_apiKey(String uri,
	    String apiKeyHeader, String apiKey) {

	RestTemplate restTemplate = new RestTemplate();

	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	messageConverters.add(new MappingJackson2HttpMessageConverter());
	restTemplate.setMessageConverters(messageConverters);

	restTemplate.setErrorHandler(new ClientErrorHandler());
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.add(apiKeyHeader, apiKey);
	HttpEntity<String> entity = new HttpEntity<String>("parameters",
		headers);

	ResponseEntity<AdverseDrugEvent> responseEntity = null;
	try {
	    responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
		    AdverseDrugEvent.class);
	    System.out.println(responseEntity.getBody());

	    // MediaType contentType = responseEntity.getHeaders()
	    // .getContentType();
	    // HttpStatus statusCode = responseEntity.getStatusCode();
	} catch (Exception ex) {
	    logger.error("Exception thrown for the restTemplate exchange: "
		    + ex.toString());
	}

	return responseEntity;
    }

    // public List<ResponseEntity<AdverseDrugEvent[]>>
    // getDrugEvents_storeAndDisplay_list(
    // String uri, String apiKey) {
    // RestTemplate restTemplate = new RestTemplate();
    //
    // List<HttpMessageConverter<?>> messageConverters = new
    // ArrayList<HttpMessageConverter<?>>();
    // messageConverters.add(new MappingJackson2HttpMessageConverter());
    // restTemplate.setMessageConverters(messageConverters);
    //
    // restTemplate.setErrorHandler(new ClientErrorHandler());
    // HttpHeaders headers = new HttpHeaders();
    // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    // HttpEntity<String> entity = new HttpEntity<String>("parameters",
    // headers);
    //
    // List<ResponseEntity<AdverseDrugEvent[]>> responseEntityArray = null;
    // try {
    // responseEntityArray = Arrays.asList(restTemplate.exchange(uri,
    // HttpMethod.GET, entity, AdverseDrugEvent[].class));
    //
    // System.out.println(responseEntityArray.size());
    // } catch (Exception ex) {
    // logger.error("Exception thrown for the restTemplate exchange: "
    // + ex.toString());
    // }
    //
    // return responseEntityArray;
    // }
    //
    // public AdverseDrugEvent[] getDrugEvents_storeAndDisplay_array(
    // String uri, String apiKey) {
    // RestTemplate restTemplate = new RestTemplate();
    //
    // List<HttpMessageConverter<?>> messageConverters = new
    // ArrayList<HttpMessageConverter<?>>();
    // messageConverters.add(new MappingJackson2HttpMessageConverter());
    // restTemplate.setMessageConverters(messageConverters);
    //
    // restTemplate.setErrorHandler(new ClientErrorHandler());
    // HttpHeaders headers = new HttpHeaders();
    // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    // HttpEntity<String> entity = new HttpEntity<String>("parameters",
    // headers);
    //
    // AdverseDrugEvent[] responseEntityArray = null;
    // try {
    // responseEntityArray = restTemplate.getForObject(uri,
    // AdverseDrugEvent[].class, entity);
    //
    // System.out.println(responseEntityArray.toString());
    // } catch (Exception ex) {
    // logger.error("Exception thrown for the restTemplate exchange: "
    // + ex.toString());
    // }
    //
    // return responseEntityArray;
    // }

    // // use API key if making 40 or more requests per minute or > 1000
    // requests
    // // per
    // // day / per IP
    // public ResponseEntity<String> getDrugEvents_display(String uri,
    // String apiKey) {
    // RestTemplate restTemplate = new RestTemplate();
    // restTemplate.setErrorHandler(new ClientErrorHandler());
    // HttpHeaders headers = new HttpHeaders();
    // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    // HttpEntity<String> entity = new HttpEntity<String>("parameters",
    // headers);
    //
    // ResponseEntity<String> responseEntity = null;
    // try {
    // responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
    // String.class);
    // // List listResponse = Arrays.asList(restTemplate.exchange(uri,
    // // HttpMethod.GET, entity, Object[].class));
    //
    // System.out.println(responseEntity.getBody());
    // } catch (Exception ex) {
    // logger.error("Exception thrown for the restTemplate exchange: "
    // + ex.toString());
    // }
    //
    // return responseEntity;
    // }

    // public List<AdverseDrugEvent> getDrugEvents(String SERVER_URI, String
    // apiKey) {
    // // String uri = "https://api.fda.gov/drug/event.json";
    // //
    // "https://api.fda.gov/drug/event.json?search={dateRange}&count=receivedate";
    // // String dateRange = "receivedate:[20140101 TO 20150101]";
    // // URI expanded = new UriTemplate(uri).expand(dateRange);
    // // try {
    // // uri = URLDecoder.decode(expanded.toString(), "UTF8");
    // // } catch (UnsupportedEncodingException e) {
    // // TODO Auto-generated catch block
    // // e.printStackTrace();
    // // }
    // // final String uri =
    // //
    // "https://api.fda.gov/drug/event.json?search=patient.drug.openfda.pharm_class_epc:nonsteroidal%2Banti-inflammatory%2Bdrug&count=patient.reaction.reactionmeddrapt.exact";
    //
    // RestTemplate restTemplate = new RestTemplate();
    // MultiValueMap<String, Object> headers =
    // new LinkedMultiValueMap<String, Object>();
    // headers.add("Accept", "application/json");
    // headers.add("Content-Type", "application/json");
    //
    // final String urlPlusAPIKey = SERVER_URI + "&api+key={api-key}";
    //
    // String termId = "NAUSEA";
    // String requestBody = "{\"term\":\"" + termId + "\"}";
    // HttpEntity request = new HttpEntity(requestBody, headers);
    //
    // Object events = restTemplate.getForObject(SERVER_URI, Object.class);
    // // AdverseDrugEvent events =
    // // restTemplate.getForObject(SERVER_URI, AdverseDrugEvent.class);
    // // List<AdverseDrugEvent> events =
    // // restTemplate.getForObject(SERVER_URI, List.class);
    // // List<LinkedHashMap> events = restTemplate.getForObject(SERVER_URI,
    // // List.class, apiKey);
    // // List<LinkedHashMap> events = restTemplate.getForObject(SERVER_URI,
    // // List.class, "results");
    //
    // List<AdverseDrugEvent> eventsList = new LinkedList<AdverseDrugEvent>();
    // // System.out.println(events.size());
    // // int counter = 0;
    // // for (LinkedHashMap event : events) {
    // // System.out.println("Term=" + event.get("term") + ",Count="
    // // + event.get("count"));
    // //
    // // AdverseDrugEvent adverseReaction = new AdverseDrugEvent();
    // // adverseReaction.setEvent_id(counter++);
    // // adverseReaction.setTerm((String) event.get("term"));
    // // adverseReaction.setReaction_count((long) event.get("count"));
    // // eventsList.add(adverseReaction);
    // // }
    //
    // return eventsList;
    // }
}