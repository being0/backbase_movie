package com.backbase.movie.toprated;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
// ShedLock makes sure that jobs will run only by one machine(not always the same) in a cluster
@EnableSchedulerLock(defaultLockAtMostFor = "5m")
@EnableCaching
public class TopRatedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopRatedServiceApplication.class, args);
	}

}
