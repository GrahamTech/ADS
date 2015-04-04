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

/*
 * <?xml version='1.0' encoding='UTF-8'?> <nvd
 * xmlns="http://nvd.nist.gov/feeds/cve/1.2"
 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" pub_date="2015-03-31"
 * nvd_xml_version="1.2" xsi:schemaLocation=
 * "http://nvd.nist.gov/feeds/cve/1.2  http://nvd.nist.gov/schema/nvdcve_1.2.1.xsd"
 * > <entry type="CVE" name="CVE-2015-0001" seq="2015-0001"
 * published="2015-01-13" modified="2015-01-14" severity="Low"
 * CVSS_version="2.0" CVSS_score="1.9" CVSS_base_score="1.9"
 * CVSS_impact_subscore="2.9" CVSS_exploit_subscore="3.4"
 * CVSS_vector="(AV:L/AC:M/Au:N/C:P/I:N/A:N)"> <desc> <descript source="cve">The
 * Windows Error Reporting (WER) component in Microsoft Windows 8, Windows 8.1,
 * Windows Server 2012 Gold and R2, and Windows RT Gold and 8.1 allows local
 * users to bypass the Protected Process Light protection mechanism and read the
 * contents of arbitrary process-memory locations by leveraging administrative
 * privileges, aka
 * "Windows Error Reporting Security Feature Bypass Vulnerability."</descript>
 * </desc> <loss_types> <conf/> </loss_types> <range> <local/> </range> <refs>
 * <ref source="MS"
 * url="http://technet.microsoft.com/security/bulletin/MS15-006" adv="1"
 * patch="1">MS15-006</ref> </refs> <vuln_soft> <prod name="windows_8"
 * vendor="microsoft"> <vers num="-"/> </prod> <prod name="windows_8.1"
 * vendor="microsoft"> <vers num="-"/> </prod> <prod name="windows_rt"
 * vendor="microsoft"> <vers num="-" edition="gold"/> </prod> <prod
 * name="windows_rt_8.1" vendor="microsoft"> <vers num="-"/> </prod> <prod
 * name="windows_server_2012" vendor="microsoft"> <vers num="-" edition="gold"/>
 * <vers num="r2" edition=":~~~x64~~"/> </prod> </vuln_soft> </entry> <entry
 * type="CVE" name="CVE-2015-0002" seq="2015-0002" published="2015-01-13"
 * modified="2015-01-14" severity="High" CVSS_version="2.0" CVSS_score="7.2"
 * CVSS_base_score="7.2" CVSS_impact_subscore="10.0" CVSS_exploit_subscore="3.9"
 * CVSS_vector="(AV:L/AC:L/Au:N/C:C/I:C/A:C)"> <desc> <descript source="cve">The
 * AhcVerifyAdminContext function in ahcache.sys in the Application
 * Compatibility component in Microsoft Windows 7 SP1, Windows Server 2008 R2
 * SP1, Windows 8, Windows 8.1, Windows Server 2012 Gold and R2, and Windows RT
 * Gold and 8.1 does not verify that an impersonation token is associated with
 * an administrative account, which allows local users to gain privileges by
 * running AppCompatCache.exe with a crafted DLL file, aka MSRC ID 20544 or
 * "Microsoft Application Compatibility Infrastructure Elevation of Privilege Vulnerability."
 * </descript> </desc> <loss_types> <avail/> <conf/> <int/> </loss_types>
 * <range> <local/> </range> <refs> <ref source="MS"
 * url="http://technet.microsoft.com/security/bulletin/MS15-001" adv="1"
 * patch="1">MS15-001</ref> <ref source="MISC"
 * url="https://code.google.com/p/google-security-research/issues/detail?id=118"
 * >
 * https://code.google.com/p/google-security-research/issues/detail?id=118</ref>
 * <ref source="MISC" url=
 * "http://www.zdnet.com/article/google-discloses-unpatched-windows-vulnerability/"
 * >
 * http://www.zdnet.com/article/google-discloses-unpatched-windows-vulnerability
 * /</ref> <ref source="MISC"
 * url="http://twitter.com/sambowne/statuses/550384131683520512"
 * >http://twitter.com/sambowne/statuses/550384131683520512</ref> </refs>
 * <vuln_soft> <prod name="windows_7" vendor="microsoft"> <vers num="-"
 * edition="sp1"/> </prod> <prod name="windows_8" vendor="microsoft"> <vers
 * num="-"/> </prod> <prod name="windows_8.1" vendor="microsoft"> <vers
 * num="-"/> </prod> <prod name="windows_rt" vendor="microsoft"> <vers num="-"
 * edition="gold"/> </prod> <prod name="windows_rt_8.1" vendor="microsoft">
 * <vers num="-"/> </prod> <prod name="windows_server_2008" vendor="microsoft">
 * <vers num="r2" edition="sp1"/> </prod> <prod name="windows_server_2012"
 * vendor="microsoft"> <vers num="-" edition="gold"/> <vers num="r2"
 * edition=":~~~x64~~"/> </prod> </vuln_soft> </entry>
 */

