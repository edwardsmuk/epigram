package net.ripe.app.epigram.assembler;

import net.ripe.app.epigram.controller.EpigramController;
import net.ripe.app.epigram.model.Epigram;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EpigramModelAssembler implements RepresentationModelAssembler<Epigram, EntityModel<Epigram>> {

    @Override
    public EntityModel<Epigram> toModel(Epigram epigram) {
        // Unconditional links to single-item resource and aggregate root
        EntityModel<Epigram> epigramEntityModel = EntityModel.of(epigram,
                linkTo(methodOn(EpigramController.class).one(epigram.getId())).withSelfRel(),
                linkTo(methodOn(EpigramController.class).all()).withRel("epigrams"),
                linkTo(methodOn(EpigramController.class).random()).withSelfRel()

        );

        return epigramEntityModel;
    }
}