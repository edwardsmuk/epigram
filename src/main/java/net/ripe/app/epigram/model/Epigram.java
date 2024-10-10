package net.ripe.app.epigram.model;

import lombok.*;
import net.ripe.app.epigram.audit.AuditMetadata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "epigrams")
public class Epigram extends AuditMetadata {
    @Id
    private String id; //unique id for the epigram
    @NonNull
    private String content; // Text content for the epigram
    @NonNull
    private String author; // Author for the epigram

}
