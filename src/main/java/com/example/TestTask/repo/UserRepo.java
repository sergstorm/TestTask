package com.example.TestTask.repo;


import com.example.TestTask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long>
{
   User findByName(String name);
}
