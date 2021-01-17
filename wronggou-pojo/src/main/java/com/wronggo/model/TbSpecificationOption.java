package com.wronggo.model;

import lombok.Data;

@Data
public class TbSpecificationOption {
    private Long id;

    private String optionName;

    private Long specId;

    private Integer orders;


}