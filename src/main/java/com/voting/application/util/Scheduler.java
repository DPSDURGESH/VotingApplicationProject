////package com.voting.application.util;
////
////
////
////import java.text.SimpleDateFormat;
////import java.util.Date;
////import java.util.List;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.scheduling.annotation.Scheduled;
////import org.springframework.stereotype.Component;
////import org.springframework.stereotype.Service;
////
////import com.voting.application.model.UserRegistration;
////import com.voting.application.repository.UserRepository;
////
////import ch.qos.logback.classic.Logger;
////import lombok.extern.java.Log;
////import lombok.extern.slf4j.Slf4j;
////
////
////@Component
////@Service
////@Slf4j
////public class Scheduler {
////
////	@Autowired
////	private UserRepository userRepository;
////	
////	@Autowired
////	private CommunicationUtil communicationUtil;
////
////	@Scheduled(cron = "*/3 * * * * *") // Run every minute
////	public void scheduleTask() {
////		log.info("");
////		communicationUtil.sendEmail("psdp4653@gmail.com", "Vote Succecfull", "Thank You Vote for");
////		
////		
////	}
////
////	private void sendSMS(String mobile, String message) {
////		System.out.println("Sending SMS to " + mobile + ": " + message);
////	}
////}
//
//
//package com.voting.application.util;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import lombok.extern.slf4j.Slf4j;
//
//import com.voting.application.model.UserRegistration;
//import com.voting.application.repository.UserRepository;
//
//@Slf4j
//@Component
//public class Scheduler {
//
//    @Autowired
//    private UserRepository userRepository;
//    
//    @Autowired
//    private CommunicationUtil communicationUtil;
//
//    @Scheduled(cron = "*/3 * * * * *") // Runs every 3 seconds
//    public void scheduleTask() {
//        System.out.println("Vote a sending..."+LocalDateTime.now());
//        communicationUtil.sendEmail("psdp4653@gmail.com", "Vote Successful", "Thank You for Voting");
//    }
//
////    protected void sendSMS(String mobile, String message) {
////        log.info("Sending SMS to {} with message: {}", mobile, message);
////    }
//}
