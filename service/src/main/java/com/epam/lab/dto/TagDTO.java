package com.epam.lab.dto;

import com.epam.lab.validation.NewEntity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TagDTO {

    private long id;
    @Size(max = 30, message = "name must not be longer than 30 characters")
    @NotEmpty(groups = NewEntity.class, message = "name must not be empty")
    private String name;

    public TagDTO() {
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public TagDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagDTO tagDTO = (TagDTO) o;

        if (id != tagDTO.id) return false;
        return name != null ? name.equals(tagDTO.name) : tagDTO.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
