package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.entity.Constants;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;

@RequestMapping(ConstantsController.CONSTANTS)
public interface ConstantsController {

    String CONSTANTS = Rest.BASE_PATH + "constants/";

    String GET = "get";


    String EDIT = "edit/{id}";

    @GetMapping(GET)
    ApiResult<Constants> get();


    @PutMapping(EDIT)
    ApiResult<?> edit(@PathVariable Long id, @RequestBody @Valid Constants constants);

}
