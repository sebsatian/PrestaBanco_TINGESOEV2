package com.example.requestservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "first_home_request")
public class FirstHomeReqEntity extends RequestEntity {

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "credit_history")
    private byte[] creditHistory;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "job_contract")
    private byte[] jobContract;
}
