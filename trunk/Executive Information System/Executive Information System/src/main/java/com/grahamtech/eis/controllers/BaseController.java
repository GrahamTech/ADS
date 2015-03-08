package com.grahamtech.eis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/")
public class BaseController {
 
  @RequestMapping(method = RequestMethod.GET)
  public String index(ModelMap model) {
 
    model.addAttribute("message", "Welcome to the Executive Information System (EIS) enterprise application!");
 
    //Spring uses the InternalResourceViewResolver defined in 
    // mvc-dispatcher-servlet.xml to prepend and append onto 
    // the view name which returns index.jsp for this scenario
    return "index";
 
  }
  
  @RequestMapping(value="welcome/{name}", method = RequestMethod.GET)
  public String welcomeName(@PathVariable String name, ModelMap model) {
 
    model.addAttribute("message", "Welcome to the Executive Information System: " + name);
    return "index";
 
  }
  
  @RequestMapping(value="overview", method = RequestMethod.GET)
  public String welcome(ModelMap model) {
 
    String overviewStr = "<p>Graham Technologies aims to provide an on-line Executive Information System (EIS) where "
        + "risk perception is made reality to enhance enterprise-wide visibility, collaboration & decision-making. "
        + "The application will assess functional risks and visualize prioritized dashboard results for mission "
        + "projects, which will roll-up for executive-level enterprise analysis. Project analysis will be centered "
        + "on Business Continuity, Disaster Recovery, Configuration Automation, Incident Response Team Evaluation, "
        + "and IT System Risk Analysis (Hardware & Software). EIS will provide a condensed, enriched, graphical, "
        + "prioritized, and easily searchable view of projects and their associated assets. This full lifecycle Agile "
        + "software development and project management effort will ensure the efficiency, effectiveness, and data "
        + "integrity of full mission situational awareness for GRAHAM and GRAHAM clients on both desktop and mobile "
        + "platforms. The solution aims to include: "
        + "</br>End-to-End capabilities:  <ul><li>Project and Product Risk Rating"
        + " Visualization</li><li>Geospatial representation of mission assets</li><li>Key performance indicators "
        + "(Metrics, Trending)</li><li>Compliance, Access and Data Integrity</li></ul>"
        + "Foundational frameworks & processes: <ul><li>Agile Software Development (SCRUM)</li><li>Mobile Responsive</li>"
        + "<li>Enterprise Application Development</li><li>Application Security</li><li>Open Source Software Development</li>"
        + "</ul></p>";
    model.addAttribute("message", overviewStr);
 
    return "index";
 
  }
 
}
