package com.example.upproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "Project")
    public class Project {
        @Id
        @GeneratedValue( strategy = GenerationType.AUTO )
        private Long project_id;
        private String title;
        @ManyToMany(mappedBy = "projects")
        private Set<Employee> employees = new HashSet<>();

        public Project(String title) {
            this.title = title;
        }
    }

