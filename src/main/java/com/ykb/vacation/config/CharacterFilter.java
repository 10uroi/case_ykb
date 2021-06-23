package tr.gov.btk.mobilkapsamaalani.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class CharacterFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(CharacterFilter.class);

    public void init(FilterConfig fConfig) {
        logger.info("CharacterFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    public void destroy() {
        logger.info("CharacterFilter destroy");
    }
}
