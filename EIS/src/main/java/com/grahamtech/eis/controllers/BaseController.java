package com.grahamtech.eis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.grahamtech.eis.daos.MyAdverseDrugEventDAO;
import com.grahamtech.eis.pojos.AdversDrugEventResultFlattened;
import com.grahamtech.eis.pojos.AdverseDrugEvent;
import com.grahamtech.eis.pojos.Results;
import com.grahamtech.eis.services.RestClient;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/gt")
public class BaseController {
  private static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

    @Autowired
    private MyAdverseDrugEventDAO myAdverseDrugEventDAO;

    // http://localhost:8080/EIS/gt/get/drug/events/apikey/3
    // https://api.fda.gov/drug/event.json
    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_CALL_WITH_API_KEY, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> getAdverseDrugEvents_apiKey(
	    @PathVariable String rowLimit) {
	RestClient restClient = new RestClient();
	String queryString = "?limit=" + rowLimit;
	String externalURL = RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
		+ queryString;
	ResponseEntity<AdverseDrugEvent> events = restClient
		.getDrugEvents_apiKey(
			externalURL,
		RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY_HEADER,
		RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);

	return events;
    }

    // http://localhost:8080/EIS/gt/get/drug/events/and/store/apikey/3
    // https://api.fda.gov/drug/event.json
    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_AND_STORE_CALL_WITH_API_KEY, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> getAdverseDrugEventsAndStore_apiKey(
	    @PathVariable String rowLimit) {
	RestClient restClient = new RestClient();
	String queryString = "?limit=" + rowLimit;
	String externalURL = RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
		+ queryString;
	ResponseEntity<AdverseDrugEvent> events = restClient
		.getDrugEvents_apiKey(
			externalURL,
			RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY_HEADER,
			RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);

	String success = storeEvents(events.getBody());
	logger.info(success);

	return events;
    }

    private String storeEvents(AdverseDrugEvent restObject) {
	StringBuffer strBuffer = new StringBuffer();
	int bufferLength = strBuffer.length();
	try {
	    for (Results result : restObject.getResults()) {
		myAdverseDrugEventDAO.save(new AdversDrugEventResultFlattened(
			result));
	    }
	} catch (ConstraintViolationException ec) {
	    strBuffer
		    .append(" ConstraintViolationException updating the entity: AdverseDrugEvent");
	} catch (RuntimeException e) {
	    strBuffer
		    .append(" RuntimeException updating the entity: AdverseDrugEvent");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer.append("Created successfully: AdverseDrugEvent");
	}
	return strBuffer.toString();
    }

    // http://localhost:8080/EIS/gt/get/drug/events/from/data/store
    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_FROM_DATA_STORE, method = RequestMethod.GET)
    public @ResponseBody
    List<AdversDrugEventResultFlattened> getAdverseDrugEventsFromDataStore() {

	List<AdversDrugEventResultFlattened> list = myAdverseDrugEventDAO
		.findAll();

	// logger.info(list.toString());

	return list;
    }

    // http://localhost:8080/EIS/gt/get/drug/events/3
    // https://api.fda.gov/drug/event.json
    // @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS, method =
    // RequestMethod.GET)
    // public @ResponseBody
    // AdverseDrugEvent getAdverseDrugEventsStoreAndReturnJSON(
    // @PathVariable String rowLimit) {
    // RestClient restClient = new RestClient();
    // String queryString = "?limit=" + rowLimit;
    // String externalURL =
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
    // + queryString;
    // AdverseDrugEvent events = restClient.getDrugEvents(externalURL);
    // return events;
    // }

    // @RequestMapping(value =
    // RestURIConstants.GET_DRUG_EVENTS_STORE_AND_DISPLAY_LIST, method =
    // RequestMethod.GET)
    // public @ResponseBody
    // List<ResponseEntity<AdverseDrugEvent[]>>
    // getAdverseDrugEventsStoreAndReturnJSON2() {
    // RestClient restClient = new RestClient();
    // String queryString = "";
    // String externalURL =
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
    // + queryString;
    //
    // List<ResponseEntity<AdverseDrugEvent[]>> events = restClient
    // .getDrugEvents_storeAndDisplay_list(externalURL,
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);
    //
    // return events;
    // }
    //
    // @RequestMapping(value =
    // RestURIConstants.GET_DRUG_EVENTS_STORE_AND_DISPLAY_ARRAY, method =
    // RequestMethod.GET)
    // public @ResponseBody
    // AdverseDrugEvent[] getAdverseDrugEventsStoreAndReturnJSON_ARRAY() {
    // RestClient restClient = new RestClient();
    // String queryString = "";
    // String externalURL =
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
    // + queryString;
    //
    // AdverseDrugEvent[] events = restClient
    // .getDrugEvents_storeAndDisplay_array(externalURL,
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);
    //
    // return events;
    // }

    // http://localhost:8080/EIS/gt/get/drug/events
  // https://api.fda.gov/drug/event.json
    // @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS, method =
    // RequestMethod.GET)
    // public @ResponseBody
    // ResponseEntity<String> getAdverseDrugEventsReturnJSON() {
    // RestClient restClient = new RestClient();
    //
    // // String fromStringDate_yyyyMMdd = "20140101";
    // // String toStringDate_yyyyMMdd = "20150101";
    // // String queryString =
    // // "?search=receivedate:[" + fromStringDate_yyyyMMdd + "+TO+"
    // // + toStringDate_yyyyMMdd + "]&count=receivedate";
    // // String queryString =
    // // "?search=patient.drug.openfda.pharm_class_epc:"
    // // + "nonsteroidal%2Banti-inflammatory%2Bdrug"
    // // + "&count=patient.reaction.reactionmeddrapt.exact";
    // String queryString = "";
    // String externalURL =
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
    // + queryString;
    //
    // ResponseEntity<String> events =
    // restClient.getDrugEvents_display(
    // externalURL,
    // RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);
    //
    // return events;
    // }
 
}
