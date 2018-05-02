package org.fuber.cabservice.fubercabservice.controller;

import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.fuber.cabservice.fubercabservice.entity.CabBIll;
import org.fuber.cabservice.fubercabservice.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cab")
public class CabController {
	
	@Autowired
	CabService cabService;
	
	@RequestMapping("/get-cab")
	public Cab getCab(	
			@RequestParam(value="cab-type",defaultValue="standard")String cabType,
			@RequestParam(value="lat",required=true)int lat,
			@RequestParam(value="lng",required=true) int lng){
		return cabService.getCab(cabType,lat, lng);
	}
	
	@RequestMapping("/release-cab")
	@ResponseBody
	public CabBIll releaseCab(
			@RequestParam(value="booking-id",required=true) int bookingId,
			@RequestParam(value="cab-type",defaultValue="standard")String cabType,
			@RequestParam(value="lat",required=true)int lat,
			@RequestParam(value="lng",required=true) int lng) {
		return new CabBIll(bookingId, cabService.releaseCab(cabType, bookingId, lat, lng));
		
	}
}
