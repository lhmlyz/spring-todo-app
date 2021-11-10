package com.app.todo.model;

import com.app.todo.annotations.UniqueTask;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.WeekFields.ISO;

@Data
public class Todo {

    private long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @UniqueTask
    @JsonProperty("task")
    private String task;

    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("context")
    private String context;

    private LocalDateTime createDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @FutureOrPresent
    @JsonProperty("deadline")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

}
