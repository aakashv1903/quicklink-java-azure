package com.link.quicklink;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.Random;

@RestController 
public class LinkController {

    @Autowired 
    private LinkRepository linkRepository;

    @PostMapping("/api/shorten")
    public Link shortenLink(@RequestBody Map<String, String> request) {
        String longUrl = request.get("longUrl");
        
        String shortId = generateShortId();
        
        Link link = new Link();
        link.setLongUrl(longUrl);
        link.setShortId(shortId);
        
        return linkRepository.save(link);
    }

    @GetMapping("/{shortId}")
    public RedirectView redirectToLongUrl(@PathVariable String shortId) {
        
        Link link = linkRepository.findByShortId(shortId);
        
        if (link != null) {
            // If we find it, send the user's browser to the longUrl
            // This is a 302 Redirect
            return new RedirectView(link.getLongUrl());
        } else {
            // If the link doesn't exist, send them to an error page
            // (For now, we'll just send them to an "error" path
            // that doesn't exist, or you could redirect to "/")
            return new RedirectView("/error-page-not-found");
        }
    }

    private String generateShortId() {
        int length = 6;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
