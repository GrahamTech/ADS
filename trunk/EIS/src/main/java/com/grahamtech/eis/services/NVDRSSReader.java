package com.grahamtech.eis.services;

import java.io.*;
import java.net.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NVDRSSReader {
  private final Logger logger = LoggerFactory.getLogger(NVDRSSReader.class);

  public NVDRSSReader() {
    // default constructor
  }

  public String readRSS(String urlAddress) {

    // urlAddress =
    // "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-2015.xml.zip";
    // urlAddress =
    // "http://static.nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-Modified.xml";
    // urlAddress =
    // "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-Modified.xml";
    // urlAddress = "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-Recent.xml";

    BufferedReader bufferedReader = null;
    String sourceCode = null;
    try {
      URL rssUrl = new URL(urlAddress);
      bufferedReader =
          new BufferedReader(new InputStreamReader(rssUrl.openStream()));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.contains("<entry")) {
          int firstPos = line.indexOf("<entry");
          String temp = line.substring(firstPos);
          temp = temp.replace("<entry", "");
          if (temp.indexOf("</entry>") != -1) {
            int lastPos = temp.indexOf("</entry>");
            temp = temp.substring(0, lastPos);
            sourceCode += temp + "\n";
          }
        }
      }
      bufferedReader.close();
    } catch (MalformedURLException e) {
      // e.printStackTrace();
      logger.debug("ERROR setting rssUrl: " + e.getMessage());
    } catch (IOException e) {
      logger.debug("ERROR setting bufferedReader: " + e.getMessage());
      try {
        bufferedReader.close();
      } catch (IOException e1) {
        logger.debug("ERROR closing bufferedReader: " + e1.getMessage());
      }
    }

    return sourceCode;
  }
}

