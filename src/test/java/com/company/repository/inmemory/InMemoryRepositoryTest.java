package com.company.repository.inmemory;

import com.company.model.Round;
import com.company.repository.GenericRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class InMemoryRepositoryTest {

    GenericRepository genericRepository = new RoundInMemoryRepository();

    @Test
    public void shouldAddNewEntity() {
        Round round = new Round();

        Integer id = genericRepository.create(round);
        Object savedRound = genericRepository.findById(id).get();

        assertThat(savedRound).isEqualTo(round);
    }

    @Test
    public void shouldReturnEmptyForNotFound() {
        Boolean isPresent = genericRepository.findById(12).isPresent();

        assertThat(isPresent).isFalse();
    }

    @Test
    public void shouldUpdateEntitySuccessfully() {
        Round round = new Round();
        Round round2 = new Round();

        Integer id = genericRepository.create(round);
        Object savedRound = genericRepository.findById(id).get();
        genericRepository.update(id, round2);
        Object savedRound2 = genericRepository.findById(id).get();

        assertThat(savedRound2).isEqualTo(round2);
    }


}