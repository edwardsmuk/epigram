package net.ripe.app.epigram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ripe.app.epigram.assembler.EpigramModelAssembler;
import net.ripe.app.epigram.exception.ResourceNotFoundException;
import net.ripe.app.epigram.model.Epigram;
import net.ripe.app.epigram.payload.NewEpigram;
import net.ripe.app.epigram.repository.EpigramRepository;
import net.ripe.app.epigram.service.EpigramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/epigrams")
public class EpigramController {

    @Autowired
    private EpigramRepository epigramRepository;

    @Autowired
    private EpigramService epigramService;

    @Autowired
    private EpigramModelAssembler epigramModelAssembler;

    @GetMapping("/random/1")
    public EntityModel<Epigram> random() {
        log.info("GET Request to retrieve one random Epigram");
        Epigram epigram = epigramService.getRandomEpigram()
                .orElseThrow(() -> new ResourceNotFoundException("No Epigrams","random","random"));
        return epigramModelAssembler.toModel(epigram);
    }

    @GetMapping
    public CollectionModel<EntityModel<Epigram>> all() {
        log.info("GET request to retrieve all Epigrams");
        List<EntityModel<Epigram>> transactions = epigramRepository.findAll().stream()
                .map(epigramModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(transactions,
                linkTo(methodOn(EpigramController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Epigram> one(@PathVariable String id) {
        log.info("GET Request to retrieve Epigram with id {}", id);
        Epigram epigram = epigramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Epigram","id",id));
        return epigramModelAssembler.toModel(epigram);
    }

    //tag::newEpigram[]
    @PostMapping
    public ResponseEntity<EntityModel<Epigram>> newEpigram(@RequestBody NewEpigram newEpigramPayload) {

       ;

        log.info("Creating a new Epigram document");
        Epigram newEpigram = epigramRepository.save( Epigram.builder().content(newEpigramPayload.getContent()).author(newEpigramPayload.getAuthor()).build());

        return ResponseEntity
                .created(linkTo(methodOn(EpigramController.class).one(newEpigram.getId())).toUri())
                .body(epigramModelAssembler.toModel(newEpigram));
    }

    //tag::delete[]
    @DeleteMapping("/{id}")
    ResponseEntity<?>  delete(@PathVariable String id){
        Epigram epigram = epigramRepository.findById(id).orElseThrow (()-> new ResourceNotFoundException("Epigram", "id",id));

        log.info("Deleting Epigram with id {}", id);
        epigramRepository.delete(epigram);
        log.info("The Epigram with id {} has been deleted", id);
        return ResponseEntity.noContent().build();
    }
    // end::delete
}
