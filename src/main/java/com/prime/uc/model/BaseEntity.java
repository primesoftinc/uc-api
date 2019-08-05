package com.prime.uc.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@ToString
public class BaseEntity {
    @Id
    private String id;
}
