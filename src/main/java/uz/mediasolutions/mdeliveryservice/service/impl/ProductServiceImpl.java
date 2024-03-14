package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Category;
import uz.mediasolutions.mdeliveryservice.entity.Product;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.ProductMapper;
import uz.mediasolutions.mdeliveryservice.payload.ProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductResDTO;
import uz.mediasolutions.mdeliveryservice.repository.CategoryRepository;
import uz.mediasolutions.mdeliveryservice.repository.ProductRepository;
import uz.mediasolutions.mdeliveryservice.service.abs.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResult<Page<ProductResDTO>> getAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (!search.equals("null")) {
            Page<Product> products = productRepository
                    .findAllByDescriptionRuContainsIgnoreCaseOrDescriptionUzContainsIgnoreCaseOrNameUzContainsIgnoreCaseOrNameRuContainsIgnoreCaseOrCategoryNameRuContainsIgnoreCaseOrCategoryNameUzContainsIgnoreCaseOrderByNumberAsc(
                            search, search, search, search, search, search, pageable);
            Page<ProductResDTO> dtos = products.map(productMapper::toDTO);
            return ApiResult.success(dtos);
        } else {
            Page<Product> products = productRepository.findAllByOrderByNumberAsc(pageable);
            Page<ProductResDTO> dtos = products.map(productMapper::toDTO);
            return ApiResult.success(dtos);
        }
    }

    @Override
    public ApiResult<ProductResDTO> getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        ProductResDTO dto = productMapper.toDTO(product);
        return ApiResult.success(dto);
    }

    @Override
    public ApiResult<?> add(ProductDTO dto) {
        if (productRepository.existsByNumberAndCategoryId(dto.getNumber(), dto.getCategoryId())) {
            throw RestException.restThrow("NUMBER MUST ME UNIQUE", HttpStatus.BAD_REQUEST);
        } else if (productRepository.existsByNameUzOrNameRu(dto.getNameUz(), dto.getNameRu())) {
            throw RestException.restThrow("NAME ALREADY EXISTED", HttpStatus.BAD_REQUEST);
        } else {
            Product product = productMapper.toEntity(dto);
            productRepository.save(product);
            return ApiResult.success("SAVED SUCCESSFULLY");
        }
    }

    @Override
    public ApiResult<?> edit(Long id, ProductDTO dto) throws IOException {
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

            if (!Objects.equals(product.getImageUrl(), dto.getImageUrl())) {
                String imageUrl = product.getImageUrl();
                String imagePath = "delivery-files/" + imageUrl.substring(imageUrl.lastIndexOf('/'));
                Path path = Paths.get(imagePath);
                Files.deleteIfExists(path);
            }

            product.setNumber(dto.getNumber());
            product.setCategory(category);
            product.setNameUz(dto.getNameUz());
            product.setNameRu(dto.getNameRu());
            product.setDescriptionRu(dto.getDescriptionRu());
            product.setDescriptionUz(dto.getDescriptionUz());
            product.setImageUrl(dto.getImageUrl());
            product.setActive(dto.isActive());
            productRepository.save(product);
            return ApiResult.success("EDITED SUCCESSFULLY");
        }
    }

    @Override
    public ApiResult<?> delete(Long id) {
        try {
            productRepository.deleteById(id);
            return ApiResult.success("DELETED SUCCESSFULLY");
        } catch (Exception e) {
            throw RestException.restThrow("CANNOT DELETE", HttpStatus.CONFLICT);
        }
    }
}
