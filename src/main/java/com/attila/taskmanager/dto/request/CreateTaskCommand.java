package com.attila.taskmanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskCommand {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Name must not be blank")
    private String description;


}
