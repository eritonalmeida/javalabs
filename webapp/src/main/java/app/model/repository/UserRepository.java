package app.model.repository;

import app.model.entity.UserEntity;
import javax.persistence.TypedQuery;

public class UserRepository extends AbstractRepository<UserEntity, Integer> {

    private static final String persistenceUnit = "default";

    public UserRepository() {
        super(persistenceUnit, UserEntity.class);
    }

    public UserEntity findByLogin(String login) {

        TypedQuery<UserEntity> query;

        query = manager.createQuery("from UserEntity e where e.login = :login", UserEntity.class);
        query.setParameter("login", login);

        UserEntity user = null;

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }

        return user;
    }
}
