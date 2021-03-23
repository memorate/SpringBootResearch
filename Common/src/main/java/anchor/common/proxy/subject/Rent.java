package anchor.common.proxy.subject;

/**
 * 被代理的服务，代理模式的核心接口
 *
 * @author Anchor
 */
public interface Rent {
    /**
     * 出租房屋
     */
    void rentHouse();

    /**
     * 获取自己的代理
     * @return 代理类
     */
    Rent getProxy();
}
