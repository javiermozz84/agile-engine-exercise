package com.exercise.model;

import com.exercise.model.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Transaction Class")
public class Transaction extends AbstractBaseModel {

    @Column
    BigDecimal amount;
    @Column
    @Enumerated(EnumType.STRING)
    TransactionType type;
    @Column
    String description;

    @ManyToOne
    @JsonIgnore
    Account account;
}
