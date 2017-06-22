package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.auth.AuthHelper;
import com.example.auth.TokenResponse;
import com.example.service.Event;
import com.example.service.OutlookService;
import com.example.service.OutlookServiceBuilder;
import com.example.service.PagedResult;

public class EventsController {
	

	public static void main(String[] args){
		
	}
	 @RequestMapping("/events")
	  public   String events(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
	    HttpSession session = request.getSession();
	    TokenResponse tokens = (TokenResponse)session.getAttribute("tokens");
	    if (tokens == null) {
	      // No tokens in session, user needs to sign in
	      redirectAttributes.addFlashAttribute("error", "Please sign in to continue.");
	      return "redirect:/index.html";
	    }

	    String tenantId = (String)session.getAttribute("userTenantId");

	    tokens = AuthHelper.ensureTokens(tokens, tenantId);

	    String email = (String)session.getAttribute("userEmail");

	    OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokens.getAccessToken(), email);

	    // Sort by start time in descending order
	    String sort = "start/dateTime DESC";
	    // Only return the properties we care about
	    String properties = "organizer,subject,start,end";
	    // Return at most 10 events
	    Integer maxResults = 10;

	    try {
	      PagedResult<Event> events = outlookService.getEvents(
	          sort, properties, maxResults)
	          .execute().body();
	      model.addAttribute("events", events.getValue());
	      System.out.println("event="+events.getValue());

	    } catch (IOException e) {
	      redirectAttributes.addFlashAttribute("error", e.getMessage());
	     // return "redirect:/index.html";
	    }
	 
	    return "events";
	 }
	 }
