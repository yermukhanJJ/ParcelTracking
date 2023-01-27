package com.example.mailservice.repository;

import com.example.mailservice.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "select * from mail_history where id_send = ?1", nativeQuery = true)
    Optional<List<History>> getAllMailPath(Long idSend);

    @Query(value = "select * from mail_history where id_send = ?1 and id_post = ?2", nativeQuery = true)
    Optional<History> getHistoryByPostIdAndSendId(Long idSender, Long idPost);

    @Query(value = "select * from mail_history where id_send = ?1 " +
            "and id = ( select MAX(id) from mail_history )", nativeQuery = true)
    Optional<History> getHistoryByLastPost(Long idSender);

    Boolean existsByIdSender(Long idSender);
}
