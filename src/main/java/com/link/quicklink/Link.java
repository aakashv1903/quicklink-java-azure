package com.link.quicklink;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

@Entity // Tells Spring this is a database table
public class Link {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true) // Make sure short IDs are unique
    private String shortId; // e.g., "aK3bF"

    @Column(length = 2048) // Make sure the long URL can be very long
    private String longUrl;

    private int clickCount = 0; // Default to 0

    private Date createdAt = new Date();

    // --- Getters and Setters ---
    // In VS Code, you can right-click > Source Action > Generate Getters and Setters
    // Or you can copy these:
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getShortId() { return shortId; }
    public void setShortId(String shortId) { this.shortId = shortId; }
    public String getLongUrl() { return longUrl; }
    public void setLongUrl(String longUrl) { this.longUrl = longUrl; }
    public int getClickCount() { return clickCount; }
    public void setClickCount(int clickCount) { this.clickCount = clickCount; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}