package com.mannit.site.prebook.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mannit.site.prebook.configs.Configvalues;
import com.mannit.site.prebook.model.Prebookdetails;
import com.mannit.site.prebook.repository.Prebookrepo;

@Controller
public class Prebookcontroller {
	 @Autowired(required = true)
     private Configvalues values;
     @Autowired
	 private Prebookrepo repo;
	@GetMapping("/prebook")
	public String getsurveyform(Model themodel) {
		 Prebookdetails  pd = new Prebookdetails();
		 List<String>op =values.getOptions();
			themodel.addAttribute("options", op);
		themodel.addAttribute("details",pd);
		return "prebooksurvey";
	}
	
	@PostMapping("/getprebookdetails")
	public String appointmentsdetails(@ModelAttribute("details") Prebookdetails details){
       Prebookdetails  pd = new Prebookdetails();
       pd.setName(details.getName());
       pd.setPhone_number(details.getPhone_number());
       pd.setAddress(details.getAddress());
       
       System.out.println(details.isNewsletter());
       System.out.println(details.isProductupdate());
       pd.setProductupdate(details.isProductupdate());
       pd.setNewsletter(details.isNewsletter());
       pd.setProduct(details.getProduct());
      
       repo.save(pd);
       System.out.println(details.toString());
		return "Thanks";
	}
	
}
