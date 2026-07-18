package sirmam.springdatajpa.domain.mappedsuper;

import jakarta.persistence.Entity;

@Entity
public class OrderHeaderInheritance extends BaseEntityInheritance {

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
