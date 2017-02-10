package com.springapp.mvc.form;

import javax.validation.constraints.Min;

/**
 * Created by kmchu on 16/5/13.
 */
public class StoreForm {
    @Min(value=1)
    private Integer id;

    private String name;

    private String bu_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBu_id(String buId){
        this.bu_id = buId;
    }

    public String getBuId(){
        return this.bu_id;
    }
}
