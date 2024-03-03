package com.example.video.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilmFilter {
    private String title;
    private String year;
    private List<String> genres;
    private String valueOrder;
    private String order;
}
