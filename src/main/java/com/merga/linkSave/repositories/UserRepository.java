package com.merga.linkSave.repositories;

import com.merga.linkSave.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
   //  User findById(Long userId);
//    @Query(value = "select * from user", nativeQuery = true)
 //  List getAllUsers();

//    User findByUsername(String username);
//
//    User save(User user);
}
