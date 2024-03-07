package uz.mediasolutions.mdeliveryservice.entity;

import lombok.*;
import uz.mediasolutions.mdeliveryservice.enums.StepName;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "order_products")
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private StepName name;

}
