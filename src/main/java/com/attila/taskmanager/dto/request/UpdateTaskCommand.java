package com.attila.taskmanager.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskCommand {

    @Size(min = 1)
    private String name;

    @Size(min = 5)
    private String description;

    private Boolean completed;
}
