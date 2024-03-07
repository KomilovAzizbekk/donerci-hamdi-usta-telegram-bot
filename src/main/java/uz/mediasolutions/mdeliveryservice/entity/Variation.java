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
@Table(name = "variations")
public class Variation extends AbsLong {

    @Column(name = "number", columnDefinition = "serial")
    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    private MeasureUnit measure;

    @Column(name = "price")
    private Double price;

}
