package com.merga.linkSave.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String savedDate;
    private String title;
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
