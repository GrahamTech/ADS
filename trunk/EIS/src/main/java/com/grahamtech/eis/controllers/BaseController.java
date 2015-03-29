package com.grahamtech.eis.controllers;

//import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.grahamtech.eis.pojos.Project;
//import com.grahamtech.eis.daos.MyFlaggedAssetsDAO;
//import com.grahamtech.eis.daos.MyProjectSystemDAO;
//import com.grahamtech.eis.daos.MySystemVulnerabilitiesDAO;
//import com.grahamtech.eis.daos.AbstractDAO;
//import com.grahamtech.eis.daos.MyProjectDAO;
//import com.grahamtech.eis.daos.MyProjectDetailDAO;
//import com.grahamtech.eis.daos.MyRiskPreferenceDAO;
//import com.grahamtech.eis.daos.MyRolesDAO;
import com.grahamtech.eis.daos.MyUserProfileDAO;
//import com.grahamtech.eis.pojos.FlaggedAsset;
import com.grahamtech.eis.pojos.ProjectPartner;
import com.grahamtech.eis.pojos.ProjectSystem;
import com.grahamtech.eis.pojos.SystemProduct;
import com.grahamtech.eis.pojos.SystemVulnerability;
//import com.grahamtech.eis.pojos.DaoTypesEnum;
//import com.grahamtech.eis.pojos.RiskPreference;
//import com.grahamtech.eis.pojos.Role;
//import com.grahamtech.eis.pojos.RolesEnum;
//import com.grahamtech.eis.pojos.StatusEnum;
import com.grahamtech.eis.pojos.UserProfile;

//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.HashSet;
import java.util.List;
//import java.util.Map;
import java.util.Set;

//import javax.annotation.PostConstruct;
//import java.util.Set;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/")
public class BaseController {
  private static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

  @Autowired
  private MyUserProfileDAO myUserProfileDAO;

  // @Autowired
  // private MyRolesDAO myRolesDAO;
  // @Autowired
  // private MyRiskPreferenceDAO myRiskPreferenceDAO;
  // @Autowired
  // private MyProjectDAO myProjectDAO;
  // @Autowired
  // private MyProjectDetailDAO myProjectDetailDAO;
  // @Autowired
  // private MyProjectSystemDAO myProjectSystemDAO;
  // @Autowired
  // private MySystemVulnerabilitiesDAO mySystemVulnerabilitiesDAO;
  // @Autowired
  // private MyFlaggedAssetsDAO myFlaggedAssetsDAO;

  /*
   * A UserProfile has a Role, Risk Preferences, and one or more Project
   * associations. Projects have Systems, Partners, and Project Risk
   * (Schedule/Budget/FTE Utilization) as risk assets. Systems have Products and
   * Vulnerabilities. These risk assets may be Flagged for escalation review:
   * Projects, Partners, System Products and System Vulnerabilities.
   */
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView index() {
    ModelAndView model = new ModelAndView("index");

    List<UserProfile> listUserProfiles = getUserProfiles();
    model.addObject("userProfileList", listUserProfiles);

    return model;
  }

