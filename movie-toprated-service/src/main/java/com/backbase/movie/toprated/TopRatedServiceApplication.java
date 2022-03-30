package com.backbase.movie.toprated;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
public class TopRatedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopRatedServiceApplication.class, args);
	}

}
