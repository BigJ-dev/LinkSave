package com.merga.linkSave.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Link> links;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Note> notes;
}

//@OneToMany(fetch = FetchType.EAGER)
//@ManyToMany(fetch = FetchType.Lazy)
//mark your method with Transactional lazy loading
