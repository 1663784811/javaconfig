package cn.cyyaw.config.config.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginShiroFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("{\"success\":false,\"message\":\"请先登录\"}");
        return false;
    }
}
