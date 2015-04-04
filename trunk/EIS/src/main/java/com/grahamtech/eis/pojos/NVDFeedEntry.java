package com.grahamtech.eis.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grahamtech.eis.utilities.ConstantsUtil;
import com.grahamtech.eis.utilities.StringUtil;

/*
 * Stores an RSS feed cve entry header attributes and a list of entries. 
 * NOTE: CVE version 2.0 scheme only has an ID attribute for the top-level entry element,
 * so this class isn't as useful. Unlike, version 1.0 which stored lots of header attributes
 * such as name="CVE-2015-0001" modified="2015-01-14" severity="Low" CVSS_score="1.9"
 * CVSS_impact_subscore="2.9" CVSS_exploit_subscore="3.4"
 * CVSS_vector="(AV:L/AC:M/Au:N/C:P/I:N/A:N). These are now child elements and will be
 * stored in the EntryMessage class."
 */
public class NVDFeedEntry {
  
    final String id;
  final Date lastModifiedDate;
    final List<NVDEntryMessage> entries = new ArrayList<NVDEntryMessage>();

  public NVDFeedEntry(String id, Date lastModifiedDate) {
    this.id = id;
    this.lastModifiedDate = lastModifiedDate;
  }

  public List<NVDEntryMessage> getMessages() {
    return entries;
  }

  public String getId() {
    return id;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
    }

    @Override
    public String toString() {
    return "Feed [id= " + id + ", lastModifiedDate= "
        + StringUtil.dateToString(lastModifiedDate, ConstantsUtil.DATE_FORMAT)
        + "]";
    }

}
