package com.example.upproject.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    public String name;
    public int id;
    public Position position;
}
