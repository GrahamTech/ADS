package com.grahamtech.ads.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.grahamtech.ads.daos.MyAdverseDrugEventDAO;
import com.grahamtech.ads.pojos.AdverseDrugEventResultFlattened;
import com.grahamtech.ads.pojos.AdverseDrugEvent;
import com.grahamtech.ads.pojos.Results;
import com.grahamtech.ads.pojos.StatusMessage;
import com.grahamtech.ads.services.RestClient;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Agile Delivery Service</h1> The Base Controller is the main entry point
 * and exit point for incoming and outgoing requests. This controller dispatches
 * requests to the internal processing functions of ADS and forwards responses
 * to the appropriate view.
 * <p>
 * This Controller exposes the following REST URL
 * 
 * @author Rodney Morris
 * @version 1.0
 * @since 2015-06-24
 */
@Controller
@RequestMapping(RestURIConstants.CONTROLLER_PREFIX)
public class BaseController {
  private static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

    @Autowired
    private MyAdverseDrugEventDAO myAdverseDrugEventDAO;

    // TODO: Re-factor: clean-up commented code.
    // http://localhost:8080/ADS/gt/get/drug/events/apikey/3
    // https://api.fda.gov/drug/event.json
//    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_CALL_WITH_API_KEY, method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<AdverseDrugEvent> getAdverseDrugEvents_apiKey(
//	    @PathVariable String rowLimit) {
//	logger.debug("Row limit: " + rowLimit);
//
//	RestClient restClient = new RestClient();
//	String queryString = "?limit=" + rowLimit;
//	String externalURL = RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
//		+ queryString;
//	ResponseEntity<AdverseDrugEvent> events = restClient
//		.getDrugEvents_apiKey(
//			externalURL,
//		RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY_HEADER,
//		RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);
//
//	logger.debug(events.toString());
//	return events;
//    }

    // http://localhost:8080/ADS/gt/get/drug/events/apikey?rowLimit={id}
    // https://api.fda.gov/drug/event.json
//    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_CALL_WITH_API_KEY_ROWLIMIT_PARAM, method = RequestMethod.GET)
//    public @ResponseBody
//    ResponseEntity<AdverseDrugEvent> getAdverseDrugEvents_apiKey_ByRowParam(
//	    @RequestParam(value = "rowLimit", required = true) String rowLimit) {
//	logger.debug("Row limit: " + rowLimit);
//
//	RestClient restClient = new RestClient();
//	String queryString = "?limit=" + rowLimit;
//	String externalURL = RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL
//		+ queryString;
//	ResponseEntity<AdverseDrugEvent> events = restClient
//		.getDrugEvents_apiKey(
//			externalURL,
//			RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY_HEADER,
//			RestURIConstants.ADVERSE_DRUG_EVENT_REPORTS_API_KEY);
//
//	logger.debug(events.toString());
//	return events;
//    }

