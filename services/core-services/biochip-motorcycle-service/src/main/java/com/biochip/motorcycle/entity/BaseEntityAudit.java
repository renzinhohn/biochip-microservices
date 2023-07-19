package com.biochip.motorcycle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "createdBy")
	private String createdBy;

    @CreationTimestamp
    @Column(name = "dateCreated", updatable = false)
    private Date dateCreated;

    @Column(name = "lastUpdatedBy")
    private String lastUpdatedBy;
    
    @UpdateTimestamp
    @Column(name = "dateLastUpdated")
    private Date dateLastUpdated;
    
    @Version
    @Column
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntityAudit)) return false;
        if (!super.equals(o)) return false;
        BaseEntityAudit that = (BaseEntityAudit) o;
        return createdBy.equals(that.createdBy) &&
        		dateCreated.equals(that.dateCreated) &&
        		lastUpdatedBy.equals(that.lastUpdatedBy) &&
        		dateLastUpdated.equals(that.dateLastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
				 createdBy, dateCreated, lastUpdatedBy, dateLastUpdated);
    }

    @Override
    public String toString() {
        return "BaseEntityAudit{" +
                "createdBy='" + createdBy + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", dateLastUpdated=" + dateLastUpdated +
                "}\n" +
                super.toString();
    }
    
}