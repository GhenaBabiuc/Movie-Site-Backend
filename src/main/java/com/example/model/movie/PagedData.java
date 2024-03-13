package com.example.model.movie;

import lombok.Data;

import java.util.List;

@Data
public class PagedData<T> {
    Long total;
    List<T> data;

    public PagedData(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}