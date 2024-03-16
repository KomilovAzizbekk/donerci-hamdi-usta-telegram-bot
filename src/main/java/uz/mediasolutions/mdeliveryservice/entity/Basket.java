package uz.mediasolutions.mdeliveryservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import uz.mediasolutions.mdeliveryservice.entity.template.AbsLong;

import javax.persistence.*;
import java.util.List;

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
@Table(name = "baskets")
public class Basket extends AbsLong {

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderProducts> orderProducts;

    @OneToOne(fetch = FetchType.LAZY)
    private TgUser tgUser;

    @Column(name = "total_price")
    private Double totalPrice;

}
