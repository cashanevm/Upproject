package com.example.upproject.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

    @Entity

    @Table(name = "Roles")
    public class Role  implements Serializable {
        @Id
        @GeneratedValue( strategy = GenerationType.AUTO )
        private Long role_id;
        private String title;
        @ManyToMany(mappedBy = "roles")
        private Set<User> users = new HashSet<>();

        public Role(String title) {
            this.title = title;
        }

        public Role() {

        }

        public Role(Long role_id, String title, Set<User> users) {
            this.role_id = role_id;
            this.title = title;
            this.users = users;
        }

        public Long getRole_id() {
            return role_id;
        }

        public void setRole_id(Long role_id) {
            this.role_id = role_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Set<User> getUsers() {
            return users;
        }

        public void setUsers(Set<User> users) {
            this.users = users;
        }
    }

