package com.example.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class WatchedFilmId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "film_id")
    private Long filmId;
}
