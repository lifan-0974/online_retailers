package com.wronggo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Specification implements Serializable {
    private TbSpecification specification;
    private List<TbSpecificationOption> specificationOptionList;


}
