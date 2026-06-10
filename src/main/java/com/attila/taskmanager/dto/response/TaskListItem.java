package com.attila.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskListItem {

    private Long id;

    private String name;

    private String description;

    private boolean completed;

}
