package net.ripe.app.epigram.repository;

import net.ripe.app.epigram.model.Epigram;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EpigramRepository extends MongoRepository<Epigram, String> {

}