package org.scoula.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoVO {
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private String userid;
}
