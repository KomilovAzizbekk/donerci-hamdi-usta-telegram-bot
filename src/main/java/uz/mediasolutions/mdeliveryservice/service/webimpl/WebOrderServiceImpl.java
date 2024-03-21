package uz.mediasolutions.mdeliveryservice.service.webimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.entity.Basket;
import uz.mediasolutions.mdeliveryservice.entity.Order;
import uz.mediasolutions.mdeliveryservice.entity.OrderProducts;
import uz.mediasolutions.mdeliveryservice.entity.TgUser;
import uz.mediasolutions.mdeliveryservice.enums.OrderStatusName;
import uz.mediasolutions.mdeliveryservice.enums.StepName;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.UniversalMapper;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderWebDTO;
import uz.mediasolutions.mdeliveryservice.repository.*;
import uz.mediasolutions.mdeliveryservice.service.webabs.WebOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebOrderServiceImpl implements WebOrderService {

    private final OrderRepository orderRepository;
    private final TgUserRepository tgUserRepository;
    private final UniversalMapper universalMapper;
    private final OrderStatusRepository orderStatusRepository;
    private final TgService tgService;
    private final BasketRepository basketRepository;
    private final OrderProductRepository orderProductRepository;
    private final MakeService makeService;

    @Override
    public ApiResult<List<OrderWebDTO>> getAll(String chatId) {
        if (tgUserRepository.existsByChatId(chatId)) {
            List<Order> orders = orderRepository.findAllByUserChatIdOrderByCreatedAtDesc(chatId);
            List<OrderWebDTO> dtoList = universalMapper.toOrderWebDTOList(orders, chatId);
            return ApiResult.success(dtoList);
        } else {
            throw RestException.restThrow("USER ID NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResult<OrderWebDTO> getById(String chatId, Long id) {
        if (tgUserRepository.existsByChatId(chatId)) {
            Order order = orderRepository.findById(id).orElseThrow(
                    () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
            OrderWebDTO orderWebDTO = universalMapper.toOrderWebDTO(order, chatId);
            return ApiResult.success(orderWebDTO);
        } else {
            throw RestException.restThrow("USER ID NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList) throws TelegramApiException {
        if (tgUserRepository.existsByChatId(chatId)) {
            TgUser tgUser = tgUserRepository.findByChatId(chatId);
            List<OrderProducts> orderProducts = universalMapper.toOrderProductsEntityList(dtoList);
            List<OrderProducts> saveAll = orderProductRepository.saveAll(orderProducts);
            Basket basket = basketRepository.findByTgUserChatId(chatId);
            basketRepository.delete(basket);

            Order.OrderBuilder builder = Order.builder();
            builder.orderStatus(orderStatusRepository.findByName(OrderStatusName.PENDING));
            builder.user(tgUser);
            builder.orderProducts(saveAll);
            builder.price(universalMapper.totalPrice(saveAll));
            Order order = builder.build();
            orderRepository.save(order);
            if (tgUser.getName() != null && tgUser.getPhoneNumber() != null) {
                makeService.setUserStep(chatId, StepName.ORDER_LOCATION);
                tgService.execute(makeService.whenOrderLocation(chatId));
            } else if (tgUser.getName() == null) {
                makeService.setUserStep(chatId, StepName.ORDER_REGISTER_NAME);
                tgService.execute(makeService.whenOrderRegName(chatId));
            } else {
                makeService.setUserStep(chatId, StepName.ORDER_REGISTER_PHONE);
                tgService.execute(makeService.whenOrderRegPhone(chatId));
            }
            return ApiResult.success("SAVED SUCCESSFULLY");
        } else {
            throw RestException.restThrow("USER ID NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }
}
