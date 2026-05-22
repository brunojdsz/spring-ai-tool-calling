package br.com.brunojdsz.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String company;
    @Column
    private int quantity;

    public Share() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Share share = (Share) o;
        return quantity == share.quantity && Objects.equals(id, share.id) && Objects.equals(company, share.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, quantity);
    }
}
