package com.rahulshettyacademy.controller;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Greeting {

    private long id;
    private String content;


}
