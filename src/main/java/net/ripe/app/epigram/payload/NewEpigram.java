package net.ripe.app.epigram.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewEpigram {

    @NotNull(message = "Content should not be null")
    @NotBlank(message = "Content should not be blank")
    @Size(min=10,max = 1000)
    private String content;

    @NotNull(message = "Author should not be null")
    @NotBlank(message = "Author should not be blank")
    @Size(min=2,max = 100)
    private String author;
}
