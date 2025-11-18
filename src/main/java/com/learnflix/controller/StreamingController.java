package com.learnflix.controller;

import com.learnflix.controller.request.StreamingRequest;
import com.learnflix.controller.response.StreamingResponse;
import com.learnflix.entity.Streaming;
import com.learnflix.mapper.StreamingMapper;
import com.learnflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learnflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping()
    public List<StreamingResponse> getAllStreamings(){
        List<Streaming> streamings = streamingService.findAll();
        return streamings.stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StreamingResponse saveStreaming(@Valid @RequestBody StreamingRequest request){
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.save(newStreaming);
        return StreamingMapper.toStreamingResponse(savedStreaming);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreamingById(@PathVariable Long id){
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
