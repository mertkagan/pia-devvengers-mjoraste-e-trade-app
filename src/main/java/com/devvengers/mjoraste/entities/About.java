package com.devvengers.mjoraste.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "about")
@AllArgsConstructor
@Data
public class About {

    @Id
    private String id;

    private String aboutText;

}
