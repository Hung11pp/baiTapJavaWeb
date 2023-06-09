package mta.restAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mta.manyToMany.Order;
import mta.manyToMany.OrderService;
import mta.manyToMany.Product;
import mta.manyToMany.ProductService;
import mta.oneToMany.User;
import mta.oneToMany.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @CrossOrigin
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.listAll();
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.get(id);
    }

    @CrossOrigin
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    @CrossOrigin
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.get(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setId(id);

        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }


    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.listAll();
    }

    @CrossOrigin
    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.get(id);
    }

    @CrossOrigin
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        order.setTotalPrice();
        return orderService.save(order);
    }
    @CrossOrigin
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existingOrder = orderService.get(id);
        if (existingOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        order.setId(id);
        order.setTotalPrice();

        Order updatedOrder = orderService.update(order);
        return ResponseEntity.ok(updatedOrder);
    }


    @CrossOrigin
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.listAll();
    }

    @CrossOrigin
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.get(id);
    }

    @CrossOrigin
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
    @CrossOrigin
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.get(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        product.setId(id);

        Product updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @CrossOrigin
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/products/search")
    public List<Product> searchProductsByName(@RequestParam String name) {
        return productService.searchByName(name);
    }
    @CrossOrigin
    @PostMapping("/orders/{orderId}/products/{productId}")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Order existingOrder = orderService.get(orderId);
        if (existingOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = productService.get(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingOrder.addProduct(product);
        Order updatedOrder = orderService.update(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }
    @CrossOrigin
    @DeleteMapping("/orders/{orderId}/products/{productId}")
    public ResponseEntity<Order> removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Order existingOrder = orderService.get(orderId);
        if (existingOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = productService.get(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingOrder.removeProduct(product);
        Order updatedOrder = orderService.update(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }

}
