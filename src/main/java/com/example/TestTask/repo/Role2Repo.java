package com.example.TestTask.repo;

import com.example.TestTask.entities.Role2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role2Repo extends JpaRepository<Role2,Long>
{

}
