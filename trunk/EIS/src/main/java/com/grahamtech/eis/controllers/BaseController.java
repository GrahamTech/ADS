package com.grahamtech.eis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.beans.factory.annotation.Autowired;










import com.grahamtech.eis.daos.MyFlaggedAssetsDAO;
import com.grahamtech.eis.daos.MyNVDEntryMessageDAO;
import com.grahamtech.eis.daos.MyProjectDAO;
import com.grahamtech.eis.daos.MyProjectDetailDAO;
import com.grahamtech.eis.daos.MyProjectPartnerDAO;
import com.grahamtech.eis.daos.MyProjectSystemDAO;
import com.grahamtech.eis.daos.MyRiskPreferenceDAO;
import com.grahamtech.eis.daos.MyRolesDAO;
import com.grahamtech.eis.daos.MySystemProductDAO;
import com.grahamtech.eis.daos.MySystemVulnerabilitiesDAO;
import com.grahamtech.eis.daos.MyUserProfileDAO;
import com.grahamtech.eis.pojos.FlaggedAsset;
import com.grahamtech.eis.pojos.NVDEntryMessage;
import com.grahamtech.eis.pojos.Project;
import com.grahamtech.eis.pojos.ProjectDetail;
import com.grahamtech.eis.pojos.ProjectPartner;
import com.grahamtech.eis.pojos.ProjectSystem;
import com.grahamtech.eis.pojos.RiskPreference;
import com.grahamtech.eis.pojos.Role;
import com.grahamtech.eis.pojos.SystemProduct;
import com.grahamtech.eis.pojos.SystemVulnerability;
//import com.grahamtech.eis.pojos.Project;
//import com.grahamtech.eis.daos.MyProjectDAO;
//import com.grahamtech.eis.daos.MyRiskPreferenceDAO;
//import com.grahamtech.eis.daos.MyUserProfileDAO;
//import com.grahamtech.eis.pojos.NVDEntryMessage;
//import com.grahamtech.eis.pojos.ProjectPartner;
//import com.grahamtech.eis.pojos.ProjectSystem;
//import com.grahamtech.eis.pojos.RiskPreference;
//import com.grahamtech.eis.pojos.Role;
//import com.grahamtech.eis.pojos.SystemProduct;
//import com.grahamtech.eis.pojos.SystemVulnerability;
import com.grahamtech.eis.pojos.UserProfile;
import com.grahamtech.eis.utilities.StringUtil;
//import com.grahamtech.eis.utilities.StringUtil;
import com.grahamtech.eis.utilities.enums.RolesEnum;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.HashSet;
//import java.security.GeneralSecurityException;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
//import java.util.Map;
//import java.util.Set;


import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
// @RequestMapping("/")
public class BaseController {
  private static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

  // private CRUDUtil crudUtil = new CRUDUtil();

  @Autowired
  private MyUserProfileDAO myUserProfileDAO;
  @Autowired
  private MyProjectDAO myProjectDAO;
  @Autowired
  private MySystemProductDAO mySystemProductDAO;
  @Autowired
  private MySystemVulnerabilitiesDAO mySystemVulnerabilitiesDAO;
  @Autowired
  private MyRolesDAO myRolesDAO;
  @Autowired
  private MyRiskPreferenceDAO myRiskPreferenceDAO;
  @Autowired
  private MyProjectSystemDAO myProjectSystemDAO;
  @Autowired
  private MyProjectPartnerDAO myProjectPartnerDAO;
  @Autowired
  private MyProjectDetailDAO myProjectDetailDAO;
  @Autowired
  private MyNVDEntryMessageDAO myNVDEntryMessageDAO;

  @Autowired
  private MyFlaggedAssetsDAO myFlaggedAssetsDAO;

  @RequestMapping(value = RestURIConstants.GET_FLAGGED_ASSETS, method = RequestMethod.GET)
  public @ResponseBody
  List<FlaggedAsset> getFlaggedAssets() {
    List<FlaggedAsset> list = myFlaggedAssetsDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_FLAGGED_ASSET, method = RequestMethod.GET)
  public @ResponseBody
  FlaggedAsset getFlaggedAssetById(@PathVariable String id) {
    return myFlaggedAssetsDAO.findById(new Long(id).longValue());
  }

