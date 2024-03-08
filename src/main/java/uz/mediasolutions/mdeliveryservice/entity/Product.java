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
@Table(name = "products")
public class Product extends AbsLong {

    @Column(name = "number", columnDefinition = "serial")
    private Long number;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    private Variation variation;

    @Column(name = "count")
    private Integer count;

    private String imageUrl;

}
