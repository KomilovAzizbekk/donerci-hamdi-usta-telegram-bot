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
@Table(name = "transactions")
public class Transaction extends AbsLong {

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentProviders paymentProvider;

    @Column(name = "sum")
    private Double sum;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentStatus status;

}
