//package uz.mediasolutions.mdeliveryservice.controller.web.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.RestController;
//import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebBasketController;
//import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
//import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
//import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
//import uz.mediasolutions.mdeliveryservice.service.webabs.WebBasketService;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class WebBasketControllerImpl implements WebBasketController {
//
//    private final WebBasketService basketWebService;
//
//    @Override
//    public ApiResult<BasketWebDTO> get(String chatId) {
//        return basketWebService.get(chatId);
//    }
//
//    @Override
//    public ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList) {
//        return basketWebService.add(chatId, dtoList);
//    }
//}
