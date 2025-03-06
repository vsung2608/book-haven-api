package com.efashion.order_service.service;

import com.efashion.order_service.dto.request.OrderLineRequest;
import com.efashion.order_service.mapper.OrderLineMapper;
import com.efashion.order_service.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public void saveOrderLines(List<OrderLineRequest> requests){
        var list = requests.stream()
                .map(orderLineMapper::toOrderLine)
                .toList();

        orderLineRepository.saveAll(list);
    }
}
