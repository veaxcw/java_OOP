
import java.io.IOException;


/**
 *
 * 定义了所有servlet 都必须实现的方法

 */


public interface Servlet {

    /**
     * servlet 容器调用该方法来初始化
     */

    public void init(ServletConfig config) throws ServletException;



    /**
     *
     *获取Servlet 配置
     */
    public ServletConfig getServletConfig();



    /**
     * 具体的业务方法
     */
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException;



    /**
     * 获取servlet 信息
     *
     */
    public String getServletInfo();



    /**
     *
     * 销毁方法
     *
     */
    public void destroy();
}
