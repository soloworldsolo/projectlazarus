package com.lazarus.twitter.projectlazarus.controller;

import com.lazarus.twitter.projectlazarus.exception.TwitterException;
import com.lazarus.twitter.projectlazarus.model.Status;
import com.lazarus.twitter.projectlazarus.model.Tweet;
import com.lazarus.twitter.projectlazarus.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * rest controller contains endpoints for all API
 */
@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets/{id}")
    public  ResponseEntity getTweetsById(@PathVariable("id") String id)  {
        try {
            return ResponseEntity.ok().body(tweetService.getTweetsById(Objects.requireNonNull(id)));

        }catch (TwitterException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/tweet/status")
    public ResponseEntity<String> postStatusUpdate(@RequestBody Status status) {
        try {
            return ResponseEntity.ok(tweetService.updateStatus(status));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/tweet/destroy/{id}")
    public ResponseEntity<String> destroyStatus(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(tweetService.deleteStatus(id));
        } catch (TwitterException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @PostMapping("/tweet/retweet/{id}/{tweetId}")
    public ResponseEntity<String> reTweet(@PathVariable("id") String id, @PathVariable("tweetId") String tweetId) {
        return ResponseEntity.ok().body(tweetService.reTweet(id, tweetId));
    }

    @PutMapping("/tweet/hidereply/{id}")
    public ResponseEntity<String> hideReplies(@PathVariable("id") String id, @RequestBody Status status) {
        return ResponseEntity.ok().body(tweetService.hideReplies(id, status));
    }
}
