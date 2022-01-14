package com.merga.linkSave.repositories;

import com.merga.linkSave.models.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> findAll();
    Link save(Link siteLink);
    Link getById(Long linkId);
}
