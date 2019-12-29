
package javax.servlet;

import java.util.Enumeration;

/**
 * servlet 容器使用的配置信息，用来在初始化时传递配置信息
 */
public interface ServletConfig {

    /**
     * @return	the name of the servlet instance
     */
    public String getServletName();


    /**
     * Returns a reference to the {@link ServletContext} in which the caller
     * is executing.
     *
     * @return	a {@link ServletContext} object, used
     * by the caller to interact with its servlet container
     *
     * @see ServletContext
     */
    public ServletContext getServletContext();


    /**
     *
     * @return a <code>String</code> containing the value
     * of the initialization parameter, or <code>null</code> if
     * the initialization parameter does not exist
     */
    public String getInitParameter(String name);


    /**
     * @return an <code>Enumeration</code> of <code>String</code>
     * objects containing the names of the servlet's
     * initialization parameters
     */
    public Enumeration<String> getInitParameterNames();

}
