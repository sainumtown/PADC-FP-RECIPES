package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class InstructionVO {
    @SerializedName("instruction-desc")
    private String instruction_desc;

    @SerializedName("instruction-image")
    private String instruction_image;

    @SerializedName("sort-order")
    private int sort_order;

    public String getInstruction_desc() {
        return instruction_desc;
    }

    public String getInstruction_image() {
        return instruction_image;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setInstruction_desc(String instruction_desc) {
        this.instruction_desc = instruction_desc;
    }

    public void setInstruction_image(String instruction_image) {
        this.instruction_image = instruction_image;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }
}
