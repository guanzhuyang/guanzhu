package com.design.yang.dto;

import java.util.List;

public class TestArea {
    private String name;
    private Long id;
    private List<TestArea> areas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestArea> getAreas() {
        return areas;
    }

    public void setAreas(List<TestArea> areas) {
        this.areas = areas;
    }
}
