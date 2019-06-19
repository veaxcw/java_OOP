package com.chengw.thread.cooperation.countDownLatch;

import com.chengw.thread.utils.Tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author veax
 */
public class SampleServiceA extends AbstractService {
    @Override
    protected void doStart() throws Exception {
        // 模拟实际操作耗时
        Tools.randomPause(10);

// 省略其他代码
    }

    public SampleServiceA(CountDownLatch latch) {
        super(latch);
    }

    @Override
    public int hashCode() {
        return super.hashCode()^new Random().nextInt(1000);
    }
}
