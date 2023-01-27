package com.example.mailservice.service;

import com.example.mailservice.model.History;
import com.example.mailservice.model.Post;
import com.example.mailservice.model.Sender;
import com.example.mailservice.payload.SendRequest;
import com.example.mailservice.repository.HistoryRepository;
import com.example.mailservice.repository.PostRepository;
import com.example.mailservice.repository.SendRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final HistoryRepository historyRepository;

    private final PostRepository postRepository;

    private final SendRepository sendRepository;

    public Sender sendMail(SendRequest request) {
        Sender parcel = sendRepository.save(new Sender(request.getType(), request.getIndex(), request.getAddress(), request.getRecipientName()));
        History history = new History();
        Post post = postRepository.findById(request.getIdPost())
                .orElseThrow(() -> new EntityNotFoundException("Post office with id: " + request.getIdPost() + " not found!"));
        history.setIdSender(parcel.getId());
        history.setIdPost(post.getId());
        history.setArrivalDate(request.getDate());
        history.setArrivalPost(post.getTitle());
        historyRepository.save(history);
        return parcel;
    }

    public History arrivalParcelStatus(Long idSend, Long idPost, LocalDate arrivalDate) {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new EntityNotFoundException("Post with id: " + idPost + " not found!"));
        Sender parcel = sendRepository.findById(idSend)
                .orElseThrow(() -> new EntityNotFoundException("Parcel with id: " + idSend + " not found!"));
        return historyRepository.save(new History(parcel.getId(), post.getId(), arrivalDate, post.getTitle()));
    }

    public History departureParcelStatus(Long idSend, Long idPost, LocalDate departureDate) {
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new EntityNotFoundException("Post with id: " + idPost + " not found!"));
        Sender parcel = sendRepository.findById(idSend)
                .orElseThrow(() -> new EntityNotFoundException("Parcel with id: " + idSend + " not found!"));
        History history = historyRepository.getHistoryByPostIdAndSendId(parcel.getId(), post.getId())
                .orElseThrow(() -> new EntityNotFoundException("History with parcel id: " + idSend + " and post id: " + idPost + " not found!"));
        history.setDepartureDate(departureDate);
        return historyRepository.save(history);
    }

    public History receivingParcelStatus(LocalDate receivingDate, Long idSender) {
        History history = historyRepository.getHistoryByLastPost(idSender)
                .orElseThrow(() -> new EntityNotFoundException("Parcel with id: " + idSender + " not found!"));
        history.setReceivingDate(receivingDate);
        return historyRepository.save(history);
    }

}
