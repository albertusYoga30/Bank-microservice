package com.maltesepu.cards.repository;

import com.maltesepu.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface CardsRepo extends JpaRepository<Cards, Long> {
    Optional<Cards> findByPhoneNumber(String phoneNumber);
    Optional<Cards> findByCardNumber(String cardNumber);
}
