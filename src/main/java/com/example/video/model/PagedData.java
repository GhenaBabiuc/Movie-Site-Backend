package com.example.video.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagedData<T> {
    Long total;
    List<T> data = new ArrayList<>();

    public PagedData(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}