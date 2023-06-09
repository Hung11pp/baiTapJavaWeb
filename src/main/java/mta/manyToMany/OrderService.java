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
    @Autowired
    private ProductRepository productRepository;
    public Order addProduct(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (order != null && product != null) {
            order.getProducts().add(product);
            order.setTotalPrice();
            return orderRepository.save(order);
        }

        return null;
    }
    public Order removeProduct(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (order != null && product != null) {
            order.getProducts().remove(product);
            order.setTotalPrice();
            return orderRepository.save(order);
        }

        return null;
    }



}
