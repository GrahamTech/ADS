package com.grahamtech.eis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.grahamtech.eis.pojos.Project;
import com.grahamtech.eis.daos.MyProjectDAO;
import com.grahamtech.eis.daos.MyRiskPreferenceDAO;
import com.grahamtech.eis.daos.MyUserProfileDAO;
import com.grahamtech.eis.pojos.ProjectPartner;
import com.grahamtech.eis.pojos.ProjectSystem;
import com.grahamtech.eis.pojos.RiskPreference;
import com.grahamtech.eis.pojos.Role;
import com.grahamtech.eis.pojos.RolesEnum;
import com.grahamtech.eis.pojos.SystemProduct;
import com.grahamtech.eis.pojos.SystemVulnerability;
import com.grahamtech.eis.pojos.UserProfile;
import com.grahamtech.eis.utilities.StringUtil;

import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
// @RequestMapping("/")
public class BaseController {
  private static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

  @Autowired
  private MyUserProfileDAO myUserProfileDAO;
  @Autowired
  private MyRiskPreferenceDAO myRiskPreferenceDAO;
  @Autowired
  private MyProjectDAO myProjectDAO;

  // @Autowired
  // private MyProjectDetailDAO myProjectDetailDAO;
  // @Autowired
  // private MyProjectSystemDAO myProjectSystemDAO;
  // @Autowired
  // private MySystemVulnerabilitiesDAO mySystemVulnerabilitiesDAO;
  // @Autowired
  // private MyFlaggedAssetsDAO myFlaggedAssetsDAO;
  // @Autowired
  // private MyRolesDAO myRolesDAO;

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
    model.addObject("message", overviewStr);

