package nginx.handler.com;


import java.util.Map;
import nginx.clojure.java.Constants;
import nginx.clojure.java.StringFacedJavaBodyFilter;
import org.redisson.api.RMap;

public class BodyFilter extends StringFacedJavaBodyFilter {
    @Override
    protected Object[] doFilter(Map<String, Object> request, String body, boolean isLast) {
        RMap<String, String> rMap = CacheManager.get().getCache();
        String key = (String) request.get(Constants.URI);
        rMap.putIfAbsent(key, body);
        return new Object[]{200, null, body};
    }
}
