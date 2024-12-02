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
@Table(name = "business_request")
public class BusinessReqEntity extends RequestEntity {

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "financial_statement")
    private byte[] financialStatement;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "business_plan")
    private byte[] businessPlan;

    public void setFinancialStatement(byte[] financialStatement) {
        this.financialStatement = financialStatement;
    }
}
