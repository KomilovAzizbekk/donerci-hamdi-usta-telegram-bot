package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.MeasureUnit;
import uz.mediasolutions.mdeliveryservice.entity.Variation;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.MeasureUnitMapper;
import uz.mediasolutions.mdeliveryservice.mapper.VariationMapper;
import uz.mediasolutions.mdeliveryservice.payload.VariationDTO;
import uz.mediasolutions.mdeliveryservice.repository.MeasureUnitRepository;
import uz.mediasolutions.mdeliveryservice.repository.VariationRepository;
import uz.mediasolutions.mdeliveryservice.service.abs.VariationService;

@Service
@RequiredArgsConstructor
public class VariationServiceImpl implements VariationService {
//
//    private final VariationRepository variationRepository;
//    private final VariationMapper variationMapper;
//    private final MeasureUnitRepository measureUnitRepository;
//
//    @Override
//    public ApiResult<Page<VariationDTO>> getAll(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Variation> variations = variationRepository.findAllByOrderByNumberAsc(pageable);
//        Page<VariationDTO> dtos = variations.map(variationMapper::toDTO);
//        return ApiResult.success(dtos);
//    }
//
//    @Override
//    public ApiResult<VariationDTO> getById(Long id) {
//        Variation variation = variationRepository.findById(id).orElseThrow(
//                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
//        VariationDTO dto = variationMapper.toDTO(variation);
//        return ApiResult.success(dto);
//    }
//
//    @Override
//    public ApiResult<?> add(VariationDTO dto) {
//        if (variationRepository.existsByNumber(dto.getNumber())) {
//            throw RestException.restThrow("NUMBER MUST ME UNIQUE", HttpStatus.BAD_REQUEST);
//        } else {
//            Variation entity = variationMapper.toVarEntity(dto);
//            variationRepository.save(entity);
//            return ApiResult.success("SAVED SUCCESSFULLY");
//        }
//    }
//
//    @Override
//    public ApiResult<?> edit(Long id, VariationDTO dto) {
//        if (variationRepository.existsByNumber(dto.getNumber()) &&
//                !variationRepository.existsByNumberAndId(dto.getNumber(), id)) {
//            throw RestException.restThrow("NUMBER MUST ME UNIQUE", HttpStatus.BAD_REQUEST);
//        } else {
//            Variation variation = variationRepository.findById(id).orElseThrow(
//                    () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
//            MeasureUnit measureUnit = measureUnitRepository.findById(id).orElseThrow(
//                    () -> RestException.restThrow("MEASURE UNIT ID NOT FOUND", HttpStatus.BAD_REQUEST));
//            variation.setNumber(dto.getNumber());
//            variation.setPrice(dto.getPrice());
//            variation.setMeasure(measureUnit);
//            variationRepository.save(variation);
//            return ApiResult.success("EDITED SUCCESSFULLY");
//        }
//    }
//
//    @Override
//    public ApiResult<?> delete(Long id) {
//        try {
//            variationRepository.deleteById(id);
//            return ApiResult.success("DELETED SUCCESSFULLY");
//        } catch (Exception e) {
//            throw RestException.restThrow("CANNOT DELETE", HttpStatus.CONFLICT);
//        }
//    }
}