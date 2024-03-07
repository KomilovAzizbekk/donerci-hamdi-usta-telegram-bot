package uz.mediasolutions.mdeliveryservice.mapper;

import org.mapstruct.Mapper;
import uz.mediasolutions.mdeliveryservice.entity.Branch;
import uz.mediasolutions.mdeliveryservice.payload.BranchDTO;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchDTO toDTO(Branch branch);

    Branch toEntity(BranchDTO branchDTO);

}
