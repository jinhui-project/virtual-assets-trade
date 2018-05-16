package com.jinhui.common.utils;

import com.github.pagehelper.PageInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class ListPageUtil<T> {
    /**
     * 原集合
     */
    private List<T> data;

    /**
     * 上一页
     */
    private int lastPage;

    /**
     * 当前页
     */
    private int nowPage;

    /**
     * 下一页
     */
    private int nextPage;
//
    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数据条数
     */
    private int totalCount;

    public ListPageUtil(List<T> data, int nowPage, int pageSize) {
        if (data == null || data.isEmpty()) {
          data=Collections.emptyList();
        }

        this.data = data;
        this.pageSize = pageSize;

        this.nowPage = nowPage;
        this.totalCount = data.size();

        int m = totalCount % pageSize;
        if (m > 0) {
            totalPage = totalCount / pageSize + 1;
        } else {
            totalPage = totalCount / pageSize;
        }

        if (nowPage == 1) {
            lastPage = 0;
        } else {
            lastPage = nowPage - 1;
        }

        if (nowPage == totalPage) {
            nextPage = 0;
        } else {
            nextPage = nowPage + 1;
        }

    }

    /**
     * 得到分页后的数据
     *
     * @param pageNum 页码
     * @return 分页后结果
     */
    public List<T> getPagedList() {
        int fromIndex = (nowPage - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return Collections.emptyList();//空数组
        }
        if (fromIndex < 0) {
            return Collections.emptyList();//空数组
        }
        int toIndex = nowPage * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }


    @Override
    public String toString() {
        return "ListPageUtil{" +
                "data=" + data +
                ", lastPage=" + lastPage +
                ", nowPage=" + nowPage +
                ", nextPage=" + nextPage +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                '}';
    }


    public PageInfo toPageInfo() {
        PageInfo pageInfo = new PageInfo();
        List<T> pagedList = getPagedList();
        pageInfo.setList(pagedList);
        pageInfo.setPageNum(nowPage);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSize(pagedList.size());
        pageInfo.setPages(totalPage);
        pageInfo.setTotal(totalCount);
        return pageInfo;


    }


    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        List<Integer> list = Arrays.asList(array);

        ListPageUtil<Integer> listPageUtil = new ListPageUtil<>(list, 1, 10);

        System.out.println(listPageUtil);
        List<Integer> pagedList = listPageUtil.getPagedList();
        System.out.println(pagedList);
        System.out.println(listPageUtil.toPageInfo());
    }
}

