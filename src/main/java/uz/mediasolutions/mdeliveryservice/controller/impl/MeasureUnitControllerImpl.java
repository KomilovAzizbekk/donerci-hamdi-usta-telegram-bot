package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.BranchController;
import uz.mediasolutions.mdeliveryservice.controller.abs.MeasureUnitController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BranchDTO;
import uz.mediasolutions.mdeliveryservice.payload.LocationDTO;
import uz.mediasolutions.mdeliveryservice.payload.MeasureUnitDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.BranchService;
import uz.mediasolutions.mdeliveryservice.service.abs.MeasureUnitService;

@RestController
@RequiredArgsConstructor
public class MeasureUnitControllerImpl implements MeasureUnitController {

    private final MeasureUnitService measureUnitService;

    @Override
    public ApiResult<Page<MeasureUnitDTO>> getAll(int page, int size, String search) {
        return measureUnitService.getAll(page, size, search);
    }

    @Override
    public ApiResult<MeasureUnitDTO> getById(Long id) {
        return measureUnitService.getById(id);
    }

    @Override
    public ApiResult<?> add(MeasureUnitDTO measureUnitDTO) {
        return measureUnitService.add(measureUnitDTO);
    }

    @Override
    public ApiResult<?> edit(Long id, MeasureUnitDTO measureUnitDTO) {
        return measureUnitService.edit(id, measureUnitDTO);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return measureUnitService.delete(id);
    }
}
