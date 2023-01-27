package com.example.mailservice.service;

import com.example.mailservice.model.History;
import com.example.mailservice.model.Post;
import com.example.mailservice.model.Sender;
import com.example.mailservice.payload.MessageResponse;
import com.example.mailservice.payload.SendRequest;
import com.example.mailservice.repository.HistoryRepository;
import com.example.mailservice.repository.PostRepository;
import com.example.mailservice.repository.SendRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final HistoryRepository historyRepository;

    private final PostRepository postRepository;

    private final SendRepository sendRepository;

    public List<History> getAllPath(Long idSend) {
        return historyRepository.getAllMailPath(idSend)
                .orElseThrow(() -> new EntityNotFoundException("Send with id + " + idSend + " not found!"));
    }

    public Map<History, MessageResponse> getStatusParcel(Long idSend) {

        History history = historyRepository.getHistoryByLastPost(idSend)
                .orElseThrow(() -> new EntityNotFoundException("History with sender id: " + idSend + " not found!"));
        if (history.getDepartureDate() == null && history.getArrivalDate() != null && history.getReceivingDate() == null) {
            Post post = postRepository.findById(history.getIdPost())
                    .orElseThrow(() -> new EntityNotFoundException("Post with id: " + history.getIdPost() + " not found!"));
            MessageResponse message = new MessageResponse("Parcel at the intermediate post office: " + post.getTitle() + ". Address post office: " + post.getAddress());
            Map<History, MessageResponse> statusInfoMap = new HashMap<>();
            statusInfoMap.put(history, message);
            return statusInfoMap;
        }

        if (history.getDepartureDate() != null && history.getArrivalDate() != null && history.getReceivingDate() == null) {
            MessageResponse message = new MessageResponse("Parcel is still on the way to intermediate post office!");
            Map<History, MessageResponse> statusInfoMap = new HashMap<>();
            statusInfoMap.put(history, message);
            return statusInfoMap;
        }

        MessageResponse message = new MessageResponse("The addressee received the parcel!");
        Map<History, MessageResponse> statusInfoMap = new HashMap<>();
        statusInfoMap.put(history, message);
        return statusInfoMap;
    }
}
