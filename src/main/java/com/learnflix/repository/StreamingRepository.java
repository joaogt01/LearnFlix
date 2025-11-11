package com.learnflix.repository;

import com.learnflix.entity.Streaming;
import com.sun.jdi.LongValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
