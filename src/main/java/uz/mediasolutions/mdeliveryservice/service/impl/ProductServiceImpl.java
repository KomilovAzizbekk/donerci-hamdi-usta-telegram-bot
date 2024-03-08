package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Category;
import uz.mediasolutions.mdeliveryservice.entity.MeasureUnit;
import uz.mediasolutions.mdeliveryservice.entity.Product;
import uz.mediasolutions.mdeliveryservice.entity.Variation;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.ProductMapper;
import uz.mediasolutions.mdeliveryservice.mapper.VariationMapper;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationResDTO;
import uz.mediasolutions.mdeliveryservice.repository.CategoryRepository;
import uz.mediasolutions.mdeliveryservice.repository.MeasureUnitRepository;
import uz.mediasolutions.mdeliveryservice.repository.ProductRepository;
import uz.mediasolutions.mdeliveryservice.repository.VariationRepository;
import uz.mediasolutions.mdeliveryservice.service.abs.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final VariationRepository variationRepository;
    private final VariationMapper variationMapper;
    private final MeasureUnitRepository measureUnitRepository;

    @Override
    public ApiResult<Page<ProductVariationResDTO>> getAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (!search.equals("null")) {
            Page<Product> products = productRepository.findAllByNameUzContainsIgnoreCaseOrNameRuContainsIgnoreCaseOrderByNumberAsc(search, search, pageable);
            Page<ProductVariationResDTO> dtos = products.map(productMapper::toDTO);
            return ApiResult.success(dtos);
        } else {
            Page<Product> products = productRepository.findAllByOrderByNumberAsc(pageable);
            Page<ProductVariationResDTO> dtos = products.map(productMapper::toDTO);
            return ApiResult.success(dtos);
        }
    }

    @Override
    public ApiResult<ProductVariationResDTO> getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        ProductVariationResDTO dto = productMapper.toDTO(product);
        return ApiResult.success(dto);
    }

    @Override
    public ApiResult<?> add(ProductVariationDTO dto) {
        if (productRepository.existsByNumberAndCategoryId(dto.getNumber(), dto.getCategoryId())) {
            throw RestException.restThrow("NUMBER MUST ME UNIQUE", HttpStatus.BAD_REQUEST);
        } else if (productRepository.existsByNameUzOrNameRu(dto.getNameUz(), dto.getNameRu())) {
            throw RestException.restThrow("NAME ALREADY EXISTED", HttpStatus.BAD_REQUEST);
        } else {
            Variation variation = variationMapper.toEntity(dto);
            Variation saved = variationRepository.save(variation);
            Product product = productMapper.toEntity(dto);
            product.setVariation(saved);
            productRepository.save(product);
            return ApiResult.success("SAVED SUCCESSFULLY");
        }
    }

    @Override
    public ApiResult<?> edit(Long id, ProductVariationDTO dto) {
        if (productRepository.existsByNumberAndCategoryId(dto.getNumber(), dto.getCategoryId()) &&
                !productRepository.existsByNumberAndId(dto.getNumber(), id)) {
            throw RestException.restThrow("NUMBER MUST ME UNIQUE", HttpStatus.BAD_REQUEST);
        } else if (productRepository.existsByNameUzOrNameRu(dto.getNameUz(), dto.getNameRu()) &&
                !productRepository.existsByNameUzOrNameRuAndId(dto.getNameUz(), dto.getNameRu(), id)) {
            throw RestException.restThrow("NAME ALREADY EXISTED", HttpStatus.BAD_REQUEST);
        } else {
            Product product = productRepository.findById(id).orElseThrow(
                    () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
            Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(
                    () -> RestException.restThrow("CATEGORY ID NOT FOUND", HttpStatus.BAD_REQUEST));
            MeasureUnit measureUnit = measureUnitRepository.findById(dto.getMeasureId()).orElseThrow(
                    () -> RestException.restThrow("MEASURE UNIT ID NOT FOUND", HttpStatus.BAD_REQUEST));

            Variation variation = product.getVariation();
            variation.setMeasure(measureUnit);
            variation.setPrice(dto.getPrice());
            Variation savedVariation = variationRepository.save(variation);

            product.setNumber(dto.getNumber());
            product.setCategory(category);
            product.setCount(dto.getCount());
            product.setNameUz(dto.getNameUz());
            product.setNameRu(dto.getNameRu());
            product.setVariation(savedVariation);
            product.setImageUrl(dto.getImageUrl());
            productRepository.save(product);
            return ApiResult.success("EDITED SUCCESSFULLY");
        }
    }

    @Override
    public ApiResult<?> delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        try {
            variationRepository.deleteById(product.getVariation().getId());
            return ApiResult.success("DELETED SUCCESSFULLY");
        } catch (Exception e) {
            throw RestException.restThrow("CANNOT DELETE", HttpStatus.CONFLICT);
        }
    }
}
