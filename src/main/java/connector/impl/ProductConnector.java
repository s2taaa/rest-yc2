package connector.impl;

import connector.BaseConnector;
import connector.IProductConnector;
import factory.ProductFactory;
import model.Product;
import system.mongo.core.IDynamicObject;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.List;

public class ProductConnector extends BaseConnector implements IProductConnector {
    private MongoObjectConnector getMongoObjectConnectorProduct() {

        return getMongoObjectConnector("product");
    }

    @Override
    public void save(Product product) {
        ProductFactory factory = new ProductFactory();
        IDynamicObject iDynamicObject = factory.createObject(product);
        getMongoObjectConnectorProduct().insert(iDynamicObject);

    }

    @Override
    public Product get(String id) {
        return (Product) getMongoObjectConnectorProduct().get(id, new ProductFactory());

    }

    @Override
    public Product getByName(String name) {
        return null;
    }

    @Override
    public List<Product> getAll(){return getMongoObjectConnectorProduct().listAll(new ProductFactory());
}

    @Override
    public void delete(String id) {
        getMongoObjectConnectorProduct().removeDocument(id);
    }

    @Override
    public void update(Product product) {
        ProductFactory factory = new ProductFactory();
        IDynamicObject iDynamicObject = factory.createObject(product);
        getMongoObjectConnectorProduct().update(iDynamicObject);
    }
}
