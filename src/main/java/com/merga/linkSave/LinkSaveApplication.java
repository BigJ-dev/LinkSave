package com.merga.linkSave;

import com.merga.linkSave.models.User;
import com.merga.linkSave.services.UserActionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LinkSaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkSaveApplication.class, args);
	}
//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder() {
//       return new BCryptPasswordEncoder();
//    }
    @Bean
	CommandLineRunner run(UserActionService userActionService) {
        return args -> {
			userActionService.saveUser(new User(null, "C", "1994", "bigjayseSegoapa28@gmail.com","0712139561",null,null));
			userActionService.saveUser(new User(null, "B", "1994", "bigjayseSegoapa28@gmail.com","0712139561",null,null));
			userActionService.saveUser(new User(null, "A", "1994", "bigjayseSegoapa28@gmail.com","0712139561",null,null));
			userActionService.addSiteLink("YouTube","https://www.youtube.com/watch?v=mVoEYmTQYlU&list=RDMMAy3P4M94ebM&index=8",1L);
			userActionService.addSiteLink("Forx","https://www.youtube.com/watch?v=mVoEYmTQYlU&list=RDMMAy3P4M94ebM&index=8",1L);
			userActionService.updateSiteLink("Forex","https://www.youtube.com/watch?v=mVoEYmTQYlU&list=RDMMAy3P4M94ebM&index=8",3L,1L);
//			User user = userActionService.getById(1L);
//			userActionService.getAllUserLinks(user);
			//userActionService.getUserSiteLinks(1L);
			//userActionService.deleteSiteLink(3L,1L);
       };
  }
}
