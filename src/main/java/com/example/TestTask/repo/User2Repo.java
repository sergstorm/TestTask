package com.example.TestTask.repo;

import com.example.TestTask.entities.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User2Repo extends JpaRepository<User2,Long>
{

    User2 findByUsername(String username);
}
