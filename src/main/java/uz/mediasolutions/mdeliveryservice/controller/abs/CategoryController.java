package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.CategoryDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;
import javax.ws.rs.Path;

@RequestMapping(CategoryController.CATEGORY)
public interface CategoryController {

    String CATEGORY = Rest.BASE_PATH + "categories/";

    String GET_ALL = "get-all";

    String GET_BY_ID = "get/{id}";

    String ADD = "add";

    String EDIT = "edit/{id}";

    String DELETE = "delete/{id}";

    @GetMapping(GET_ALL)
    ApiResult<Page<CategoryDTO>> getAll(@RequestParam(defaultValue = Rest.DEFAULT_PAGE_NUMBER) int page,
                                        @RequestParam(defaultValue = Rest.DEFAULT_PAGE_SIZE) int size,
                                        @RequestParam(defaultValue = "null", required = false) String search);

    @GetMapping(GET_BY_ID)
    ApiResult<CategoryDTO> getById(@PathVariable Long id);

    @PostMapping(ADD)
    ApiResult<?> add(@RequestBody @Valid CategoryDTO categoryDTO);

    @PutMapping(EDIT)
    ApiResult<?> edit(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO);

    @DeleteMapping(DELETE)
    ApiResult<?> delete(@PathVariable Long id);

}
