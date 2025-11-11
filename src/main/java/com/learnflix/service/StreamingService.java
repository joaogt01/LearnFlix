package com.learnflix.service;

import com.learnflix.entity.Streaming;
import com.learnflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> findAll(){
        return repository.findAll();
    }

    public Streaming save(Streaming streaming){
        return repository.save(streaming);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Optional<Streaming> findById(Long id){
        return repository.findById(id);
    }
}

