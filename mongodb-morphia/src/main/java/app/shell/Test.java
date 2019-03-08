package app.shell;

import java.util.Date;
import app.model.entity.ProductEntity;
import app.model.repository.ProductRepository;
import app.model.service.ValidatorService;

public class Test {

    public static void main(String[] args) {

        ProductRepository repository = new ProductRepository();
        ProductEntity product = new ProductEntity();

        product.setName("Test 1");
        product.setLink("http://localhost/p/1");
        product.setImage("http://localhost/images/1.png");
        product.setPrice(9.90);

        ValidatorService<ProductEntity> validator = new ValidatorService();

        validator.process(product);

        if (validator.hasError()) {
            System.out.println(validator.getError());
            return;
        }

        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        if (repository.persist(product)) {
            System.out.println("insertedId: " + product.getId());
        }
    }
}
