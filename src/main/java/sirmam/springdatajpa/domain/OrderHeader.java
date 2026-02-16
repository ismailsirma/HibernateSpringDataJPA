package sirmam.springdatajpa.domain;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@AttributeOverrides({
        @AttributeOverride(name = "shippingAddress.address",
        column = @Column(name = "shipping_address")
        ),
        @AttributeOverride(name = "shippingAddress.city",
                column = @Column(name = "shipping_city")
        ),
        @AttributeOverride(name = "shippingAddress.state",
                column = @Column(name = "shipping_state")
        ),
        @AttributeOverride(name = "shippingAddress.zipCode",
                column = @Column(name = "shipping_zip_code")
        ),
        @AttributeOverride(name = "billingToAddress.address",
                column = @Column(name = "bill_to_address")
        ),
        @AttributeOverride(name = "billingToAddress.city",
                column = @Column(name = "bill_to_city")
        ),
        @AttributeOverride(name = "billingToAddress.state",
                column = @Column(name = "bill_to_state")
        ),
        @AttributeOverride(name = "billingToAddress.zipCode",
                column = @Column(name = "bill_to_zip_code")
        )
})
public class OrderHeader extends BaseEntity {

    private String customer;
    @Embedded
    private Address shippingAddress;
    @Embedded
    private Address billToAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader")
    private Set<OrderLine> orderlines;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(Set<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHeader that)) return false;
        if (!super.equals(o)) return false;

        if (getCustomer() != null ? !getCustomer().equals(that.getCustomer()) : that.getCustomer() != null)
            return false;
        if (getShippingAddress() != null ? !getShippingAddress().equals(that.getShippingAddress()) : that.getShippingAddress() != null)
            return false;
        if (getBillToAddress() != null ? !getBillToAddress().equals(that.getBillToAddress()) : that.getBillToAddress() != null)
            return false;
        if (getOrderStatus() != that.getOrderStatus()) return false;
        return getOrderlines() != null ? getOrderlines().equals(that.getOrderlines()) : that.getOrderlines() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getShippingAddress() != null ? getShippingAddress().hashCode() : 0);
        result = 31 * result + (getBillToAddress() != null ? getBillToAddress().hashCode() : 0);
        result = 31 * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        result = 31 * result + (getOrderlines() != null ? getOrderlines().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "customer='" + customer + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billToAddress=" + billToAddress +
                ", orderStatus=" + orderStatus +
                ", orderlines=" + orderlines +
                '}';
    }
}
