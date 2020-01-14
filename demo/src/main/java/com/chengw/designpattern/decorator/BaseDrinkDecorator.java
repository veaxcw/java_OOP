package com.chengw.designpattern.decorator;

import lombok.Data;

/**
 * 装饰者
 * @author chengwei
 */
@Data
public abstract class BaseDrinkDecorator implements DrinkComponent {

    WaterComponent water;

    public BaseDrinkDecorator(WaterComponent water) {
        this.water = water;
    }
}
