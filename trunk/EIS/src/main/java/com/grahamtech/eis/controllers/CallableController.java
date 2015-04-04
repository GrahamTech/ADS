package com.grahamtech.eis.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.grahamtech.eis.daos.MyNVDEntryMessageDAO;
import com.grahamtech.eis.pojos.NVDEntryMessage;
import com.grahamtech.eis.services.RSSFeedParser;
@Controller
@RequestMapping(RestURIConstants.ASYNC_CALLABLE)
public class CallableController {

  private static final Logger logger = LoggerFactory
      .getLogger(CallableController.class);

  @Autowired
  private MyNVDEntryMessageDAO myNVDEntryMessageDAO;

  @RequestMapping(RestURIConstants.ASYNC_RESPONSE_BODY)
  public @ResponseBody
  Callable<String> callable() {
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        logger.info("############### START response-body > call.");
        Thread.sleep(6000);
        return "Callable result";
      }
    };
  }

  @RequestMapping(RestURIConstants.ASYNC_VIEW)
  public Callable<String> callableWithView(final Model model) {
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        logger.info("############### START view > call.");
        Thread.sleep(6000);
        model.addAttribute("message1", "async message 1 test");
        model.addAttribute("message2", "async message 2 test");
        return "callable";
      }
    };
  }

  @RequestMapping(RestURIConstants.ASYNC_EXCEPTION)
  public @ResponseBody
  Callable<String> callableWithException(
      final @RequestParam(required = false, defaultValue = "true") boolean handled) {
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        logger.info("############### START exception > call.");
        Thread.sleep(6000);
        if (handled) {
          // see handleException method further below
          throw new IllegalStateException("Callable error");
        } else {
          throw new IllegalArgumentException("Callable error");
        }
      }
    };
  }

  @RequestMapping(RestURIConstants.ASYNC_CUSTOM_TIMEOUT_HANDLING)
  public @ResponseBody
  WebAsyncTask<String> callableWithCustomTimeoutHandling() {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        logger.info("############### START custom-timeout-handling > call.");
        Thread.sleep(6000);
        return "Callable result";
      }
    };
    return new WebAsyncTask<String>(1000, callable);
  }

  @ExceptionHandler
  @ResponseBody
  public String handleException(IllegalStateException ex) {
    return "Handled exception: " + ex.getMessage();
  }

  @RequestMapping(value = RestURIConstants.RSS_NVD_20_ALL)
  public @ResponseBody
  WebAsyncTask<String> getRssNVDAll() {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        logger.info("############### START getRssNVDAll.");

        getDataFromNVDFeedAndStore(RestURIConstants.RSS_NVD_CVE_20_2015_URL);
        getDataFromNVDFeedAndStore(RestURIConstants.RSS_NVD_CVE_20_2014_URL);

        logger.info("############### FINISH getRssNVDAll.");
        return "NVD Feed Yearly Import Complete.";
      }
    };
    return new WebAsyncTask<String>(10000000, callable);
  }

  // TODO research why the modified XML feed is causing a malformed xml error
  // when reading
  @RequestMapping(value = RestURIConstants.RSS_NVD_20_MODIFIED)
  public @ResponseBody
  WebAsyncTask<String> getRssNVDModified() {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        logger.info("############### START getRssNVDModified.");

        getModifiedDataFromNVDFeedAndUpdate(RestURIConstants.RSS_NVD_CVE_20_MODIFIED_URL);

        logger.info("############### FINISH getRssNVDModified.");
        return "NVD Feed Modificaton Update Complete.";
      }
    };
    return new WebAsyncTask<String>(10000000, callable);
  }

  private void getModifiedDataFromNVDFeedAndUpdate(String uriConstant) {
    logger.info("############### START getModifiedDataFromNVDFeedAndUpdate: "
        + uriConstant);
    RSSFeedParser rssFeedParser = new RSSFeedParser(uriConstant);
    List<NVDEntryMessage> feedEntries = rssFeedParser.readFeed();
    // TODO increase the maxNvdEntries for production
    int maxNvdEntries = 5;
    int storedEntriesCount = 0;
    for (NVDEntryMessage message : feedEntries) {
      if (storedEntriesCount > maxNvdEntries) {
        break;
      }
      logger.info("Updating: " + message.toString());
      myNVDEntryMessageDAO.merge(message);
      logger.info("Successfully Updated " + message.getCve_id());

      storedEntriesCount++;
    }
    logger.info("############### FINISH getModifiedDataFromNVDFeedAndUpdate: "
        + uriConstant);
  }

  private void getDataFromNVDFeedAndStore(String uriConstant) {
    logger.info("############### START getDataFromNVDFeedAndStore: "
        + uriConstant);
    RSSFeedParser rssFeedParser = new RSSFeedParser(uriConstant);
    List<NVDEntryMessage> feedEntries = rssFeedParser.readFeed();
    // TODO increase the maxNvdEntries for production
    int maxNvdEntriesStored = 5;
    int storedEntriesCount = 0;
    for (NVDEntryMessage message : feedEntries) {
      if (storedEntriesCount > maxNvdEntriesStored) {
        break;
      }
      // logger.info("Saving: " + message.toString());
      // logger.info("Vulnerable Software List: "
      // + message.getVulnerability_software_list().toString());

      try {
      myNVDEntryMessageDAO.save(message);
      } catch (ConstraintViolationException e) {
        logger.warn("Failed to save object " + message.getCve_id()
            + ", due to ConstraintViolationException.");
      }

      storedEntriesCount++;
    }
    logger.info("############### FINISH getDataFromNVDFeedAndStore: "
        + uriConstant);
  }
}