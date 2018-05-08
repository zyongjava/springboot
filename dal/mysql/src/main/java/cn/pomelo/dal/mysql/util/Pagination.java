package cn.pomelo.dal.mysql.util;

import java.util.List;

/**
 * 分页类<br/>
 */
public class Pagination<Q, T> {

    /**
     * 默认分页页码
     */
    public static final Integer DEFAULT_PAGE_INDEX = 1;
    /**
     * 默认每页数量
     */
    public static final int     DEFAULT_PAGE_SIZE  = 10;
    /**
     * 页码
     */
    private int                 pageIndex          = DEFAULT_PAGE_INDEX;
    /**
     * 每页数量
     */
    private int                 pageSize           = DEFAULT_PAGE_SIZE;
    /**
     * 总记录条数
     */
    private int                 totalCount         = 0;
    /**
     * 查询query参数
     */
    private Q                   query;
    /**
     * 查询结果
     */
    private List<T>             result;
    /**
     * query结果
     */
    private boolean             success;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = (null == pageIndex || pageIndex < DEFAULT_PAGE_INDEX ? DEFAULT_PAGE_INDEX : pageIndex);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = (pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = (totalCount < 0 ? 0 : totalCount);
    }

    public Q getQuery() {
        return query;
    }

    public void setQuery(Q query) {
        this.query = query;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * 获得当前分页对象的总页数
     *
     * @return
     */
    public int getPageCount() {
        if (totalCount <= 0) {
            return 0;
        }
        int pageNum = totalCount / pageSize;
        // 如果不能整除，页数加1
        if (totalCount % pageSize != 0) {
            pageNum++;
        }
        return pageNum;
    }

    /**
     * 获取记录offset
     * 
     * @return offset
     */
    public int getOffset() {
        int offset = (getPageIndex() - 1) * getPageSize();
        return offset < 0 ? 0 : offset;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
