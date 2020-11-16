package com.uber.uberapi.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//dont' craete table for auditable these properties
// are inherited in my child classes
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable implements Serializable {

    @Id //primary key
    //generation type
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP) // provided by JPA
    @LastModifiedDate // provided by hibernate
    private Date updatedAt;

    @Override
    public int hashCode() {
        return id == null ? 0: id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true; //same memory address
        if(!this.getClass().equals(obj.getClass())) return false;
        if(obj instanceof Auditable) {
            Auditable auditable = (Auditable)obj;
            if (id == null && obj== null) return true;
            if(id == null || obj == null) return false;
            return id == ((Auditable)obj).id;
        }else{
            return super.equals(obj);
        }
    }
}
