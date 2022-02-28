package connector;

import model.Product;

import java.util.List;

public interface IProductConnector {
    void save(Product product);

    Product get(String id);

    Product getByName(String name);

    List<Product> getAll();


    void delete(String id);

    void update(Product product);
}
