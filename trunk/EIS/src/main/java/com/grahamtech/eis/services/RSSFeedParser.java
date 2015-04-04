package com.grahamtech.eis.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.events.Attribute;
//import javax.xml.namespace.QName;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grahamtech.eis.pojos.NVDEntryMessage;
import com.grahamtech.eis.pojos.NVDEntryVulnerableSoftware;
import com.grahamtech.eis.utilities.ConstantsUtil;
import com.grahamtech.eis.utilities.StringUtil;

public class RSSFeedParser {
  private static final Logger logger = LoggerFactory
      .getLogger(RSSFeedParser.class);
  static final String ENTRY = "entry";
  static final String ID = "id";
  static final String CVE_ID = "cve-id";
  static final String VULNERABILITY_SOFTWARE_LIST = "product";
  static final String PUBLISHED_DATETIME = "published-datetime";
  static final String LAST_MODIFIED_DATETIME = "last-modified-datetime";
  static final String SCORE = "score";
  static final String ACCESS_VECTOR = "access-vector";
  static final String ACCESS_COMPLEXITY = "access-complexity";
  static final String AUTHENTICATION = "authentication";
  static final String CONFIDENTIALITY_IMPACT = "confidentiality-impact";
  static final String INTEGRITY_IMPACT = "integrity-impact";
  static final String AVAILABILITY_IMPACT = "availability-impact";
  static final String SOURCE = "source";
  static final String GENERATED_ON_DATETIME = "generated-on-datetime";
  static final String CWE_ID = "cwe_id";
  static final String SUMMARY = "summary";

  final URL url;

  public RSSFeedParser(String feedUrl) {
    try {
      this.url = new URL(feedUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<NVDEntryMessage> readFeed() {
    // FeedEntry feedEntry = null;
    List<NVDEntryMessage> feedEntries = new ArrayList<NVDEntryMessage>();
    try {
      // boolean isFeedHeader = true;

      String cve_id = "";
      Set<NVDEntryVulnerableSoftware> vulnerability_software_list =
          new HashSet<NVDEntryVulnerableSoftware>(); // product
      String published_datetime = "";
      String last_modified_datetime = "";
      String score = "";
      String access_vector = "";
      String access_complexity = "";
      String authentication = "";
      String confidentiality_impact = "";
      String integrity_impact = "";
      String availability_impact = "";
      String source = "";
      String generated_on_datetime = "";
      String summary = "";

      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = read();
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

      // read the XML document
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          String localPart = startElement.getName().getLocalPart();
          switch (localPart) {
		    case ENTRY:
            vulnerability_software_list.clear(); // clear the set for the next
            // Entry's products
            // if (isFeedHeader) {
            // isFeedHeader = false;

            //
            // // Get 'id' attribute for the entire rss feed using the url and
            // last modified date
            // Date lastModifiedDate = null;
            // try {
            // lastModifiedDate = StringUtil.getHttpHeadDate(url);
            // } catch (IOException e) {
            // e.printStackTrace();
            // }
            // feedEntry = new FeedEntry(url.toString(), lastModifiedDate);
            //
            // // OR Get the 'id' attribute from Entry element
            // Attribute idAttr = startElement.getAttributeByName(new
            // QName("id"));
            // if(idAttr != null){
            // String idStr = idAttr.getValue();
            // feedEntry = new FeedEntry(idStr);
            // } else {
            // logger
            // .debug("There was no attribute name id within the entry elements.");
            // }
            // }
            event = eventReader.nextEvent();
            break;

          case CVE_ID:
            cve_id = getCharacterData(event, eventReader);
            break;
          case VULNERABILITY_SOFTWARE_LIST:
            vulnerability_software_list.add(new NVDEntryVulnerableSoftware(
                getCharacterData(event, eventReader)));
            break;
          case PUBLISHED_DATETIME:
            published_datetime = getCharacterData(event, eventReader);
            break;
          case LAST_MODIFIED_DATETIME:
            last_modified_datetime = getCharacterData(event, eventReader);
            break;
          case SCORE:
            score = getCharacterData(event, eventReader);
            break;
          case ACCESS_VECTOR:
            access_vector = getCharacterData(event, eventReader);
            break;
          case ACCESS_COMPLEXITY:
            access_complexity = getCharacterData(event, eventReader);
            break;
          case AUTHENTICATION:
            authentication = getCharacterData(event, eventReader);
            break;
          case CONFIDENTIALITY_IMPACT:
            confidentiality_impact = getCharacterData(event, eventReader);
            break;
          case INTEGRITY_IMPACT:
            integrity_impact = getCharacterData(event, eventReader);
            break;
          case AVAILABILITY_IMPACT:
            availability_impact = getCharacterData(event, eventReader);
            break;
          case SOURCE:
            source = getCharacterData(event, eventReader);
            break;
          case GENERATED_ON_DATETIME:
            generated_on_datetime = getCharacterData(event, eventReader);
            break;
          case SUMMARY:
            summary = getCharacterData(event, eventReader);
            break;
          }
        } else if (event.isEndElement()) {
		    if (event.asEndElement().getName().getLocalPart() == (ENTRY)) {
            NVDEntryMessage message = new NVDEntryMessage();

            message.setCve_id(cve_id);

            message.setVulnerability_software_list(vulnerability_software_list);

            try {
              message.setPublished_datetime(StringUtil.stringToFormattedDate(
                  published_datetime, ConstantsUtil.DATE_FORMAT));
            } catch (ParseException e) {
              logger.warn("Cannot parse published_datetime for " + cve_id);
              // e.printStackTrace();
            }

            try {
              message.setLast_modified_datetime(StringUtil
                  .stringToFormattedDate(last_modified_datetime,
                      ConstantsUtil.DATE_FORMAT));
            } catch (ParseException e) {
              logger.warn("Cannot parse last_modified_datetime for " + cve_id);
              // e.printStackTrace();
            }

            message.setScore(StringUtil.stringToBigDecimal(score));
            message.setAccess_vector(access_vector);
            message.setAccess_complexity(access_complexity);
            message.setAuthentication(authentication);
            message.setConfidentiality_impact(confidentiality_impact);
            message.setIntegrity_impact(integrity_impact);
            message.setAvailability_impact(availability_impact);
            message.setSource(source);

            try {
              message.setGenerated_on_datetime(StringUtil
                  .stringToFormattedDate(generated_on_datetime,
                      ConstantsUtil.DATE_FORMAT));
            } catch (ParseException e) {
              logger.warn("Cannot parse generated_on_datetime for " + cve_id);
              // e.printStackTrace();
            }

            message.setSummary(summary);

            feedEntries.add(message);
            // feedEntry.getMessages().add(message);

            event = eventReader.nextEvent();
            continue;
          } // end if end element for Entry
        } // end else if end element
      } // end while
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return feedEntries;
    // return feedEntry;
  }

  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
      throws XMLStreamException {
    String result = "";
    event = eventReader.nextEvent();
    if (event instanceof Characters) {
      result = event.asCharacters().getData();
    }
    return result;
  }

  private InputStream read() {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
