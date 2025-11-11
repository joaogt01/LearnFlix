package com.learnflix.controller.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
