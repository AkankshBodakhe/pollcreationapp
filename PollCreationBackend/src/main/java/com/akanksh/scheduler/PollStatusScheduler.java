//package com.akanksh.scheduler;
//
//import com.akanksh.dao.PollDao;
//import com.akanksh.entity.Poll;
//import com.akanksh.repository.PollRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//public class PollStatusScheduler {
//
//    @Autowired
//    private PollRepository pollRepository;
//    
//    @Autowired
//    private PollDao pollDao;
//
//    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
//    public void updatePollStatus() {
//        List<Poll> activePolls = pollDao.findActivePolls();
//        System.out.println("");
//        LocalDateTime now = LocalDateTime.now().withNano(0);
//        
//        System.out.println("Active Polls:");
//        if (activePolls.isEmpty()) {
//            System.out.println("No active polls available.");
//        } else {
//            activePolls.forEach(poll -> System.out.println("Poll ID: " + poll.getId() + ", Question: " + poll.getQuestion() + ", Expiry: " + poll.getExpiryDate()));
//        }
//
//        for (Poll poll : activePolls) {
//        	LocalDateTime pollExpiryDate = poll.getExpiryDate().withNano(0);
//        	 System.out.println("Poll Expiry: " + pollExpiryDate);
//             System.out.println("Now: " + now);
//             System.out.println("Is Expired: " + pollExpiryDate.isBefore(now));
//
//             if (pollExpiryDate.isBefore(now))  {
//                poll.setActive(false);
//                pollDao.savePoll(poll);
//                System.out.println("Poll Saved Successfully");
//                System.out.println("Poll ID " + poll.getId() + " has been marked as inactive.");
//            }
//        }
//    }
//}
