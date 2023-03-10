package com.costardstudio.uponchart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "uc_authority")
public class AuthorityEntity implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthorityEntity)) {
            return false;
        }
        return Objects.equals(name, ((AuthorityEntity) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
    // prettier-ignore

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "name='" + name + '\'' +
                "}";
    }
}
