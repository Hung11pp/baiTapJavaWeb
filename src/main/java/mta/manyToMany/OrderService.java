package mta.manyToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public List<Order> listAll() {
        return repo.findAll();
    }

    public Order save(Order order) {
        repo.save(order);
        return order;
    }

    public Order get(long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
    @Autowired
    private OrderRepository orderRepository;
    public Order update(Order order) {
        order.setTotalPrice();
        return orderRepository.save(order);
    }

}
