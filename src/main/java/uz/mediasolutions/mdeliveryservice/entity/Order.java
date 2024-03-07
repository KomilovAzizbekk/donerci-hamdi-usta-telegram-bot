package uz.mediasolutions.mdeliveryservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import uz.mediasolutions.mdeliveryservice.entity.template.AbsLong;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "orders")
public class Order extends AbsLong {

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private TgUser user;

    @Column(name = "phone_number")
    private String phoneNumber;

    //USER'S LOCATION
    @Column(name = "lon")
    private Double lon;

    @Column(name = "lat")
    private Double lat;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderProducts orderProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentProviders paymentProviders;

    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    @Column(name = "price")
    private Double price;

    @Column(name = "delivery_price")
    private Double deliveryPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    private Transaction transaction;

}
