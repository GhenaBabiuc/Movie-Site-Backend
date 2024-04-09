package com.example.repository.user;

import com.example.model.user.UserFilm;
import com.example.model.user.UserFilmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFilmListsRepository extends JpaRepository<UserFilm, UserFilmId> {
    List<UserFilm> findByIdUserId(Long userId);
}

