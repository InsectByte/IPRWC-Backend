package com.insectbyte.iprwc.daos;

import com.insectbyte.iprwc.models.Geoname;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeonameDAO {

    public ArrayList<Geoname> getAdressesByCode(String code) {
        final String uri = "http://api.geonames.org/postalCodeSearch?username=insectbyte&postalcode=" + code;
        RestTemplate restTemplate = new RestTemplate();
        JSONObject object = XML.toJSONObject(restTemplate.getForObject(uri, String.class));
        object = object.getJSONObject("geonames");
        JSONArray addresses;
        if ( object.getInt("totalResultsCount") == 1) {
            addresses = new JSONArray();
            addresses.put(object.getJSONObject("code"));
        } else {
            addresses = object.getJSONArray("code");
        }
        return jsonArrayToGeoname(addresses);
    }

    private ArrayList<Geoname> jsonArrayToGeoname(JSONArray arr) {
        ArrayList<Geoname> geonames = new ArrayList<>();
        for (Object object : arr) {
            JSONObject jobject = (JSONObject) object;
            geonames.add(new Geoname(
                    jobject.get("postalcode").toString(),
                    jobject.getString("name"),
                    jobject.getString("countryCode"),
                    jobject.getString("adminName1")
            ));
        }
        return geonames;
    }
}
