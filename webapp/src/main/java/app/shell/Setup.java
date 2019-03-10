package app.shell;

import java.util.Date;
import app.model.entity.UserEntity;
import app.model.repository.UserRepository;

public class Setup {

    public static void main(String[] args) {

        UserEntity user = new UserEntity();

        user.setName("Admin");
        user.setLogin("admin");
        user.setPassword("123qwe");
        user.setRoles("ADMIN");
        user.setStatus("A");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        UserRepository repository = new UserRepository();

        repository.persist(user);

        System.out.println("user: admin");
        System.out.println("password: 123qwe");
        
        repository.close();

    }
}
