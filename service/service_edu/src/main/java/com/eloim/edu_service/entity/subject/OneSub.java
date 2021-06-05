package com.eloim.edu_service.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSub {
    private String id;
    private String title;
    private List <TwoSub> children=new ArrayList<>();
}
