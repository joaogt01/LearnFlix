package com.learnflix.mapper;

import com.learnflix.controller.request.StreamingRequest;
import com.learnflix.controller.response.StreamingResponse;
import com.learnflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest Request){
        return Streaming
                .builder()
                .name(Request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}
