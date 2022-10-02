package com.tours.tourism.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @RequestMapping(value="**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
            RequestMethod.PUT})
    public ResponseEntity<Object> defaultPath(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "fail");
        jsonObject.put("message", "Requested route not found");

        return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.NOT_FOUND);
    }
}
