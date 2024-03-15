package uz.mediasolutions.mdeliveryservice.service.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Order;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.mapper.UniversalMapper;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderWebDTO;
import uz.mediasolutions.mdeliveryservice.repository.OrderRepository;
import uz.mediasolutions.mdeliveryservice.repository.TgUserRepository;
import uz.mediasolutions.mdeliveryservice.service.web.abs.WebOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebOrderServiceImpl implements WebOrderService {

    private final OrderRepository orderRepository;
    private final TgUserRepository tgUserRepository;
    private final UniversalMapper universalMapper;

    @Override
    public ApiResult<List<OrderWebDTO>> getAll(String chatId) {
        List<Order> orders = orderRepository.findAllByUserChatIdOrderByCreatedAtDesc(chatId);
        List<OrderWebDTO> dtoList = universalMapper.toOrderWebDTOList(orders, chatId);
        return ApiResult.success(dtoList);
    }

    @Override
    public ApiResult<OrderWebDTO> getById(String chatId, Long id) {
        return null;
    }

    @Override
    public ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList) {
        return null;
    }
}
