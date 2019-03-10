package app.model.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractRepository<T, ID> {

    final private EntityManagerFactory factory;
    final protected EntityManager manager;
    final private Class<T> entityClass;

    public AbstractRepository(String persistenceUnit, Class<T> entityClass) {
        factory = Persistence.createEntityManagerFactory(persistenceUnit);
        manager = factory.createEntityManager();
        this.entityClass = entityClass;
    }

    final public List<T> findAll() {
        String query = "from " + entityClass.getName() + " e";
        return manager.createQuery(query, entityClass).getResultList();
    }

    final public T findById(ID id) {
        return manager.find(entityClass, id);
    }

    final public boolean persist(T entity) {
        manager.getTransaction().begin();
        try {
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }

    final public boolean remove(T entity) {
        manager.getTransaction().begin();
        try {
            manager.remove(entity);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }

    final public void close() {
        manager.close();
        factory.close();
    }
}
