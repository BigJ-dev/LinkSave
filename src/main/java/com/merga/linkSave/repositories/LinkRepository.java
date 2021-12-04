package com.merga.linkSave.repositories;

import com.merga.linkSave.models.Link;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {
    List<Link> findAll();

    Link getById(Long linkId);
}