    /**
     * Calls the Adverse Drug Events external REST web service provided by
     * https://api.fda.gov/drug/event.json, retrieves JSON and stores a subset
     * of the JSON event data in the local data store.
     * http://localhost:8080/ADS/gt/get/drug/events/and/store/apikey/3
     * 
     * @param rowLimit
     *            Used to limit the number of event results returned from the
     *            provider.
     * @return ResponseEntity<AdverseDrugEvent> Indicates an Http Status message
     *         and returns the event via JSON.
     * @see AdverseDrugEvent
     */
    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_AND_STORE_CALL_WITH_API_KEY, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> getAdverseDrugEventsAndStore_apiKey(
	    @PathVariable String rowLimit) {
	logger.debug("Row limit: " + rowLimit);

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

    // http://localhost:8080/ADS/gt/get/drug/events/and/store/apikey?rowLimit={id}
    @RequestMapping(value = RestURIConstants.GET_DRUG_EVENTS_AND_STORE_CALL_WITH_API_KEY_ROWLIMIT_PARAM, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> getAdverseDrugEventsAndStore_apiKey_RowLimitByParam(
	    @RequestParam(value = "rowLimit", required = true) String rowLimit) {
	return this.getAdverseDrugEventsAndStore_apiKey(rowLimit);
    }

    private String storeEvents(AdverseDrugEvent entity) {
	logger.debug(entity.toString());
	
	StringBuffer strBuffer = new StringBuffer();
	int bufferLength = strBuffer.length();
	try {
	    for (Results result : entity.getResults()) {
		List<AdverseDrugEventResultFlattened> entityInDBList = myAdverseDrugEventDAO
			.findEventBySafetyReportId(result.getSafetyreportid());
		if (entityInDBList.size() == 0) {
		    //didn't find it, so create it.
		    myAdverseDrugEventDAO.save(new AdverseDrugEventResultFlattened(
				result));
		} else {
		    strBuffer
			    .append(" ConstraintViolationException saving the entity Adverse Drug Event with safety report id: "
				    + result.getSafetyreportid());
		    logger.warn(" ConstraintViolationException saving the entity Adverse Drug Event with safety report id: "
			    + result.getSafetyreportid());
		}

	    }
	} catch (ConstraintViolationException ec) {
	    strBuffer
		    .append(" ConstraintViolationException saving the entity: AdverseDrugEvent");
	    logger.error(" ConstraintViolationException saving the entity: AdverseDrugEvent");
	} catch (RuntimeException e) {
	    strBuffer
		    .append(" RuntimeException saving the entity: AdverseDrugEvent");
	    logger.error(" RuntimeException saving the entity: AdverseDrugEvent");
	} catch (Exception ex) {
	    strBuffer.append(" Exception saving the entity: AdverseDrugEvent");
	    logger.error(" Exception saving the entity: AdverseDrugEvent");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer.append("Created successfully: AdverseDrugEvent");
	    logger.debug("Created successfully: AdverseDrugEvent");
	}
	return strBuffer.toString();
    }

    // http://localhost:8080/ADS/gt/read/db/events
    /*@RequestMapping(value = RestURIConstants.READ_DB_EVENTS, method = RequestMethod.GET)
    public @ResponseBody
    List<AdverseDrugEventResultFlattened> readDBEvents() {
	List<AdverseDrugEventResultFlattened> list = myAdverseDrugEventDAO
		.findAll();

	List<AdverseDrugEventResultFlattened> listFlattened = new ArrayList<AdverseDrugEventResultFlattened>();
	for (AdverseDrugEventResultFlattened item : list) {
	    item.setPatient_reactions(item.flattenFromDB(item
		    .getPatient_reactions()));
	    listFlattened.add(item);
	}
	return listFlattened;
    }*/

    /**
     * Returns all the Adverse Drug Events stored in the local data store
     * http://localhost:8080/ADS/gt/read/db/events
     * 
     * @param None
     * @return ResponseEntity<AdverseDrugEvent> Indicates an Http Status message
     *         and returns the local DB event via JSON.
     * @see AdverseDrugEvent
     */
    @RequestMapping(value = RestURIConstants.READ_DB_EVENTS, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> readDBEvents() {
	List<AdverseDrugEventResultFlattened> list = myAdverseDrugEventDAO
		.findAll();

	List<AdverseDrugEvent> listEvents = new ArrayList<AdverseDrugEvent>();
	for (AdverseDrugEventResultFlattened item : list) {
	    item.setPatient_reactions(item.flattenFromDB(item
		    .getPatient_reactions()));
	    AdverseDrugEvent adverseDrugEvent = new AdverseDrugEvent();
	    adverseDrugEvent = adverseDrugEvent.flattenEventObjectDTO(item);
	    
	    listEvents.add(adverseDrugEvent);
	}
	
	AdverseDrugEvent adverseDrugEvent = new AdverseDrugEvent();
	adverseDrugEvent = adverseDrugEvent.combineDBEvents(listEvents);
	ResponseEntity<AdverseDrugEvent> responseEntity = new ResponseEntity<AdverseDrugEvent> (adverseDrugEvent, HttpStatus.OK);
	return responseEntity;
    }
    
    /**
     * Returns a Adverse Drug Events stored in the local data store
     * http://localhost:8080/ADS/gt/read/db/event?event_id={id}
     * 
     * @param event_id
     *            The id in the local data store to retrieve.
     * @return ResponseEntity<AdverseDrugEvent> Indicates an Http Status message
     *         and returns the local DB event via JSON.
     * @see AdverseDrugEvent
     */
    @RequestMapping(value = RestURIConstants.READ_DB_EVENT_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> readDBEventById(
	    @RequestParam(value = "event_id", required = true) long event_id) {
	
	AdverseDrugEvent adverseDrugEvent = new AdverseDrugEvent();
	// StringBuffer strBuffer = new StringBuffer();
	// StatusMessage statusMessage = new StatusMessage();
	// Boolean idIsValid = new Boolean(true);
	// long eventIdLong = 0;
	// try {
	// eventIdLong = new Long(event_id);
	// } catch (NumberFormatException nex) {
	// idIsValid = new Boolean(false);
	// strBuffer
	// .append("Error deleting the entity: AdverseDrugEventResultFlattened");
	// statusMessage.setStatus(RestURIConstants.STATUS_MESSAGE_FAILURE);
	// statusMessage
	// .setStatusDetails("Error: Event ID must be a valid number");
	// logger.debug("Error: Event ID is not a number. User entered: "
	// + event_id);
	// }
	//
	// if(idIsValid.booleanValue()){
	    AdverseDrugEventResultFlattened event = myAdverseDrugEventDAO
		.findById(event_id);

		event.setPatient_reactions(event.flattenFromDB(event
			.getPatient_reactions()));
		
	adverseDrugEvent = adverseDrugEvent.flattenEventObjectDTO(event);
	// }

	ResponseEntity<AdverseDrugEvent> responseEntity = new ResponseEntity<AdverseDrugEvent>(
		adverseDrugEvent, HttpStatus.OK);
	return responseEntity;
    }

    /**
     * Returns a Adverse Drug Events stored in the local data store
     * http://localhost:8080/ADS/gt/read/db/event/{id}
     * 
     * @param event_id
     *            The id in the local data store to retrieve.
     * @return ResponseEntity<AdverseDrugEvent> Indicates an Http Status message
     *         and returns the local DB event via JSON.
     * @see AdverseDrugEvent
     */
    @RequestMapping(value = RestURIConstants.READ_DB_EVENT_BY_ID_PATH_VARIABLE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdverseDrugEvent> readDBEventByIdPathVariabl(
	    @PathVariable String event_id) {
	return this.readDBEventById(new Long(event_id).longValue());
    }

    /**
     * Creates an Adverse Drug Events in the local data store and returns an
     * indication of success or failure.
     * http://localhost:8080/ADS/gt/create/db/event
     * 
     * @param entity
     *            Captures the fields sent in the request associated with an
     *            Adverse Drug Event in local data store format. Flattened for
     *            prototype purposes.
     * @return StatusMessage Indicates the status of the change and a detailed
     *         message returned via JSON.
     * @see StatusMessage, AdverseDrugEventResultFlattened
     */
    @RequestMapping(value = RestURIConstants.CREATE_DB_EVENT, method = RequestMethod.POST)
    public @ResponseBody
    StatusMessage createDBEvent(
	    @RequestBody AdverseDrugEventResultFlattened entity) {

	logger.debug(entity.toString());

	StringBuffer strBuffer = new StringBuffer();
	StatusMessage statusMessage = new StatusMessage();
	List<AdverseDrugEventResultFlattened> entityInDBList = myAdverseDrugEventDAO
		.findEventBySafetyReportId(entity.getSafetyreportid());
	if (entityInDBList.size() == 0) {
	    // didn't find it, so create it.
	    int bufferLength = strBuffer.length();
	    try {
		myAdverseDrugEventDAO.save(entity);
	    } catch (ConstraintViolationException ec) {
		strBuffer
			.append(" ConstraintViolationException updating the entity: AdverseDrugEventResultFlattened");
		statusMessage
			.setStatus(RestURIConstants.STATUS_MESSAGE_FAILURE);
		statusMessage
			.setStatusDetails(RestURIConstants.STATUS_MESSAGE_DETAILS
				+ "bc_c_001");
		logger.error(" ConstraintViolationException updating the entity: AdverseDrugEventResultFlattened: bc_c_001");
	    } catch (RuntimeException e) {
		strBuffer
			.append(" RuntimeException updating the entity: AdverseDrugEventResultFlattened");
		statusMessage
			.setStatus(RestURIConstants.STATUS_MESSAGE_FAILURE);
		statusMessage
			.setStatusDetails(RestURIConstants.STATUS_MESSAGE_DETAILS
				+ "bc_c_002");
		logger.error(" RuntimeException updating the entity: AdverseDrugEventResultFlattened: bc_c_002");
	    }
	    if (strBuffer.length() == bufferLength) {
		strBuffer
			.append("Advers Drug Event record stored successfully");
		statusMessage
			.setStatus(RestURIConstants.STATUS_MESSAGE_SUCCESS);
		statusMessage
			.setStatusDetails("Advers Drug Event record stored successfully");
	    }
	    logger.info(strBuffer.toString() + ": id=" + entity.getEvent_id());

	} else {
	    strBuffer
		    .append(" ConstraintViolationException saving the entity Adverse Drug Event with safety report id: "
			    + entity.getSafetyreportid());
	    logger.warn(" ConstraintViolationException saving the entity Adverse Drug Event with safety report id: "
		    + entity.getSafetyreportid());
	} // end else if

	return statusMessage;
    }

    /**
     * Updates an Adverse Drug Events in the local data store and returns an
     * indication of success or failure.
     * http://localhost:8080/ADS/gt/update/db/event
     * 
     * @param entity
     *            Captures the fields sent in the request associated with an
     *            Adverse Drug Event in local data store format. Flattened for
     *            prototype purposes.
     * @return StatusMessage Indicates the status of the change and a detailed
     *         message returned via JSON.
     * @see StatusMessage, AdverseDrugEventResultFlattened
     */
    @RequestMapping(value = RestURIConstants.UPDATE_DB_EVENT, method = RequestMethod.POST)
    public @ResponseBody
    StatusMessage updateDBEvent(
	    @RequestBody AdverseDrugEventResultFlattened entity) {

	logger.debug(entity.toString());

	StatusMessage statusMessage = new StatusMessage();
	StringBuffer strBuffer = new StringBuffer();
	int bufferLength = strBuffer.length();
	try {
	    List<AdverseDrugEventResultFlattened> eventsList = myAdverseDrugEventDAO
		    .findEventBySafetyReportId(entity.getSafetyreportid());
	    for (AdverseDrugEventResultFlattened event : eventsList) {
		event.setCompanynumb(entity.getCompanynumb());
		//event.setPatient_reactions(entity.getPatient_reactions());
		event.setSenderorganization(entity.getSenderorganization());
		event.setSerious(entity.getSerious());
		myAdverseDrugEventDAO.update(event);
	    }
	} catch (RuntimeException e) {
	    strBuffer
		    .append("Error updating the entity: AdverseDrugEventResultFlattened");
	    statusMessage.setStatus(RestURIConstants.STATUS_MESSAGE_FAILURE);
	    statusMessage
		    .setStatusDetails(RestURIConstants.STATUS_MESSAGE_DETAILS
			    + "bc_u_001");
	    logger.error("Error updating the entity: AdverseDrugEventResultFlattened bc_u_001");
	}
	if (strBuffer.length() == bufferLength) {
	    strBuffer.append("Advers Drug Event record updated successfully");
	    statusMessage.setStatus(RestURIConstants.STATUS_MESSAGE_SUCCESS);
	    statusMessage
		    .setStatusDetails("Advers Drug Event record stored successfully");
	}
	logger.info(strBuffer.toString() + ": id=" + entity.getEvent_id());

	return statusMessage;
    }

    /**
     * Deletes an Adverse Drug Events in the local data store and returns an
     * indication of success or failure.
     * http://localhost:8080/ADS/gt/delete/db/event?event_id={id}
     * 
     * @param event_id
     *            The id to remove from the local data store
     * @return StatusMessage Indicates the status of the change and a detailed
     *         message returned via JSON.
     * @see StatusMessage
     */
    @RequestMapping(value = RestURIConstants.DELETE_DB_EVENT_BY_ID, method = {
	    RequestMethod.GET, RequestMethod.PUT })
    public @ResponseBody
    StatusMessage deleteDBEventById(
	    @RequestParam(value = "event_id", required = true) String event_id) {
	logger.debug("Event ID: " + event_id);
	StatusMessage statusMessage = new StatusMessage("Failure",
		"the message");
	StringBuffer strBuffer = new StringBuffer();
	Boolean idIsValid = new Boolean(true);
	long eventIdLong = 0;
	try {
	    eventIdLong = new Long(event_id);
	} catch (NumberFormatException nex) {
	    idIsValid = new Boolean(false);
	    strBuffer
		    .append("Error deleting the entity: AdverseDrugEventResultFlattened");
	    statusMessage.setStatus(RestURIConstants.STATUS_MESSAGE_FAILURE);
	    statusMessage
		    .setStatusDetails("Error: Event ID must be a valid number");
	    logger.debug("Error: Event ID is not a number. User entered: "
		    + event_id);
	}

	int bufferLength = strBuffer.length();

	if (idIsValid.booleanValue()) {
	    try {
		AdverseDrugEventResultFlattened entity = new AdverseDrugEventResultFlattened();
		entity.setEvent_id(eventIdLong);
		myAdverseDrugEventDAO.delete(entity);
	    } catch (RuntimeException e) {
		strBuffer
			.append("Error deleting the entity: AdverseDrugEventResultFlattened");
		statusMessage
			.setStatus(RestURIConstants.STATUS_MESSAGE_FAILURE);
		statusMessage
			.setStatusDetails(RestURIConstants.STATUS_MESSAGE_DETAILS
				+ "bc_d_001");
		logger.error("Error deleting the entity: AdverseDrugEventResultFlattened");
	    }
	    if (strBuffer.length() == bufferLength) {
		strBuffer.append("Advers Drug Event deleted successfully");
		statusMessage
			.setStatus(RestURIConstants.STATUS_MESSAGE_SUCCESS);
		statusMessage
			.setStatusDetails("Advers Drug Event deleted successfully");
	    }
	}

	logger.info(strBuffer.toString() + ": id=" + event_id);
	return statusMessage;
    }

    /**
     * Deletes an Adverse Drug Events in the local data store and returns an
     * indication of success or failure.
     * http://localhost:8080/ADS/gt/delete/db/event/{event_id}
     * 
     * @param event_id
     *            The id to remove from the local data store
     * @return StatusMessage Indicates the status of the change and a detailed
     *         message returned via JSON.
     * @see StatusMessage
     */
    @RequestMapping(value = RestURIConstants.DELETE_DB_EVENT_BY_ID_PATH_VARIABLE, method = {
	    RequestMethod.GET, RequestMethod.PUT })
    public @ResponseBody
    StatusMessage deleteDBEventByIdPathVariable(@PathVariable String event_id) {
	return this.deleteDBEventById(event_id);
    }

    // http://localhost:8080/ADS/gt/index
    @RequestMapping(value = RestURIConstants.INDEX, method = RequestMethod.GET)
    public ModelAndView index() {
	ModelAndView model = new ModelAndView("/grahamtech/index.html");
	// model.addObject("message", "Adverse Drug Events");
	return model;
    }
}
