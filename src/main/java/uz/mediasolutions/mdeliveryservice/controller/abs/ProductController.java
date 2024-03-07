package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(ProductController.PRODUCT)
public interface ProductController {

    String PRODUCT = Rest.BASE_PATH + "products/";

}
