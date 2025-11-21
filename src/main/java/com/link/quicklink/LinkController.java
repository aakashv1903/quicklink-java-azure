package com.link.quicklink;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.Random;

@RestController // This tells Spring this class will handle web requests
public class LinkController {

    @Autowired // This "injects" an instance of your LinkRepository
    private LinkRepository linkRepository;

    // --- Endpoint 1: Create a Short Link ---
    // This method listens for POST requests to "/api/shorten"
    @PostMapping("/api/shorten")
    public Link shortenLink(@RequestBody Map<String, String> request) {
        // Get the longUrl from the JSON body
        String longUrl = request.get("longUrl");
        
        // Generate a random 6-character ID
        String shortId = generateShortId();
        
        // Create a new Link object
        Link link = new Link();
        link.setLongUrl(longUrl);
        link.setShortId(shortId);
        
        // Save the new Link object to the H2 database and return it
        return linkRepository.save(link);
    }

    // --- Endpoint 2: Redirect to the Long URL ---
    // This method listens for GET requests (like from a browser)
    // The "{shortId}" part means it's a variable
    @GetMapping("/{shortId}")
    public RedirectView redirectToLongUrl(@PathVariable String shortId) {
        
        // Use the repository to find the link by its shortId
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

    // --- Helper function to make a random string ---
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
