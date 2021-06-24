package nginx.handler.com;

import static java.util.jar.Attributes.Name.CONTENT_TYPE;
import static nginx.clojure.MiniConstants.NGX_HTTP_OK;

import java.util.Map;
import nginx.clojure.NginxClojureRT;
import nginx.clojure.java.ArrayMap;
import nginx.clojure.java.Constants;
import nginx.clojure.java.NginxJavaRingHandler;
import org.redisson.api.RMap;

public class NgingRewriteHandler implements NginxJavaRingHandler {

    /**
     * handler for incoming requests
     * @param request params from request
     */
    @Override
    public Object[] invoke(Map<String, Object> request) {
        RMap<String, String> rMap = CacheManager.get().getCache();
        String key = (String) request.get(Constants.URI);
        String response = rMap.get(key);
        if (response != null) {
            NginxClojureRT.log.debug(String.format("Get cache! Key : %s value %s", key, response));
            return new Object[]{
                    NGX_HTTP_OK,
                    ArrayMap.create(CONTENT_TYPE, "text/xml"), //headers map
                    response  //response body can be string, File or Array/Collection of string or File
            };
        }
        return Constants.PHASE_DONE;
    }
}
