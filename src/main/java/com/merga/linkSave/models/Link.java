package com.merga.linkSave.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String savedDate;
    private String lastModifiedDate;
    private String siteName;
    private String siteUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
