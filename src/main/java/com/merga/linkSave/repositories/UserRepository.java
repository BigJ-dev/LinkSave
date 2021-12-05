package com.merga.linkSave.repositories;

import com.merga.linkSave.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getById(Long userId);
}
