package uz.mediasolutions.mdeliveryservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import uz.mediasolutions.mdeliveryservice.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(name = "branches")
public class Branch extends AbsLong {

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "address_uz")
    private String addressUz;

    @Column(name = "address_ru")
    private String addressRu;

    @Column(name = "opening_time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closingTime;

    @Column(name = "is_closes_after_mn")
    private boolean isClosesAfterMn;

    @Column(name = "is_active")
    private boolean isActive;

}
