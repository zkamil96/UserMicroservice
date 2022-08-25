package com.example.usermicroservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long owner;
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
}
