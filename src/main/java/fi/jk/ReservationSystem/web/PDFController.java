package fi.jk.ReservationSystem.web;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

import fi.jk.ReservationSystem.domain.Event;
import fi.jk.ReservationSystem.domain.EventRepository;
import fi.jk.ReservationSystem.domain.Reservation;
import fi.jk.ReservationSystem.domain.ReservationRepository;
import fi.jk.ReservationSystem.domain.User;
import fi.jk.ReservationSystem.domain.UserRepository;

@Controller
public class PDFController {
	
	@Autowired
	private EventRepository eRep;	
	@Autowired
	private UserRepository uRep;	
	@Autowired
	private ReservationRepository rRep;
	
	@Autowired
	private ServletContext serCont;
	
	private final TemplateEngine tempEng;
	
	public PDFController(TemplateEngine tempEng) {
        this.tempEng = tempEng;
    }
	
	  @GetMapping(path = "/pdf/{id}/{idtwo}")
	    public ResponseEntity<?> getPDF(
			    		@PathVariable("id")Long eventId,
			    		@PathVariable("idtwo") Long reservationId,
			    		HttpServletRequest request,
			    		HttpServletResponse response,
			    		Principal princip) throws IOException {
	        
		  	Event event = eRep.findById(eventId).get();
		  	User user = uRep.findByUsername(princip.getName());
		  	Reservation res = rRep.findById(reservationId).get();

	        // HTML with Thymeleaf template Engine
	        WebContext context = new WebContext(request, response, serCont);
	        context.setVariable("singleevent", event);
	        context.setVariable("user", user);
	        context.setVariable("res", res);
	        String orderHtml = tempEng.process("pdfview", context);

	        // Setup Source and target I/O streams
	        ByteArrayOutputStream target = new ByteArrayOutputStream();
	        ConverterProperties converterProperties = new ConverterProperties();
	        converterProperties.setBaseUri("http://localhost:8080");
	        
	        // Convert method
	        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

	        // extract output as bytes
	        byte[] bytes = target.toByteArray();
	        
	        String fileName = event.getName();

	        // Send the response as downloadable PDF
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+".pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(bytes);
	    }

}
