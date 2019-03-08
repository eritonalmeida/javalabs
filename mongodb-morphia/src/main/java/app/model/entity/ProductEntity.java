package app.model.entity;

import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;

@Entity(value = "products", noClassnameStored = true)
public class ProductEntity {

    @Id
    private ObjectId id;

    @NotEmpty(message = "{product.name.empty}")
    private String name;

    @NotEmpty(message = "{product.link.empty}")
    private String link;

    @NotEmpty(message = "{product.image.empty}")
    private String image;

    @Min(value = 1, message = "{product.price.min}")
    private double price;

    private Date createdAt;

    private Date updatedAt;

    public String getId() {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
