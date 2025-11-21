package com.link.quicklink;

import org.springframework.data.jpa.repository.JpaRepository;

// This is an interface, not a class.
// It extends JpaRepository, which gives us all the database methods.
// We tell it to manage <Link, Long> (Our "Link" entity, whose ID is a "Long")
public interface LinkRepository extends JpaRepository<Link, Long> {

    // This is a "Query Method".
    // Because we named it "findByShortId", Spring Data JPA will
    // automatically write the SQL query: "SELECT * FROM link WHERE short_id = ?"
    // This is the magic!
    Link findByShortId(String shortId);
}