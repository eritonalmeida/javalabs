package app.model.repository;

import app.model.entity.ProductEntity;

public class ProductRepository extends AbstractRepository<ProductEntity> {

    private static final String dbStore = "morphia_test";
    
    public ProductRepository() {
        super(dbStore, ProductEntity.class);
    }
}
