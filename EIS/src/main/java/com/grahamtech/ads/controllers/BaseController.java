package com.grahamtech.ads.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.grahamtech.ads.daos.MyAdverseDrugEventDAO;
import com.grahamtech.ads.pojos.AdversDrugEventResultFlattened;
import com.grahamtech.ads.pojos.AdverseDrugEvent;
import com.grahamtech.ads.pojos.Results;
import com.grahamtech.ads.services.RestClient;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(RestURIConstants.CONTROLLER_PREFIX)
public class BaseController {
  private static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

    @Autowired
    private MyAdverseDrugEventDAO myAdverseDrugEventDAO;

    // http://localhost:8080/ADS/gt/get/drug/events/apikey/3
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

    // http://localhost:8080/ADS/gt/get/drug/events/and/store/apikey/3
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
		    .append(" ConstraintViolationException saving the entity: AdverseDrugEvent");
	} catch (RuntimeException e) {
	    strBuffer
		    .append(" RuntimeException saving the entity: AdverseDrugEvent");
	} catch (Exception ex) {
	    strBuffer.append(" Exception saving the entity: AdverseDrugEvent");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer.append("Created successfully: AdverseDrugEvent");
	}
	return strBuffer.toString();
    }

    // use spring tag lib to create spring form which binds the form fields to
    // the same getter/setter names in the ModelAttribute object. Use 'entity'
    // to bind in you JSP file

    // List<AdversDrugEventResultFlattened> createDBEvent(
    // @ModelAttribute("entity") AdversDrugEventResultFlattened entity) {

    // http://localhost:8080/ADS/gt/create/db/event
    @RequestMapping(value = RestURIConstants.CREATE_DB_EVENT, method = RequestMethod.POST)
    public @ResponseBody
    List<AdversDrugEventResultFlattened> createDBEvent(
	    @RequestBody AdverseDrugEvent entity) {

	logger.info(entity.toString());

	StringBuffer strBuffer = new StringBuffer();
	int bufferLength = strBuffer.length();
	try {
	    for (Results result : entity.getResults()) {
		myAdverseDrugEventDAO.save(new AdversDrugEventResultFlattened(
			result));
	    }
	} catch (ConstraintViolationException ec) {
	    strBuffer
		    .append(" ConstraintViolationException updating the entity: AdversDrugEventResultFlattened");
	} catch (RuntimeException e) {
	    strBuffer
		    .append(" RuntimeException updating the entity: AdversDrugEventResultFlattened");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer
		    .append("Created successfully: AdversDrugEventResultFlattened");
	}
	logger.info(strBuffer.toString());
	return readDBEvents();
    }

    // http://localhost:8080/ADS/gt/read/db/events
    @RequestMapping(value = RestURIConstants.READ_DB_EVENTS, method = RequestMethod.GET)
    public @ResponseBody
    List<AdversDrugEventResultFlattened> readDBEvents() {
	List<AdversDrugEventResultFlattened> list = myAdverseDrugEventDAO
		.findAll();
	return list;
    }

    // http://localhost:8080/ADS/gt/read/db/event/{id}
    @RequestMapping(value = RestURIConstants.READ_DB_EVENT_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    AdversDrugEventResultFlattened readDBEventById(@PathVariable String id) {
	AdversDrugEventResultFlattened event = myAdverseDrugEventDAO
		.findById(new Long(id).longValue());

	return event;
    }

    // http://localhost:8080/ADS/gt/update/db/event/params/{id}
    @RequestMapping(value = RestURIConstants.UPDATE_DB_EVENT_BY_ID, method = RequestMethod.POST)
    public @ResponseBody
    List<AdversDrugEventResultFlattened> updateDBEventById(
	    @PathVariable long id,
	    @RequestParam(value = "safetyreportid", required = true) String safetyreportid,
	    @RequestParam(value = "senderorganization", required = true) String sender,
	    @RequestParam(value = "serious", required = true) String serious,
	    @RequestParam(value = "companynumb", required = true) String companynumb,
	    @RequestParam(value = "reactionmeddrapt", required = true) String reactionmeddrapt) {

	logger.info("safetyreportid=" + safetyreportid);
	// StringBuffer strBuffer = new StringBuffer();
	// int bufferLength = strBuffer.length();
	// try {
	// for (Results result : entity.getResults()) {
	// myAdverseDrugEventDAO.merge(new AdversDrugEventResultFlattened(
	// result));
	// }
	// } catch (RuntimeException e) {
	// strBuffer
	// .append("Error updating the entity: AdversDrugEventResultFlattened");
	// }
	// if (strBuffer.length() == bufferLength) {
	// strBuffer
	// .append("Updated successfully: AdversDrugEventResultFlattened");
	// }
	// logger.info(strBuffer.toString());

	List<AdversDrugEventResultFlattened> dbReturnList = readDBEvents();
	// Convert from flattened to external object format for JSON
	// presentation to the browser
	List<AdverseDrugEvent> adverseDrugEventList = new ArrayList<AdverseDrugEvent>();
	for (AdversDrugEventResultFlattened flattenedItem : dbReturnList) {
	    Results event = new Results();
	    event.setCompanynumb(flattenedItem.getCompanynumb());

	    // event.setPatient(patient);

	}
	return dbReturnList;
    }

    // http://localhost:8080/ADS/gt/update/db/event/{id}
    @RequestMapping(value = RestURIConstants.UPDATE_DB_EVENT_BY_ID_AND_PARAMS, method = RequestMethod.POST)
    public @ResponseBody
    List<AdversDrugEventResultFlattened> updateDBEventByIdParams(
	    @PathVariable long id,
 @RequestBody AdverseDrugEvent entity) {
	StringBuffer strBuffer = new StringBuffer();
	int bufferLength = strBuffer.length();
	try {
	    for (Results result : entity.getResults()) {
		myAdverseDrugEventDAO.merge(new AdversDrugEventResultFlattened(
			result));
	    }
	} catch (RuntimeException e) {
	    strBuffer
		    .append("Error updating the entity: AdversDrugEventResultFlattened");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer
		    .append("Updated successfully: AdversDrugEventResultFlattened");
	}
	logger.info(strBuffer.toString());

	List<AdversDrugEventResultFlattened> dbReturnList = readDBEvents();
	// Convert from flattened to external object format for JSON
	// presentation to the browser
	List<AdverseDrugEvent> adverseDrugEventList = new ArrayList<AdverseDrugEvent>();
	for (AdversDrugEventResultFlattened flattenedItem : dbReturnList) {
	    Results event = new Results();
	    event.setCompanynumb(flattenedItem.getCompanynumb());

	    // event.setPatient(patient);

	}
	return dbReturnList;
    }

    // http://localhost:8080/ADS/gt/delete/db/event/{id}
    @RequestMapping(value = RestURIConstants.DELETE_DB_EVENT_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    List<AdversDrugEventResultFlattened> deleteDBEventById(@PathVariable long id) {
	StringBuffer strBuffer = new StringBuffer();
	int bufferLength = strBuffer.length();
	try {
	    AdversDrugEventResultFlattened entity = new AdversDrugEventResultFlattened();
	    entity.setEvent_id(id);
	    myAdverseDrugEventDAO.delete(entity);
	} catch (RuntimeException e) {
	    strBuffer
		    .append("Error deleting the entity: AdversDrugEventResultFlattened");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer
		    .append("Deleted successfully: AdversDrugEventResultFlattened");
	}
	logger.info(strBuffer.toString());
	return readDBEvents();
    }

    // http://localhost:8080/ADS/gt/index
    @RequestMapping(value = RestURIConstants.INDEX, method = RequestMethod.GET)
    public ModelAndView index() {
	ModelAndView model = new ModelAndView("index");
	model.addObject("message", "Adverse Drug Events");
	return model;
    }
}
