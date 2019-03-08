package app.model.repository;

import app.config.MongodbConfig;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import com.mongodb.WriteResult;

public abstract class AbstractRepository<T> {

    final protected Datastore datastore;
    final private Class<T> entityClass;

    public AbstractRepository(String dbStore, Class<T> entityClass) {
        this.datastore = MongodbConfig.getDatastore(dbStore);
        this.entityClass = entityClass;
    }

    final public List<T> findAll() {
        List<T> list = datastore.find(entityClass).asList();
        return list;
    }

    final public T findById(String id) {
        return datastore.find(entityClass)
                .filter("id", new ObjectId(id))
                .get();
    }

    final public boolean persist(T entity) {
        try {
            datastore.save(entity);
            return true;
        } catch (Exception e) {
        }

        return false;
    }

    final public boolean remove(T entity) {
        WriteResult result = datastore.delete(entity);
        return result.wasAcknowledged();
    }
}
