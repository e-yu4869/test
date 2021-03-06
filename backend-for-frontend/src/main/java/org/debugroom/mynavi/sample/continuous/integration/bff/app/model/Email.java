package org.debugroom.mynavi.sample.continuous.integration.bff.app.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Email implements Serializable {

    @Min(0)
    private long userId;
    @Min(0)
    private long emailNo;

    @NotBlank
    @javax.validation.constraints.Email
    @Size(max = 256)
    private String email;

}
