package com.grahamtech.eis.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.grahamtech.eis.pojos.AdverseReactions;
import com.grahamtech.eis.utilities.ClientErrorHandler;

public class RestClient implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = LoggerFactory
      .getLogger(RestClient.class);

  public RestClient() {
    // default constructor
  }

  // use API key if making 40 or more requests per minute or > 1000 requests per
  // day / per IP
    public ResponseEntity<String> getDrugEvents_display(String uri,
	    String apiKey) {
	RestTemplate restTemplate = new RestTemplate();
	restTemplate.setErrorHandler(new ClientErrorHandler());
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

	ResponseEntity<String> responseEntity = null;
	try {
	    responseEntity = restTemplate.exchange(uri,
		    HttpMethod.GET, entity, String.class);

	    System.out.println(responseEntity.getBody());
	} catch (Exception ex) {
	    logger.error("Exception thrown for the restTemplate exchange: "
		    + ex.toString());
	}

	return responseEntity;
  }

  public List<AdverseReactions> getDrugEvents(String SERVER_URI, String apiKey) {
	// String uri = "https://api.fda.gov/drug/event.json";
	// "https://api.fda.gov/drug/event.json?search={dateRange}&count=receivedate";
	// String dateRange = "receivedate:[20140101 TO 20150101]";
	// URI expanded = new UriTemplate(uri).expand(dateRange);
	// try {
	// uri = URLDecoder.decode(expanded.toString(), "UTF8");
	// } catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// final String uri =
	// "https://api.fda.gov/drug/event.json?search=patient.drug.openfda.pharm_class_epc:nonsteroidal%2Banti-inflammatory%2Bdrug&count=patient.reaction.reactionmeddrapt.exact";

    RestTemplate restTemplate = new RestTemplate();
    MultiValueMap<String, Object> headers =
        new LinkedMultiValueMap<String, Object>();
    headers.add("Accept", "application/json");
    headers.add("Content-Type", "application/json");

    final String urlPlusAPIKey = SERVER_URI + "&api+key={api-key}";

    String termId = "NAUSEA";
    String requestBody = "{\"term\":\"" + termId + "\"}";
    HttpEntity request = new HttpEntity(requestBody, headers);

    Object events = restTemplate.getForObject(SERVER_URI, Object.class);
    // AdverseReactions events =
    // restTemplate.getForObject(SERVER_URI, AdverseReactions.class);
    // List<AdverseReactions> events =
    // restTemplate.getForObject(SERVER_URI, List.class);
    // List<LinkedHashMap> events = restTemplate.getForObject(SERVER_URI,
    // List.class, apiKey);
    // List<LinkedHashMap> events = restTemplate.getForObject(SERVER_URI,
    // List.class, "results");

    List<AdverseReactions> eventsList = new LinkedList<AdverseReactions>();
    // System.out.println(events.size());
    // int counter = 0;
    // for (LinkedHashMap event : events) {
    // System.out.println("Term=" + event.get("term") + ",Count="
    // + event.get("count"));
    //
    // AdverseReactions adverseReaction = new AdverseReactions();
    // adverseReaction.setEvent_id(counter++);
    // adverseReaction.setTerm((String) event.get("term"));
    // adverseReaction.setReaction_count((long) event.get("count"));
    // eventsList.add(adverseReaction);
    // }

    return eventsList;
  }
}

// "results": [
// {
// "safetyreportid": "4322505-4",
// "sender": {
// "senderorganization": "FDA-Public Use"
// },
// "patient": {
// "reaction": [
// {
// "reactionmeddrapt": "ARTHRALGIA"
// },
// {
// "reactionmeddrapt": "OEDEMA PERIPHERAL"
// },
// {
// "reactionmeddrapt": "PURPURA"
// }
// ],
// "serious": "1",
// "companynumb": "HQWYE821915MAR04"
// }
// ]