    return model;
  }

  @RequestMapping(value = RestURIConstants.PROFILE_GET_ALL_USERS, method = RequestMethod.GET)
  public @ResponseBody
  List<UserProfile> getAllUserProfiles() {
    ModelAndView model = new ModelAndView("index");

    List<UserProfile> listUserProfiles = getUserProfiles();
    model.addObject("userProfileList", listUserProfiles);

    return listUserProfiles;
  }

  @RequestMapping(value = RestURIConstants.PROFILE_GET_USER, method = RequestMethod.GET)
  public @ResponseBody
  UserProfile getUserProfileById(@PathVariable String id) {
    //
    return getUserProfile(id);
  }

  @RequestMapping(value = RestURIConstants.PROFILE_CREATE_USER, method = RequestMethod.POST)
  public @ResponseBody
  void createUserProfile(@PathVariable String userEmail,
      @PathVariable String primaryRole) {

    createUserProfile(userEmail, RolesEnum.fromString(primaryRole));
  }

  @RequestMapping(value = RestURIConstants.PROFILE_UPDATE_USER_EMAIL, method = RequestMethod.POST)
  public @ResponseBody
  void updateUserProfile(@PathVariable String id, @PathVariable String userEmail) {

    updateUser(id, userEmail);
  }

  @RequestMapping(value = RestURIConstants.PROFILE_DELETE_USER, method = RequestMethod.PUT)
  public @ResponseBody
  void createUserProfile(@PathVariable String id) {

    deleteUserProfile(getUserProfile(id));

  }

  public List<UserProfile> getUserProfiles() {
    logger.info("############### START getUserProfiles.");

    List<UserProfile> listUserProfiles = myUserProfileDAO.findAll();
    for (UserProfile userProfile : listUserProfiles) {

      logger.info("User Profile: " + userProfile.getEmail());

      // Roles
      for (Role role : userProfile.getRoleSet()) {
        logger.info("\n** User Profile Has Role: "
            + role.getRole_name().getEnumString());
      }

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

    logger.info("############### FINISH getUserProfiles.");
    return listUserProfiles;
  }

  private void updateUser(String id, String userEmail) {

    UserProfile userProfile = getUserProfile(id);
    userProfile.setEmail(userEmail);
    myUserProfileDAO.update(userProfile);
  }

  public UserProfile getUserProfile(String id) {
    logger.info("############### START getUserProfile.");

    UserProfile userProfile =
        myUserProfileDAO.findById(new Long(id).longValue());
    logger.info("############### FINISH getUserProfile.");

    return userProfile;
  }

  public void deleteUserProfile(UserProfile userProfile) {
    logger.info("############### START deleteUserProfile.");

    myUserProfileDAO.delete(userProfile);

    logger.info("############### FINISH deleteUserProfile.");
  }

  public void createUserProfile(String userEmail, RolesEnum primaryRole) {
    logger.info("############### START createUserProfile.");

    Set<Project> projectSet = new HashSet<Project>();
    Project project = getDefaultProject();
    projectSet.add(project);
    UserProfile userProfile1 = new UserProfile(userEmail, primaryRole, projectSet, getDefaultRiskPreference());
    project.setUserProfileAttribute(userProfile1);

    String userPassword = "12345678";
    char[] password = userPassword.toCharArray();
    byte[] salt = StringUtil.nextSalt();
    byte[] passwordAndSaltHash = null;
    try {
      passwordAndSaltHash = StringUtil.hashPassword(password, salt);
    } catch (GeneralSecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // assertTrue(Passwords.matches(password, passwordHash, salt));
    // byte[] otherSaltBytes = Arrays.copyOf(salt, salt.length);
    // otherSaltBytes[0] ^= otherSaltBytes[0];
    // assertFalse(Passwords.matches(password, passwordHash, otherSaltBytes));
    // assertFalse(Passwords.matches("wrong".toCharArray(), passwordHash,
    // salt));

    String encodedSalt = StringUtil.encode(salt);
    String encryptedSalt = null;
    try {
      encryptedSalt = StringUtil.encrypt(encodedSalt);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String encodedPasswordAndSalt = StringUtil.encode(passwordAndSaltHash);

    userProfile1.setPwd_salt(encryptedSalt);
    userProfile1.setPwd_hash(encodedPasswordAndSalt);
    // assertEquals(encodedSalt.length(), 24);
    // assertEquals(encodedSalt.substring(22, 24), "==");

    logger.info("\n** User Profile Pwd Salt encoded: " + encodedSalt);
    logger.info("\n** User Profile Pwd Salt encrypted: " + encryptedSalt);
    logger.info("\n** User Profile Password with Salt encoded: "
        + encodedPasswordAndSalt);

    // TODO get Pwd and Salt from DB for this User using email and remove the
    // variables from the method signature and calls
    String encodedPasswordAndSaltFromDB = encodedPasswordAndSalt;
    String encodedSaltFromDB = null;
    try {
      encodedSaltFromDB = StringUtil.decrypt(encryptedSalt);
    } catch (Exception e) {
      e.printStackTrace();
    }

    boolean isAuthenticated =
        authenticateUser(userEmail, userPassword, encodedPasswordAndSaltFromDB,
            encodedSaltFromDB);
    logger.info("\n*IS USER AUTHENTICATED?: "
        + ((isAuthenticated == false) ? "No" : "Yes"));

    // Set User
    Set<UserProfile> userProfileSet = new java.util.HashSet<UserProfile>();
    userProfileSet.add(userProfile1);

    // for (Role role : roleSet) {
    // Role myRole = new Role(role.getRole_name(), StatusEnum.active);
    // myRole.setUserProfileSet(userProfileSet);
    // }

    myUserProfileDAO.save(userProfile1);

    logger.info("############### FINISH createUserProfile.");
  }

  public boolean authenticateUser(String userEmail, String userPassword,
      String encodedPasswordFromDB, String encodedSaltFromDB) {
    logger.info("############### START authenticateUser.");

    // Authenticate User
    char[] newPassword = userPassword.toCharArray();
    byte[] passwordHashFromDB = StringUtil.decode(encodedPasswordFromDB);
    byte[] saltFromDB = StringUtil.decode(encodedSaltFromDB);
    boolean isAuthenticated = false;
    try {
      isAuthenticated =
          StringUtil.matches(newPassword, passwordHashFromDB, saltFromDB);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }

    logger.info("############### FINISH authenticateUser.");
    return isAuthenticated;
  }

  //Default
   private RiskPreference getDefaultRiskPreference() {
     return myRiskPreferenceDAO.findById(new Long(1).longValue());
   }
  
   // Default
   private Project getDefaultProject() {
     return myProjectDAO.findById(new Long(1).longValue());
   }
 
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
          + "At a high-level, a UserProfile has a Role, Risk Preferences, and one or more Project associations. Projects "
          + "have Systems, Partners, and Project Risk (Schedule/Budget/FTE Utilization) as risk assets. Systems have Products "
          + "and Vulnerabilities. These risk assets may be Flagged for escalation review: Projects, Partners, System Products "
          + "and System Vulnerabilities."
          + "</p>"

          + "<p>The EIS Workflow will roll-up risk analysis for products and project vulnerabilities (Project Risks) either entered into"
          + " EIS manually or ingested via an EIS utility or web service.</p> "

          + "<p>Workflow Summary - Executives monitor & initiate events for EIS at-risk projects, in addition to managing Manager"
      + " profiles-to-project associations; Managers monitor & enter Project Risk data, in addition to flagging at-risk projects"
      + " for Executives; and Administrators have the capability to perform Executive and Manager functions as needed, as well as,"
          + " manage all EIS user profiles.</p>"
      
          + "<p>Workflow Details - 1) Users with the Manager role will be assigned projects by an Executive and are responsible for managing"
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
          + " EIS outgoing notifications, etc.</p>"
      
      + "</br>Key Building blocks of the EIS system include:"
      + "</p>"
      + "<ol>"
      + "<li>Data Integration - EIS will interact with the National Institute of Standards and Technology (NIST) - National"
          + " Vulnerabilities Database (NVD) Common Vulnerabilities & Exposures (CVE) system via an RSS Feed to consume CVE"
          + " risk rating information. CVE’s common identifiers enable data exchange between security products and provide a"
          + " baseline index point for evaluating coverage of tools and services.</li>"
      + "<li>Technologies and User Personas are referenced in Technical Solution Appendix A and B, respectively.</li>"
      + "</ol>";
 
}
