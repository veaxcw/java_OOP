package com.chengw.designpattern.decorator;

/**
 * 被装饰者
 * @author chengwei
 */
public class WaterComponent implements DrinkComponent {

    @Override
    public void operation() {
        System.out.println(" water with");
    }
}
