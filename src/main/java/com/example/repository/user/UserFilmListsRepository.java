package com.example.repository.user;

import com.example.model.user.WatchedFilm;
import com.example.model.user.WatchedFilmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFilmListsRepository extends JpaRepository<WatchedFilm, WatchedFilmId> {
    List<WatchedFilm> findByIdUserId(Long userId);
}

