package com.example.requestservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "remodeling_request")
@PrimaryKeyJoinColumn(name = "id")
public class RemodelingReqEntity extends RequestEntity {
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "remodeling_budget")
    private byte[] remodelingBudget;
}