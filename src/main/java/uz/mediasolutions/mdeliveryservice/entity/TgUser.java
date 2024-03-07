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
@Table(name = "tg_users")
public class TgUser extends AbsLong {

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    private Language language;

    @Column(name = "is_registered")
    private boolean isRegistered;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_banned")
    private boolean isBanned;

    @ManyToOne(fetch = FetchType.LAZY)
    private Step step;

}
