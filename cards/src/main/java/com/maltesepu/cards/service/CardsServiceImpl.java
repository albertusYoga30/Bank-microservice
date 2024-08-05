package com.maltesepu.cards.service;

import com.maltesepu.cards.constants.CardsConstants;
import com.maltesepu.cards.dto.CardsDto;
import com.maltesepu.cards.entity.Cards;
import com.maltesepu.cards.exception.CardAlreadyExistsException;
import com.maltesepu.cards.exception.ResourceNotFoundException;
import com.maltesepu.cards.mapper.CardsMapper;
import com.maltesepu.cards.repository.CardsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService {

    private CardsRepo cardsRepo;

    @Override
    public void createCard(String phoneNumber) {
        Optional<Cards> optionalCards = cardsRepo.findByPhoneNumber(phoneNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given number " + phoneNumber);
        }

        Cards newCard = new Cards();
        long generateCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(generateCardNumber));
        newCard.setPhoneNumber(phoneNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        cardsRepo.save(newCard);
    }

    @Override
    public CardsDto fetchCard(String phoneNumber) {
        Cards cards = cardsRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Phone number", phoneNumber)
        );
        return CardsMapper.mapToCardDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepo.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Card Number", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepo.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String phoneNumber) {
        Cards cards = cardsRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Phone Number", phoneNumber)
        );
        cardsRepo.deleteById(cards.getCardId());
        return true;
    }
}