  @RequestMapping(value = RestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
  public @ResponseBody
  List<UserProfile> getAllUserProfiles() {
    logger.info("START getAllUserProfiles.");
    ModelAndView model = new ModelAndView("json");

    List<UserProfile> listUserProfiles = getUserProfiles();
    model.addObject("userProfileList", listUserProfiles);

    logger.info("FINISH getAllUserProfiles.");

    return listUserProfiles;
  }
  public List<UserProfile> getUserProfiles() {
    List<UserProfile> listUserProfiles = myUserProfileDAO.findAll();

    for (UserProfile userProfile : listUserProfiles) {

      logger.info("User Profile: " + userProfile.getEmail());
      logger.info("\n** User Profile Has Role: "
          + userProfile.getRole().getRole_name());
      logger.info("\n** User Profile Has Risk Preference Named: "
          + userProfile.getRiskPreference().getRisk_preference_name());

      Set<Project> projectSet = userProfile.getUserProjectSet();
      for (Project project : projectSet) {
        logger.info("\n** User Profile Has Project: "
            + project.getProject_name());
            
        // Project Plan Risk (Schedule, Budget, FTE Utilization)
        logger.info("\n**** Project Has Budget Variance of : "
            + project.getProjectDetail().getBudget_variance());
        logger.info("\n**** Project Has Schedule Variance of : "
            + project.getProjectDetail().getSchedule_variance());
        logger.info("\n**** Project Has FTE Utilization Variance of : "
            + project.getProjectDetail().getFte_utilization_rate_variance());

        logger.info("\n**** Project Has a Flag of : "
            + ((project.getFlaggedAsset() == null) ? "N/A" : project
                .getFlaggedAsset().getFlagged_reason()));

        // Project Partners
        Set<ProjectPartner> projectPartnerSet = project.getProjectPartnerSet();
        for (ProjectPartner projectPartner : projectPartnerSet) {
          logger.info("\n**** Project Has Project Partner: "
              + projectPartner.getProject_partner_name());

          logger.info("\n****** Project Partner Has a Flag of : "
              + ((projectPartner.getFlaggedAsset() == null) ? "N/A"
                  : projectPartner.getFlaggedAsset().getFlagged_reason()));
        }
        // Project Systems
        Set<ProjectSystem> projectSystemSet = project.getProjectSystemSet();
        for (ProjectSystem projectSystem : projectSystemSet) {
          logger.info("\n**** Project Has Project System: "
              + projectSystem.getSystem_name());

          logger.info("\n****** Project System Has a Flag of : "
              + ((projectSystem.getFlaggedAsset() == null) ? "N/A"
                  : projectSystem.getFlaggedAsset().getFlagged_reason()));

          // System Vulnerabilities
          Set<SystemVulnerability> systemVulnerabilitySet =
              projectSystem.getSystemVulnerabilitySet();
          for (SystemVulnerability systemVulnerability : systemVulnerabilitySet) {
            logger.info("\n****** Project System Has Vulnerability: "
                + systemVulnerability.getVulnerability_name());

            logger
                .info("\n****** Project System Vulnerability Has a Flag of : "
                    + ((systemVulnerability.getFlaggedAsset() == null) ? "N/A"
                        : systemVulnerability.getFlaggedAsset()
                            .getFlagged_reason()));
          }
          // System Products
          Set<SystemProduct> systemProdutSet =
              projectSystem.getSystemProductSet();
          for (SystemProduct systemProduct : systemProdutSet) {
            logger.info("\n****** Project System Has Product: "
                + systemProduct.getProduct_name());

            logger.info("\n****** Project System Products Has a Flag of : "
                + ((systemProduct.getFlaggedAsset() == null) ? "N/A"
                    : systemProduct.getFlaggedAsset().getFlagged_reason()));
          }
        }// end project system
      }// end project

      // TODO implement and capture which risk assets have been flagged

    }// end user profile
    // Below are just for testing selects of all database tables.

    // List<Role> listRoles = myRolesDAO.findAll();
    // model.addObject("rolesList", listRoles);
    // List<RiskPreference> listRiskPreference = myRiskPreferenceDAO.findAll();
    // model.addObject("riskPreferenceList", listRiskPreference);
    // List<Project> listProjects = myProjectDAO.findAll();
    // model.addObject("projectList", listProjects);
    // List<ProjectSystem> listProjectSystem = myProjectSystemDAO.findAll();
    // List<SystemVulnerability> listSystemVulnerabilities =
    // mySystemVulnerabilitiesDAO.findAll();
    // List<FlaggedAsset> listFlaggedAssets = myFlaggedAssetsDAO.findAll();

    return listUserProfiles;
  }

  // @RequestMapping(value = "constructUserProfile", method = RequestMethod.GET)
  // public ModelAndView constructUserProfile() {
  // ModelAndView model = new ModelAndView("index");
  //
  // Map<String, AbstractDAO> daoMap = new HashMap<String, AbstractDAO>();
  // daoMap.put(DaoTypesEnum.myRolesDAO.getEnumString(), myRolesDAO);
  // daoMap.put(DaoTypesEnum.myUserProfileDAO.getEnumString(),
  // myUserProfileDAO);
  // EISProfileManager eisProfileManager = new EISProfileManager(daoMap);
  //
  // // Get all users and the build their complete profile
  // List<UserProfile> listUserProfiles = myUserProfileDAO.findAll();
  // for (UserProfile userProfile : listUserProfiles) {
  // eisProfileManager.construct(userProfile);
  // }
  //
  // // INSERT
  // // myUserProfileDAO.save(transientInstance);
  //
  //
  // return model;
  // // // model.addAttribute("message", " The databse test " +
  // // // myRolesDAO.findAll());
  // //
  // // // loads configuration and mappings
  // // Configuration configuration = new Configuration().configure();
  // // ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
  // // registry.applySettings(configuration.getProperties());
  // // ServiceRegistry serviceRegistry =
  // registry.getBootstrapServiceRegistry();
  // //
  // // // builds a session factory from the service registry
  // // SessionFactory sessionFactory =
  // // configuration.buildSessionFactory(serviceRegistry);
  // //
  // // // obtains the session
  // // Session session = sessionFactory.openSession();
  // // session.beginTransaction();
  // //
  // // Role myRole =
  // // new Role(RolesEnum.Admin, StatusEnum.Active);
  // //
  // // UserProfile userProfile = new UserProfile("myeamil@xyz.com", myRole);
  // //
  // // Set<UserProfile> userProfileSet = new HashSet<UserProfile>();
  // // userProfileSet.add(userProfile);
  // //
  // // myRole.setUserProfileSet(userProfileSet);
  // //
  // // session.save(myRole);
  // //
  // // session.getTransaction().commit();
  // // session.close();
  //
  // // List<Role> listRoles = myRolesDAO.findAll();
  // // model.put("myRolesList", listRoles);
  // // model.addAttribute("message", listRoles);
  //
  // // List<UserProfile> listUserProfiles = myUserProfileDAO.findAll();
  // // model.put("userProfileList", listUserProfiles);
  // // model.addAttribute("message", listUserProfiles);
  // // model.addAttribute("message", "Check the DB commit");
  // }

  // @RequestMapping(method = RequestMethod.GET)
  // public String index(ModelMap model) {
  //
  // //model.addAttribute("message",
  // "Welcome to the Executive Information System (EIS) enterprise application!");
  // model.addAttribute("message", overviewStr);
  //
  // //Spring uses the InternalResourceViewResolver defined in
  // // mvc-dispatcher-servlet.xml to prepend and append onto
  // // the view name which returns index.jsp for this scenario
  // return "index";
  //
  // }
  
  @RequestMapping(value="welcome/{name}", method = RequestMethod.GET)
  public String welcomeName(@PathVariable String name, ModelMap model) {
 
    model.addAttribute("message", "Welcome to the Executive Information System: " + name);
    return "index";
 
  }
  
  @RequestMapping(value="overview", method = RequestMethod.GET)
  public String welcome(ModelMap model) {
        
    model.addAttribute("message", overviewStr);
 
    return "index";
 
  }
  
  private String overviewStr = "<p>Graham Technologies aims to provide an on-line Enterprise Governance, Risk, and Compliance (eGRC) - "
      + "Executive Information System (EIS) where risk perception is made reality to enhance enterprise-wide visibility, "
      + "collaboration & decision-making to ensure compliance. The application will assess project risks and visualize "
      + "prioritized dashboard results for mission products and project association vulnerabilities; which will roll-up "
      + "for multi-layered enterprise analysis. Project analysis will be centered on Business Continuity, Disaster Prevention,"
      + " Incident Response Team Evaluations, and IT System Risk Analysis (Hardware & Software). EIS will provide a condensed,"
      + " enriched, graphical, prioritized, notification and easily searchable view of the enterprise and its associated assets."
      + "This full lifecycle Agile software development and project management effort will ensure the efficiency, effectiveness,"
      + " and data integrity of full mission situational awareness for GRAHAM and GRAHAM clients on both desktop and mobile"
      + " platforms. The solution aims to include:"
      + "End-to-End capabilities:  "
      + "</p>"
      + "<ul>"
      + "<li>Project and Product Risk Assessment Visualization</li>"
      + "<li>Geospatial representation of mission assets</li>"
      + "<li>Key performance indicators (Metrics, Trending)</li>"
      + "<li>Compliance, Access and Data Integrity</li>"
      + "</ul>"
      
      + "<p>"
      + "Foundational frameworks & processes:" 
      + "</p>"
      + "<ul>"
      + "<li>Agile Software Development (SCRUM)</li>"
      + "<li>Mobile Responsive</li>"
      + "<li>Enterprise Application Development</li>"
      + "<li>Application Security</li>"
      + "<li>Open Source Software Development</li>"
      + "</ul>"
      
      + "<p>"
          + "At a High-level: A UserProfile has a Role, Risk Preferences, and one or more Project associations. Projects "
          + "have Systems, Partners, and Project Risk (Schedule/Budget/FTE Utilization) as risk assets. Systems have Products "
          + "and Vulnerabilities. These risk assets may be Flagged for escalation review: Projects, Partners, System Products "
          + "and System Vulnerabilities."
          + "</p>"

      + "The EIS Workflow will roll-up risk analysis for products and project vulnerabilities (Project Risks) either entered into"
      + " EIS manually or ingested via an EIS utility or web service. "

      + "Workflow Summary - Executives monitor & initiate events for EIS at-risk projects, in addition to managing Manager"
      + " profiles-to-project associations; Managers monitor & enter Project Risk data, in addition to flagging at-risk projects"
      + " for Executives; and Administrators have the capability to perform Executive and Manager functions as needed, as well as,"
      + " manage all EIS user profiles. "
      
      + "Workflow Details - 1) Users with the Manager role will be assigned projects by an Executive and are responsible for managing"
      + " EIS projects and their associated Project Risks. A Manager has the ability to manage projects for which they are associated."
      + " Managers may set custom preferences for Project Risk vulnerability tolerance. A Manager may use their customized dashboard"
      + " or EIS notifications to monitor and/or address risk areas of interest. A Manager may use EIS to flag Project Risks to indicate"
      + " that interested Managers and Executives are advised to monitor or perform mitigation tasks for associated Project Risks."
      + "  2) Executive roles will be assigned by an Administrator, will be associated with all projects by default, and can perform"
      + " Manager functions. Executives are responsible for managing EIS Manager profiles, as well as, monitoring & tracking EIS at-risk"
      + " projects. An Executive may set custom preferences for Project Risk vulnerability tolerance. An Executive may use their"
      + " customized dashboard or EIS notifications to monitor and/or address project risk areas of interest. An Executive may use EIS"
      + " to flag Projects to indicate that Managers must monitor or perform mitigation tasks.  3) Administrators have the ability to"
      + " execute Executive and Manager role capabilities, manage EIS profiles, and start/stop EIS services such as user system access,"
      + " EIS outgoing notifications, etc."
      
      + "</br>Key Building blocks of the EIS system include:"
      + "</p>"
      + "<ol>"
      + "<li>Data Integration - EIS will interact with the National Institute of Standards and Technology (NIST) - National"
      + " Vulnerabilities Database (NVD) system via an RSS Feed to consume product vulnerability and risk rating information.</li>"
      + "<li>Technologies and User Personas are referenced in Technical Solution Appendix A and B, respectively.</li>"
      + "</ol>";
 
}