//<200 OK,{
//  "meta": {
//    "disclaimer": "openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.",
//    "license": "http://open.fda.gov/license",
//    "last_updated": "2015-01-21",
//    "results": {
//      "skip": 0,
//      "limit": 1,
//      "total": 4587031
//    }
//  },
//  "results": [
//    {
//      "safetyreportid": "4322505-4",
//      "fulfillexpeditecriteria": "1",
//      "receiver": null,
//      "receivedateformat": "102",
//      "receiptdateformat": "102",
//      "primarysource": null,
//      "receivedate": "20040319",
//      "seriousnessother": "1",
//      "sender": {
//        "senderorganization": "FDA-Public Use"
//      },
//      "@epoch": 1417048845.306429,
//      "patient": {
//        "reaction": [
//          {
//            "reactionmeddrapt": "ARTHRALGIA"
//          },
//          {
//            "reactionmeddrapt": "OEDEMA PERIPHERAL"
//          },
//          {
//            "reactionmeddrapt": "PURPURA"
//          }
//        ],
//        "patientonsetage": "56",
//        "patientonsetageunit": "801",
//        "drug": [
//          {
//            "drugtreatmentdurationunit": "804",
//            "drugauthorizationnumb": "50621",
//            "drugtreatmentduration": "4",
//            "drugstartdateformat": "102",
//            "drugcharacterization": "1",
//            "drugindication": "PYELONEPHRITIS",
//            "medicinalproduct": "OROKEN (CEFIXIME, UNSPEC)",
//            "drugadministrationroute": "048",
//            "drugdosagetext": "ORAL",
//            "drugstartdate": "20031227",
//            "drugenddate": "20031230",
//            "drugenddateformat": "102"
//          },
//          {
//            "drugcharacterization": "2",
//            "medicinalproduct": "PYOSTACINE (PRISTINAMYCIN)"
//          },
//          {
//            "drugcharacterization": "2",
//            "medicinalproduct": "ROCEPHIN",
//            "openfda": {
//              "unii": [
//                "75J73V1629"
//              ],
//              "spl_id": [
//                "86e3103c-9d8b-4693-b5db-3fd62330c754"
//              ],
//              "product_ndc": [
//                "0004-1963",
//                "0004-1964"
//              ],
//              "substance_name": [
//                "CEFTRIAXONE SODIUM"
//              ],
//              "rxcui": [
//                "204871",
//                "105212"
//              ],
//              "spl_set_id": [
//                "9467f6c9-3e59-45c6-a1be-77200f2d4554"
//              ],
//              "product_type": [
//                "HUMAN PRESCRIPTION DRUG"
//              ],
//              "pharm_class_cs": [
//                "Cephalosporins [Chemical/Ingredient]"
//              ],
//              "manufacturer_name": [
//                "Genentech, Inc."
//              ],
//              "brand_name": [
//                "ROCEPHIN"
//              ],
//              "route": [
//                "INTRAMUSCULAR",
//                "INTRAVENOUS"
//              ],
//              "nui": [
//                "N0000011161",
//                "N0000175488"
//              ],
//              "package_ndc": [
//                "0004-1964-04",
//                "0004-1963-02",
//                "0004-1963-01",
//                "0004-1964-01"
//              ],
//              "pharm_class_epc": [
//                "Cephalosporin Antibacterial [EPC]"
//              ],
//              "generic_name": [
//                "CEFTRIAXONE SODIUM"
//              ],
//              "application_number": [
//                "ANDA063239"
//              ]
//            }
//          },
//          {
//            "drugcharacterization": "2",
//            "medicinalproduct": "OFLOXACIN",
//            "openfda": {
//              "unii": [
//                "A4P49JAZ9H"
//              ],
//              "spl_id": [
//                "b2235a46-f526-471e-8a9c-62f943ef6f7b",
//                "1419e0b7-cb1e-48fd-ab83-edfa70ddd693",
//                "4ebe9909-6509-ab0a-931c-e70bd210125f",
//                "4d4af447-be12-4ca8-89ce-4393ee8bcc9e",
//                "10d0fd2b-81f4-4836-b279-cf2b17813eb5",
//                "8ec38b43-795a-45ba-bbd3-8ebd6e1fcdf2",
//                "243c0b7c-8011-4a94-8b25-0329de936439",
//                "52572d94-d47b-428f-acc7-67147c5b0681",
//                "0095d1d6-d83e-4e22-aa61-3b4753f0753b",
//                "349789cb-da00-4eeb-88fa-a949263c1618",
//                "c3b5609c-3d6a-4c9a-8f81-611d0fa19180",
//                "3665d266-c322-45b4-9e56-caa6035b092c",
//                "568bf875-beda-4425-b175-decd86dcae12",
//                "7bc4742b-6f34-2c2d-95b9-d70f8358e55f",
//                "abfa4cd4-5c6d-62cd-3d3a-ff5a393b8301",
//                "1f88e5a7-78c8-4f48-81bd-5fd664cee9c8"
//              ],
//              "product_ndc": [
//                "55111-160",
//                "55111-161",
//                "55111-162",
//                "16571-130",
//                "60505-0560",
//                "65691-0105",
//                "65691-0104",
//                "65691-0106",
//                "11980-779",
//                "61314-015",
//                "50383-025",
//                "50383-024",
//                "61314-012",
//                "0093-7181",
//                "60505-0363",
//                "40042-049",
//                "0093-7180",
//                "59390-140",
//                "0093-7182",
//                "24208-434",
//                "17478-713",
//                "24208-410"
//              ],
//              "substance_name": [
//                "OFLOXACIN"
//              ],
//              "rxcui": [
//                "207202",
//                "312075",
//                "198048",
//                "198049",
//                "198050",
//                "242446"
//              ],
//              "spl_set_id": [
//                "8db221b1-32f3-f6ca-e404-71f56a860d08",
//                "1d19a6db-6da5-e7de-f929-2d18bdfa2cf5",
//                "95b9fc17-9c94-4762-910c-df0bb0b2aa85",
//                "7aab4449-3dda-4e2c-8e40-b3244a548bf5",
//                "81e8ece1-ab0b-4deb-8f84-79f21419b328",
//                "2ec6bd57-96df-47ac-b218-1469801868b7",
//                "7882f70e-d228-4c52-9390-0d927c51af1f",
//                "93a464f1-6b54-4f4e-8b71-fa781e2964e6",
//                "fe564e33-8f5d-4b57-87a9-7afae02eaf97",
//                "ad40954d-0b1d-47c5-8bd5-8a1efd9a7153",
//                "8b73d354-4631-40e8-b187-e8b0580bd6ea",
//                "749177b8-1c9e-4bc2-81fd-27d7e88cf9b8",
//                "b199f48d-647c-4041-a3ee-33737c515152",
//                "5117d567-2004-c5ed-1391-f8831864696f",
//                "c5484f1a-2321-453f-9469-dfcf709e2e2e",
//                "33ad989c-f551-4c57-a4e3-b607686206fa"
//              ],
//              "product_type": [
//                "HUMAN PRESCRIPTION DRUG"
//              ],
//              "pharm_class_cs": [
//                "Quinolones [Chemical/Ingredient]"
//              ],
//              "manufacturer_name": [
//                "Akorn, Inc.",
//                "Altaire Pharmaceuticals Inc.",
//                "Dr. Reddy's Laboratories Limited",
//                "Pack Pharmaceuticals, LLC",
//                "Hi-Tech Pharmacal Co., Inc.",
//                "Allergan, Inc.",
//                "Bausch & Lomb Incorporated",
//                "Apotex Corp.",
//                "Cadila Pharmaceuticals Limited",
//                "PharmaForce, Inc.",
//                "Falcon Pharmaceuticals, Ltd.",
//                "Teva Pharmaceuticals USA Inc"
//              ],
//              "brand_name": [
//                "OFLOXACIN OTIC",
//                "OFLOXACIN OPHTHALMIC",
//                "OFLOXACIN",
//                "OCUFLOX"
//              ],
//              "route": [
//                "AURICULAR (OTIC)",
//                "ORAL",
//                "OPHTHALMIC"
//              ],
//              "nui": [
//                "N0000175937",
//                "N0000007606"
//              ],
//              "package_ndc": [
//                "16571-130-50",
//                "55111-161-50",
//                "61314-012-05",
//                "17478-713-11",
//                "17478-713-10",
//                "55111-160-78",
//                "55111-161-78",
//                "55111-162-78",
//                "55111-160-30",
//                "0093-7181-01",
//                "65691-0106-2",
//                "65691-0106-1",
//                "50383-024-10",
//                "65691-0105-1",
//                "65691-0105-3",
//                "65691-0105-2",
//                "0093-7182-01",
//                "60505-0363-1",
//                "24208-434-05",
//                "0093-7180-01",
//                "61314-015-05",
//                "55111-161-01",
//                "65691-0104-1",
//                "65691-0104-2",
//                "65691-0104-3",
//                "55111-161-05",
//                "50383-025-10",
//                "40042-049-10",
//                "55111-162-01",
//                "50383-025-05",
//                "55111-162-05",
//                "61314-012-10",
//                "16571-130-11",
//                "55111-160-01",
//                "55111-160-05",
//                "50383-024-05",
//                "55111-162-30",
//                "60505-0363-2",
//                "24208-410-10",
//                "55111-162-50",
//                "24208-434-10",
//                "24208-410-05",
//                "60505-0560-1",
//                "60505-0560-0",
//                "61314-015-10",
//                "59390-140-05",
//                "40042-049-05",
//                "55111-161-30",
//                "55111-160-50",
//                "11980-779-05"
//              ],
//              "pharm_class_epc": [
//                "Quinolone Antimicrobial [EPC]"
//              ],
//              "generic_name": [
//                "OFLOXACIN",
//                "OFLOXAXIN"
//              ],
//              "application_number": [
//                "ANDA076407",
//                "ANDA076182",
//                "NDA019921",
//                "ANDA091656",
//                "ANDA076128",
//                "ANDA078222",
//                "ANDA090395",
//                "ANDA076527",
//                "ANDA076622",
//                "ANDA076513",
//                "ANDA076616",
//                "ANDA202692",
//                "ANDA078559",
//                "ANDA076615",
//                "ANDA076231",
//                "ANDA077098"
//              ]
//            }
//          }
//        ],
//        "patientsex": "1"
//      },
//      "receiptdate": "20040315",
//      "transmissiondate": "20041129",
//      "transmissiondateformat": "102",
//      "seriousnesshospitalization": "1",
//      "serious": "1",
//      "companynumb": "HQWYE821915MAR04"
//    }
//  ]
//},{accept-ranges=[bytes], access-control-allow-headers=[X-Requested-With], access-control-allow-origin=[*], age=[0], cache-control=[public, max-age=60], content-security-policy=[default-src 'none'], Content-Type=[application/json; charset=utf-8], Date=[Fri, 19 Jun 2015 20:31:22 GMT], etag=[W/"A0mpVcejLpbmk8psevly8A=="], location=[], Server=[nginx], vary=[Accept-Encoding, Accept-Encoding], via=[1.1 varnish-v4], x-cache=[MISS], x-content-type-options=[nosniff], x-frame-options=[deny], x-varnish=[40569702], x-xss-protection=[1; mode=block], Content-Length=[10406], Connection=[keep-alive]}>
