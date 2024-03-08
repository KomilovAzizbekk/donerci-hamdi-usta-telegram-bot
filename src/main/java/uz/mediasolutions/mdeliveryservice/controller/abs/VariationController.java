package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BranchDTO;
import uz.mediasolutions.mdeliveryservice.payload.LocationDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;

//@RequestMapping(VariationController.VARIATION)
public interface VariationController {

//    String VARIATION = Rest.BASE_PATH + "variation/";
//
//    String GET_ALL = "get-all";
//
//    String GET_BY_ID = "get/{id}";
//
//    String ADD = "add";
//
//    String EDIT = "edit/{id}";
//
//    String DELETE = "delete/{id}";
//
//    @GetMapping(GET_ALL)
//    ApiResult<Page<VariationDTO>> getAll(@RequestParam(defaultValue = Rest.DEFAULT_PAGE_NUMBER) int page,
//                                         @RequestParam(defaultValue = Rest.DEFAULT_PAGE_SIZE) int size);
//
//
//    @GetMapping(GET_BY_ID)
//    ApiResult<VariationDTO> getById(@PathVariable Long id);
//
//    @PostMapping(ADD)
//    ApiResult<?> add(@RequestBody @Valid VariationDTO variationDTO);
//
//    @PutMapping(EDIT)
//    ApiResult<?> edit(@PathVariable Long id, @RequestBody @Valid VariationDTO variationDTO);
//
//    @DeleteMapping(DELETE)
//    ApiResult<?> delete(@PathVariable Long id);


}
