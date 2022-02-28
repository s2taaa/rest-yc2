package app;

import jwt.JWTFilter;
import rest.ProductService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ProductApp extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public ProductApp() {
        singletons.add(new JWTFilter());
        singletons.add(new ProductService());

    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }
}
