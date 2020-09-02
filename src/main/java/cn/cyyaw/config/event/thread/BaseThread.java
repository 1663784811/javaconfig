package cn.cyyaw.config.event.thread;

/**
 * 基础线程
 */
public interface  BaseThread extends Runnable{


    /**
     * 添加到线程池
     */
    void addPool();

}
