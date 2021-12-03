package com.merga.linkSave.repositories;

import com.merga.linkSave.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
