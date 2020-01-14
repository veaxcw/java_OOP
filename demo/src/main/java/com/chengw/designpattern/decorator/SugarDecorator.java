package com.chengw.designpattern.decorator;

/**
 * 具体的功能的实现
 * @author chengwei
 */
public class SugarDecorator extends BaseDrinkDecorator {


    public SugarDecorator(WaterComponent water) {
        super(water);
    }

    @Override
    public void operation() {
        water.operation();
        System.out.println(".sugar");
    }
}
