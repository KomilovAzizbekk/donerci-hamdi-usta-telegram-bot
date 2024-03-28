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
@Table(name = "click_invoices")
public class ClickInvoice extends AbsLong {

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Transaction transaction;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_status")
    private Long invoiceStatus;

    @Column(name = "invoice_status_note")
    private String invoiceStatusNote;

}
