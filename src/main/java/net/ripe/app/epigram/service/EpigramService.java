package net.ripe.app.epigram.service;

import net.ripe.app.epigram.model.Epigram;
import net.ripe.app.epigram.repository.EpigramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EpigramService {
    @Autowired
    private EpigramRepository epigramRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //Get Random Epigram
    public Optional<Epigram> getRandomEpigram(){
        // Aggregation with $sample to get one random document
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sample(1)
        );

        // Execute aggregation and fetch results
        AggregationResults<Epigram> result = mongoTemplate.aggregate(aggregation, "epigrams", Epigram.class);

        // Return the first (random) document
        return Optional.ofNullable(result.getUniqueMappedResult());
    }

}
