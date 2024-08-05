package com.maltesepu.cards.service;

import com.maltesepu.cards.dto.CardsDto;

public interface CardsService {

    void createCard(String phoneNumber);

    CardsDto fetchCard(String phoneNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String phoneNumber);
}
