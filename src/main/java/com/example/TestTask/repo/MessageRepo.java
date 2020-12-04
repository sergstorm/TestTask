package com.example.TestTask.repo;

import com.example.TestTask.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;


public interface MessageRepo extends JpaRepository<Message,Long>
{
    List<Message> findMessageByApprovedIsFalse();
    List<Message> findMessageByApprovedIsTrue();
    void deleteMessageById(Long id);

   // Iterable<Message> findBySearch(String search);
    Iterable<Message> findByTitle(String title);

}
