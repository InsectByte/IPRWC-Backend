package com.insectbyte.iprwc.controllers;

import com.insectbyte.iprwc.daos.GeonameDAO;
import com.insectbyte.iprwc.models.Geoname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("${end.geoname}")
@CrossOrigin(origins="${origin}")
public class GeonameController {

    private final GeonameDAO GEONAME_DAO;

    @Autowired
    public GeonameController(GeonameDAO geonameDAO) {
        this.GEONAME_DAO = geonameDAO;
    }

    @GetMapping
    public ResponseEntity getAdresses (@RequestParam String postalcode) {
        try{
            return new ResponseEntity(this.GEONAME_DAO.getAdressesByCode(postalcode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
