package com.example.model.movie.dto;

import com.example.model.movie.Film;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FilmDataDto {
    private Film film;
    private String status;
}
