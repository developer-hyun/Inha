package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetMainNation {
    private List<GetMainPage> movie;
    private List<GetMainPage> nation;
}
