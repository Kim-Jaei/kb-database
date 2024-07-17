package org.scoula.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 테이블들의 컬럼을 지정해 놓은 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoVO
{
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private String userId;
}

