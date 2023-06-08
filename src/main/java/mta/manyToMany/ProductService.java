package mta.manyToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }

    public Product save(Product product) {
        repo.save(product);
        return product;
    }

    public Product get(long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Product update(Product product) {
        repo.save(product);
        return product;
    }
    @Autowired
    private ProductRepository productRepository;
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

}
