package com.bookhaven.order_service.service;

import com.bookhaven.common.event.OrderPlacedDetailEvent;
import com.bookhaven.common.event.ProductPurchase;
import com.bookhaven.order_service.dto.request.OrderLineRequest;
import com.bookhaven.order_service.dto.request.OrderRequest;
import com.bookhaven.common.dto.PurchaseRequest;
import com.bookhaven.order_service.dto.response.OrderResponse;
import com.bookhaven.common.dto.PurchaseResponse;
import com.bookhaven.order_service.exception.BusinessException;
import com.bookhaven.order_service.mapper.OrderMapper;
import com.bookhaven.order_service.repository.OrderRepository;
import com.bookhaven.order_service.repository.web_client.CartClient;
import com.bookhaven.order_service.repository.web_client.CustomerClient;
import com.bookhaven.order_service.repository.web_client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final ProductClient productClient;
    private final CustomerClient customerClient;
    private final CartClient cartClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException(
                        "Cannot place order:: No customer exists with this id %s".formatted(orderRequest.customerId()))
                );

        var purchaseResponse =  productClient.purchase(orderRequest.orderLines());
        boolean isValid =purchaseResponse.stream()
                .allMatch(PurchaseResponse::available);

        if (isValid) {
            var order = orderRepository.save(orderMapper.toOrder(orderRequest));
            List<OrderLineRequest> orderLineRequests = new ArrayList<>();
            for (PurchaseRequest purchaseRequest : orderRequest.orderLines()) {
                orderLineRequests.add(new OrderLineRequest(
                        order.getId(), purchaseRequest.quantity(), purchaseRequest.id()
                ));
            }
            orderLineService.saveOrderLines(orderLineRequests);

            OrderPlacedDetailEvent event = new OrderPlacedDetailEvent(
                    customer.email(),
                    customer.firstName() + customer.lastName(),
                    orderRequest.reference(),
                    purchaseResponse.stream()
                            .map(res -> new ProductPurchase(res.id(), res.name(), res.price(), res.quantity()))
                            .toList(),
                    order.getTotalPrice()
            );
            kafkaTemplate.send("orderTopics", event);
            cartClient.deleteItemsInCart(orderRequest.customerId());

            return orderMapper.toOrderResponse(order);
        }else{
            throw new BusinessException("Product is not in stock, please try again later");
        }
    }
}
