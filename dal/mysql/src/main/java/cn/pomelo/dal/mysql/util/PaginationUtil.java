package cn.pomelo.dal.mysql.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Pagination util<br/>
 */
public class PaginationUtil {

    /**
     * 查询信息mapper以 Pagination 结束
     */
    public static final String  QUERY_LIST_SUFFIX  = "Pagination";
    /**
     * 查询总数mapper以 Count 结束
     */
    public static final String  QUERY_COUNT_SUFFIX = "Count";

    private static final Logger logger             = LoggerFactory.getLogger(PaginationUtil.class);

    /**
     * 分页查询
     * 
     * @param dao Dao interface
     * @param pagination Pagination
     * @param queryPrefix query前缀
     * @param <Q> Query对象
     * @param <T> 返回List类型
     */
    public static <Q, T> void queryPagination(Object dao, Pagination<Q, T> pagination, String queryPrefix) {
        try {
            Method declaredMethod = dao.getClass().getDeclaredMethod(queryPrefix + QUERY_LIST_SUFFIX, Pagination.class);
            @SuppressWarnings("unchecked")
            List<T> result = (List<T>) declaredMethod.invoke(dao, pagination);
            Method declaredMethod2 = dao.getClass().getDeclaredMethod(queryPrefix + QUERY_COUNT_SUFFIX,
                                                                      Pagination.class);
            int total = (int) declaredMethod2.invoke(dao, pagination);
            pagination.setResult(result);
            pagination.setTotalCount(total);
            pagination.setSuccess(true);
        } catch (Exception e) {
            logger.error("pagination error", e);
        }
    }

}
