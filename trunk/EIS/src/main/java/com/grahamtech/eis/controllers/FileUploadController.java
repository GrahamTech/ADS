package com.grahamtech.eis.controllers;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.grahamtech.eis.utilities.AjaxUtils;
import com.grahamtech.eis.utilities.ConstantsUtil;

@Controller
@RequestMapping(RestURIConstants.FILE_UPLOAD)
public class FileUploadController {

  private static final Logger logger = LoggerFactory
      .getLogger(FileUploadController.class);

  private static final String UPLOAD_LOCATION = ConstantsUtil.UPLOAD_LOCATION;
  
  @ModelAttribute
  public void ajaxAttribute(WebRequest request, Model model) {
    logger.info("############### START ajaxAttribute.");
    model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
  }

  @RequestMapping(method = RequestMethod.GET)
  public void fileUploadForm() {
    logger.info("############### START fileuploadForm.");
  }

  @RequestMapping(method = RequestMethod.POST)
  public void processUpload(@RequestParam MultipartFile file, Model model)
      throws IOException {
    logger.info("############### START processUpload.");

    String orginalName = file.getOriginalFilename();
    String filePath = UPLOAD_LOCATION + orginalName;
    File destination = new File(filePath);
    String status = "success";
    try {
      file.transferTo(destination);
    } catch (IllegalStateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      status = "failure";
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      status = "iofailure";
    }

    model.addAttribute("message", "File '" + file.getOriginalFilename()
        + "' uploaded: " + status);
  }
}