  /*
   * <sf:form method="POST" modelAttribute="entity"
   * enctype="multipart/form-data" action="/update/flaggedAsset/${entity.id}">
   * <fieldset> <table cellspacing="0"> <tr> <th><label
   * for="entity_id">Id:</label></th> <td><sf:input path="id" size="15"
   * id="entity_id" /></td> </tr> <tr> <th><label
   * for="entity_flagged_reason">Flagged Reason:</label></th> <td><sf:input
   * path="flagged_reason" size="15" id="entity_flagged_reason" /></td> </tr>
   * </table> <input type="submit" value="Update" /> </fieldset> </sf:form>
   */
  @RequestMapping(value = RestURIConstants.UPDATE_FLAGGED_ASSET, method = RequestMethod.POST)
  public String updateMember(@PathVariable long id,
      @ModelAttribute("entity") FlaggedAsset entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myFlaggedAssetsDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: FlaggedAsset");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: FlaggedAsset");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_FLAGGED_ASSET, method = RequestMethod.POST)
  public String deleteFlaggedAssetById(@PathVariable long id,
      @ModelAttribute("entity") FlaggedAsset entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setFlagged_id(id);
      myFlaggedAssetsDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: FlaggedAsset");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: FlaggedAsset");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_FLAGGED_ASSET, method = RequestMethod.POST)
  public String createFlaggedAsset(
      @ModelAttribute("entity") FlaggedAsset entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myFlaggedAssetsDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: FlaggedAsset");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: FlaggedAsset");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: FlaggedAsset");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_NVD_ENTRY_MESSAGES, method = RequestMethod.GET)
  public @ResponseBody
  List<NVDEntryMessage> getNvdEntryMessages() {
    List<NVDEntryMessage> list = myNVDEntryMessageDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_NVD_ENTRY_MESSAGE, method = RequestMethod.GET)
  public @ResponseBody
  NVDEntryMessage getNVDEntryMessageById(@PathVariable String id) {
    return myNVDEntryMessageDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_NVD_ENTRY_MESSAGE, method = RequestMethod.POST)
  public String updateNVDEntryMessage(@PathVariable long id,
      @ModelAttribute("entity") NVDEntryMessage entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myNVDEntryMessageDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: NVDEntryMessage");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: NVDEntryMessage");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_NVD_ENTRY_MESSAGE, method = RequestMethod.PUT)
  public String deleteNVDEntryMessageById(@PathVariable long id,
      @ModelAttribute("entity") NVDEntryMessage entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setEntry_message_id(id);
      myNVDEntryMessageDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: NVDEntryMessage");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: NVDEntryMessage");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_NVD_ENTRY_MESSAGE, method = RequestMethod.POST)
  public String createNVDEntryMessage(
      @ModelAttribute("entity") NVDEntryMessage entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myNVDEntryMessageDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: NVDEntryMessage");
    } catch (RuntimeException e) {
      strBuffer
          .append(" RuntimeException updating the entity: NVDEntryMessage");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: NVDEntryMessage");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECTS, method = RequestMethod.GET)
  public @ResponseBody
  List<Project> getProject() {
    List<Project> list = myProjectDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT, method = RequestMethod.GET)
  public @ResponseBody
  Project getProjectById(@PathVariable String id) {
    return myProjectDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_PROJECT, method = RequestMethod.POST)
  public String updateProject(@PathVariable long id,
      @ModelAttribute("entity") Project entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: Project");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: Project");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_PROJECT, method = RequestMethod.PUT)
  public String deleteProjectById(@PathVariable long id,
      @ModelAttribute("entity") Project entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setRole_id(id);
      myProjectDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: Project");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: Project");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_PROJECT, method = RequestMethod.POST)
  public String createProject(@ModelAttribute("entity") Project entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: Project");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: Project");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: Project");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT_DETAILS, method = RequestMethod.GET)
  public @ResponseBody
  List<ProjectDetail> getProjectDetails() {
    List<ProjectDetail> list = myProjectDetailDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT_DETAIL, method = RequestMethod.GET)
  public @ResponseBody
  ProjectDetail getProjectDetailById(@PathVariable String id) {
    return myProjectDetailDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_PROJECT_DETAIL, method = RequestMethod.POST)
  public String updateProjectDetail(@PathVariable long id,
      @ModelAttribute("entity") ProjectDetail entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectDetailDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: ProjectDetail");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: ProjectDetail");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_PROJECT_DETAIL, method = RequestMethod.PUT)
  public String deleteProjectDetailById(@PathVariable long id,
      @ModelAttribute("entity") ProjectDetail entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setProject_details_id(id);
      myProjectDetailDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: ProjectDetail");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: ProjectDetail");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_PROJECT_DETAIL, method = RequestMethod.POST)
  public String createProjectDetail(
      @ModelAttribute("entity") ProjectDetail entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectDetailDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: ProjectDetail");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: ProjectDetail");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: ProjectDetail");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT_PARTNERS, method = RequestMethod.GET)
  public @ResponseBody
  List<ProjectPartner> getProjectPartners() {
    List<ProjectPartner> list = myProjectPartnerDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT_PARTNER, method = RequestMethod.GET)
  public @ResponseBody
  ProjectPartner getProjectPartnerById(@PathVariable String id) {
    return myProjectPartnerDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_PROJECT_PARTNER, method = RequestMethod.POST)
  public String updateProjectPartner(@PathVariable long id,
      @ModelAttribute("entity") ProjectPartner entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectPartnerDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: ProjectPartner");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: ProjectPartner");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_PROJECT_PARTNER, method = RequestMethod.PUT)
  public String deleteProjectPartnerById(@PathVariable long id,
      @ModelAttribute("entity") ProjectPartner entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setProject_partner_id(id);
      myProjectPartnerDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: ProjectPartner");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: ProjectPartner");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_PROJECT_PARTNER, method = RequestMethod.POST)
  public String createProjectPartner(
      @ModelAttribute("entity") ProjectPartner entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectPartnerDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: ProjectPartner");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: ProjectPartner");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: ProjectPartner");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT_SYSTEMS, method = RequestMethod.GET)
  public @ResponseBody
  List<ProjectSystem> getProjectSystems() {
    List<ProjectSystem> list = myProjectSystemDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_PROJECT_SYSTEM, method = RequestMethod.GET)
  public @ResponseBody
  ProjectSystem getProjectSystemById(@PathVariable String id) {
    return myProjectSystemDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_PROJECT_SYSTEM, method = RequestMethod.POST)
  public String updateProjectSystem(@PathVariable long id,
      @ModelAttribute("entity") ProjectSystem entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectSystemDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: ProjectSystem");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: ProjectSystem");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_PROJECT_SYSTEM, method = RequestMethod.PUT)
  public String deleteProjectSystemById(@PathVariable long id,
      @ModelAttribute("entity") ProjectSystem entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setSystem_id(id);
      myProjectSystemDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: ProjectSystem");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: ProjectSystem");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_PROJECT_SYSTEM, method = RequestMethod.POST)
  public String createProjectSystem(
      @ModelAttribute("entity") ProjectSystem entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myProjectSystemDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: ProjectSystem");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: ProjectSystem");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: ProjectSystem");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_RISK_PREFERENCES, method = RequestMethod.GET)
  public @ResponseBody
  List<RiskPreference> getRiskPreferences() {
    List<RiskPreference> list = myRiskPreferenceDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_RISK_PREFERENCE, method = RequestMethod.GET)
  public @ResponseBody
  RiskPreference getRiskPreferenceById(@PathVariable String id) {
    return myRiskPreferenceDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_RISK_PREFERENCE, method = RequestMethod.POST)
  public String updateRiskPreference(@PathVariable long id,
      @ModelAttribute("entity") RiskPreference entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myRiskPreferenceDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: RiskPreference");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: RiskPreference");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_RISK_PREFERENCE, method = RequestMethod.PUT)
  public String deleteRiskPreferenceById(@PathVariable long id,
      @ModelAttribute("entity") RiskPreference entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setRisk_preference_id(id);
      ;
      myRiskPreferenceDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: RiskPreference");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: RiskPreference");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_RISK_PREFERENCE, method = RequestMethod.POST)
  public String createRiskPreference(
      @ModelAttribute("entity") RiskPreference entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myRiskPreferenceDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: RiskPreference");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: RiskPreference");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: RiskPreference");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_ROLES, method = RequestMethod.GET)
  public @ResponseBody
  List<Role> getRoles() {
    List<Role> list = myRolesDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_ROLE, method = RequestMethod.GET)
  public @ResponseBody
  Role getRoleById(@PathVariable String id) {
    return myRolesDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_ROLE, method = RequestMethod.POST)
  public String updateRole(@PathVariable long id,
      @ModelAttribute("entity") Role entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myRolesDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: Role");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: Role");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_ROLE, method = RequestMethod.PUT)
  public String deleteRoleById(@PathVariable long id,
      @ModelAttribute("entity") Role entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setRole_id(id);
      myRolesDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: Role");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: Role");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_ROLE, method = RequestMethod.POST)
  public String createRole(@ModelAttribute("entity") Role entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myRolesDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: Role");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: Role");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: Role");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_SYSTEM_VULNERABILITIES, method = RequestMethod.GET)
  public @ResponseBody
  List<SystemVulnerability> getSystemVulnerabilities() {
    List<SystemVulnerability> list = mySystemVulnerabilitiesDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_SYSTEM_VULNERABILITY, method = RequestMethod.GET)
  public @ResponseBody
  SystemVulnerability getSystemVulnerabilityById(@PathVariable String id) {
    return mySystemVulnerabilitiesDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_SYSTEM_VULNERABILITY, method = RequestMethod.POST)
  public String updateSystemVulnerability(@PathVariable long id,
      @ModelAttribute("entity") SystemVulnerability entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      mySystemVulnerabilitiesDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: SystemVulnerability");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: SystemVulnerability");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_SYSTEM_VULNERABILITY, method = RequestMethod.PUT)
  public String deleteSystemVulnerabilityById(@PathVariable long id,
      @ModelAttribute("entity") SystemVulnerability entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setVulnerability_id(id);
      mySystemVulnerabilitiesDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: SystemVulnerability");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: SystemVulnerability");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_SYSTEM_VULNERABILITY, method = RequestMethod.POST)
  public String createSystemVulnerability(
      @ModelAttribute("entity") SystemVulnerability entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      mySystemVulnerabilitiesDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: SystemVulnerability");
    } catch (RuntimeException e) {
      strBuffer
          .append(" RuntimeException updating the entity: SystemVulnerability");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: SystemVulnerability");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_SYSTEM_PRODUCTS, method = RequestMethod.GET)
  public @ResponseBody
  List<SystemProduct> getSystemProducts() {
    List<SystemProduct> list = mySystemProductDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_SYSTEM_PRODUCT, method = RequestMethod.GET)
  public @ResponseBody
  SystemProduct getSystemProductById(@PathVariable String id) {
    return mySystemProductDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_SYSTEM_PRODUCT, method = RequestMethod.POST)
  public String updateSystemProduct(@PathVariable long id,
      @ModelAttribute("entity") SystemProduct entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      mySystemProductDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: SystemProduct");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: SystemProduct");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_SYSTEM_PRODUCT, method = RequestMethod.PUT)
  public String deleteSystemProductById(@PathVariable long id,
      @ModelAttribute("entity") SystemProduct entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setProduct_id(id);
      mySystemProductDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: SystemProduct");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: SystemProduct");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_SYSTEM_PRODUCT, method = RequestMethod.POST)
  public String createSystemProduct(
      @ModelAttribute("entity") SystemProduct entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      mySystemProductDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: SystemProduct");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: SystemProduct");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: SystemProduct");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.GET_USERS, method = RequestMethod.GET)
  public @ResponseBody
  List<UserProfile> getUsers() {
    List<UserProfile> list = myUserProfileDAO.findAll();
    return list;
  }

  @RequestMapping(value = RestURIConstants.GET_USER, method = RequestMethod.GET)
  public @ResponseBody
  UserProfile getUserById(@PathVariable String id) {
    return myUserProfileDAO.findById(new Long(id).longValue());
  }

  @RequestMapping(value = RestURIConstants.UPDATE_USER, method = RequestMethod.POST)
  public String updateUserProfile(@PathVariable long id,
      @ModelAttribute("entity") UserProfile entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myUserProfileDAO.merge(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: UserProfile");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Updated successfully: UserProfile");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.DELETE_USER, method = RequestMethod.PUT)
  public String deleteUserProfileById(@PathVariable long id,
      @ModelAttribute("entity") UserProfile entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      entity.setUser_profile_id(id);
      myUserProfileDAO.delete(entity);
    } catch (RuntimeException e) {
      strBuffer.append("Error updating the entity: UserProfile");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Deleted successfully: UserProfile");
    }
    return strBuffer.toString();
  }

  @RequestMapping(value = RestURIConstants.CREATE_USER, method = RequestMethod.POST)
  public String createUserProfile(@ModelAttribute("entity") UserProfile entity) {
    StringBuffer strBuffer = new StringBuffer();
    int bufferLength = strBuffer.length();
    try {
      myUserProfileDAO.save(entity);
    } catch (ConstraintViolationException ec) {
      strBuffer
          .append(" ConstraintViolationException updating the entity: UserProfile");
    } catch (RuntimeException e) {
      strBuffer.append(" RuntimeException updating the entity: UserProfile");
    }
    if (strBuffer.length() == bufferLength) {
      strBuffer.append("Created successfully: UserProfile");
    }
    return strBuffer.toString();
  }

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
    model.addObject("message", overviewStr);
    return model;
  }

  @RequestMapping(value = RestURIConstants.LOCALIZATION, method = RequestMethod.GET)
  public ModelAndView localization() {
    logger.info("*** START localization()");
    ModelAndView model = new ModelAndView("localization");

    logger.info("*** FINISH localization()");
    return model;
  }

  @RequestMapping(value = RestURIConstants.GET_PROFILES, method = RequestMethod.GET)
  public @ResponseBody
  List<UserProfile> getAllUserProfiles() {
    ModelAndView model = new ModelAndView("index");

    List<UserProfile> listUserProfiles = getUserProfiles();
    model.addObject("userProfileList", listUserProfiles);

    return listUserProfiles;
  }

  @RequestMapping(value = RestURIConstants.GET_PROFILE, method = RequestMethod.GET)
  public @ResponseBody
  UserProfile getUserProfileById(@PathVariable String id) {
    //
    return getUserProfile(id);
  }

  @RequestMapping(value = RestURIConstants.CREATE_USER_BASIC, method = RequestMethod.POST)
  public @ResponseBody
  void createUserProfile(@PathVariable String userEmail,
      @PathVariable String primaryRole) {

    createUserProfile(userEmail, RolesEnum.fromString(primaryRole));
  }

  @RequestMapping(value = RestURIConstants.UPDATE_USER_EMAIL, method = RequestMethod.POST)
  public @ResponseBody
  void updateUserProfile(@PathVariable String id, @PathVariable String userEmail) {

    updateUser(id, userEmail);
  }

  @RequestMapping(value = RestURIConstants.DELETE_USER, method = RequestMethod.PUT)
  public @ResponseBody
  void createUserProfile(@PathVariable String id) {

    deleteUserProfile(getUserProfile(id));

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

          // System NVD CVE Association
          Set<NVDEntryMessage> nvdEntryMessageSet =
              projectSystem.getSystemNVDEntryMessageSet();
          for (NVDEntryMessage nvdEntryMessage : nvdEntryMessageSet) {
            logger.info("\n****** Project System Has NVD Entry Message ID: "
                + nvdEntryMessage.getCve_id());

            logger
                .info("\n****** Project System NVD Entry Message Has a Flag of : "
                    + ((nvdEntryMessage.getFlaggedAsset() == null) ? "N/A"
                        : nvdEntryMessage.getFlaggedAsset().getFlagged_reason()));
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

  public void updateUser(String id, String userEmail) {

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
    UserProfile userProfile1 =
        new UserProfile(userEmail, primaryRole, projectSet,
            getDefaultRiskPreference());
    project.setUserProfileAttribute(userProfile1);

    String userPassword = "12345678";
    Map<String, String> passwordMap = generatePassword(userPassword);

    String encryptedSalt = passwordMap.get("encryptedSalt");
    String encodedPasswordAndSaltHash =
        passwordMap.get("encodedPasswordAndSaltHash");

    userProfile1.setPwd_salt(encryptedSalt);

    logger.info("\n** User Profile Pwd Salt encrypted: " + encryptedSalt);
    logger.info("\n** User Profile Password with Salt encoded: "
        + encodedPasswordAndSaltHash);

    // TODO get Pwd and Salt from DB for this User using email and remove the
    // variables from the method signature and calls
    String encodedPasswordAndSaltFromDB = encodedPasswordAndSaltHash;
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

  public Map<String, String> generatePassword(String userPassword) {
    Map<String, String> passwordMap = new HashMap<String, String>();

    char[] password = userPassword.toCharArray();
    byte[] salt = StringUtil.nextSalt();
    byte[] passwordAndSaltHash = null;
    try {
      passwordAndSaltHash = StringUtil.hashPassword(password, salt);
    } catch (GeneralSecurityException e) {
      logger.error("Could not hash the password and salt", e);
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
    String encodedPasswordAndSaltHash = StringUtil.encode(passwordAndSaltHash);
    // assertEquals(encodedSalt.length(), 24);
    // assertEquals(encodedSalt.substring(22, 24), "==");

    passwordMap.put("encryptedSalt", encryptedSalt);
    passwordMap.put("encodedPasswordAndSaltHash", encodedPasswordAndSaltHash);

    return passwordMap;
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

  // Default
  public RiskPreference getDefaultRiskPreference() {
    return myRiskPreferenceDAO.findById(new Long(1).longValue());
  }

  // Default
  public Project getDefaultProject() {
    return myProjectDAO.findById(new Long(1).longValue());
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
          + "At a high-level, a UserProfile has Roles, Risk Preferences, and one or more Project associations. Projects "
          + "have Systems, Partners, and Project Risk (Schedule/Budget/FTE Utilization) as risk assets. Systems have custom Products "
          + "and Vulnerabilities, as well as National Vulnerability Database (NVD) - Common Vulnerability & Exposure (CVE) associations. These risk assets may be Flagged for escalation review: Projects, Partners, System Products, "
          + "System Vulnerabilities, and NVD CVEs."
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
