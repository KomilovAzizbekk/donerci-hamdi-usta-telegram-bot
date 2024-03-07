package uz.mediasolutions.mdeliveryservice.entity;

import lombok.*;
import uz.mediasolutions.mdeliveryservice.enums.ProviderName;
import uz.mediasolutions.mdeliveryservice.enums.StepName;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "payment_providers")
public class PaymentProviders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ProviderName name;

    @Column(name = "is_active")
    private boolean isActive;

}
