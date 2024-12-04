package com.vector.mytodo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private int id;
    private String title;
    private Date createdDate;
    private Date finishDate;
    private User user;
    private Status status;
}
