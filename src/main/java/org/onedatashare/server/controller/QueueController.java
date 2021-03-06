package org.onedatashare.server.controller;

import org.onedatashare.server.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/stork/q")
public class QueueController {

  @Autowired
  private JobService jobService;

  @PostMapping
  public Object queue(@RequestHeader HttpHeaders headers) {
    String cookie = headers.getFirst("cookie");
    return jobService.getAllJobsForUser(cookie)
            .subscribeOn(Schedulers.elastic());
  }
}
