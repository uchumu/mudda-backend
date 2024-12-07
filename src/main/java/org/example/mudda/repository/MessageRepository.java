package org.example.mudda.repository;


import org.example.mudda.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {


    Optional<Message> findById(Long id);


}
