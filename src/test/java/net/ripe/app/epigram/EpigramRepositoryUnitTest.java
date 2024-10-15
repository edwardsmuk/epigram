package net.ripe.app.epigram;

import net.ripe.app.epigram.model.Epigram;
import net.ripe.app.epigram.repository.EpigramRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class EpigramRepositoryUnitTest {

    @Autowired
    private EpigramRepository epigramRepository;


    @Test
    public void testSaveAndFindEpigram() {
        // Create and save an epigram
        Epigram epigram = Epigram.builder().content("Wisdom is the daughter of experience").author("Leonardo da Vinci").build();
        epigramRepository.save(epigram);

        // Retrieve the saved epigram
        Epigram foundEpigram = epigramRepository.findById(epigram.getId()).orElse(null);

        // Assertions
        assertNotNull(foundEpigram);
        assertEquals("Wisdom is the daughter of experience", foundEpigram.getContent());
    }
}
