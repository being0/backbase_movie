package com.backbase.movie.toprated.service;

import com.backbase.movie.toprated.service.repository.Top10RatedMovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Refreshes top10_rated_materialized view periodically.
 * The ShedLock makes sure only one server runs the job
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/30/2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Top10MaterializedViewRefresher {

    private final Top10RatedMovieRepository top10RatedMovieRepository;

    @SchedulerLock(name = "refresh_top10_view_task", lockAtMostFor = "${my.top10.materialized-view.keep-lock}", lockAtLeastFor = "${my.top10.materialized-view.keep-lock}")
    @Scheduled(fixedRateString = "${my.top10.materialized-view.refresh-interval-ms}", initialDelay = 3000L)
    public void refresh() {
        long t1 = System.currentTimeMillis();
        top10RatedMovieRepository.refreshMaterializedView();
        log.info("Top 10 materialized view updated in {}ms", System.currentTimeMillis() - t1);
    }

}
