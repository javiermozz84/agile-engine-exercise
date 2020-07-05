package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Account Class.")
public class Account extends AbstractBaseModel {

    @OneToOne
    @MapsId
    User user;
    @Column
    BigDecimal amount;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
            @OrderBy("id DESC")
    List<Transaction> transactionList = new ArrayList<>();

}
