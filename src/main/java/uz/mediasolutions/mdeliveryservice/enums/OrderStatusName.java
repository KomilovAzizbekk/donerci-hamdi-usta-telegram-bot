package uz.mediasolutions.mdeliveryservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusName {

    PENDING("Tasdiqlanmagan", "Не подтверждено"),
    ACCEPTED("Tasdiqlangan", "Подтвержденный"),
    REJECTED("Rad etilgan", "Отклоненный"),
    DELIVERED("Yetkazib berilgan", "Доставленный");

    private final String nameUz;

    private final String nameRu;

}
