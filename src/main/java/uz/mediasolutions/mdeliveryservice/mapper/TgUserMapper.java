package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;
import uz.mediasolutions.mdeliveryservice.payload.TgUserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TgUserMapper {

    TgUserDTO toDTO(TgUser user);

}
