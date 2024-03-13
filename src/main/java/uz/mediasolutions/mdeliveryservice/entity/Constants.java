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
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "constants")
public class Constants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_order_price", nullable = false)
    private Double minOrderPrice;

    @Column(name = "min_delivery_price")
    private Double minDeliveryPrice;

    @Column(name = "price_per_kilometer")
    private Double pricePerKilometer;

    @Column(name = "radius_free_delivery")
    private Double radiusFreeDelivery;

    @Column(name = "min_order_price_for_free_delivery")
    private Double minOrderPriceForFreeDelivery;

    @Column(name = "bot_working")
    private Integer botWorking;

}
