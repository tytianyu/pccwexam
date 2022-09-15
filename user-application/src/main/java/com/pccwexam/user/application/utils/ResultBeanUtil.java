package com.pccwexam.user.application.utils;

import com.pccwexam.user.api.dto.ResultBean;
import com.pccwexam.user.api.enums.ResultStatusEnum;

import java.util.List;

public class ResultBeanUtil {

    public static <T> ResultBean<T> fail(String status, String msg) {
        return createResultBean(status, msg, null);
    }

    public static <T> ResultBean<T> fail() {
        return fail("操作失败！");
    }

    public static ResultBean fail(String msg) {
        return createResultBean(ResultStatusEnum.ERROR.getStatus(), msg, null);
    }

    public static ResultBean<?> fail(String msg, Object data) {
        return createResultBean(ResultStatusEnum.ERROR.getStatus(), msg, data);
    }

    public static <T> ResultBean<T> fail(T data) {
        return createResultBean(ResultStatusEnum.ERROR.getStatus(), ResultStatusEnum.ERROR.getMessage(), data);
    }

    public static ResultBean<?> success() {
        return success("操作成功");
    }

    public static ResultBean<?> success(String msg) {
        return success(msg, null);
    }

    public static ResultBean<?> success(String msg, Object data) {
        return createResultBean(ResultStatusEnum.SUCCESS.getStatus(), msg, data);
    }

    public static <T> ResultBean<T> success(T data) {
        return result(ResultStatusEnum.SUCCESS,data);
    }

    public static <T> ResultBean<List<T>> successList(List<T> dataList) {
        return result(ResultStatusEnum.SUCCESS,dataList);
    }

    public static ResultBean<?> error404() {
        return createResultBean("404", "请求资源不存在", null);
    }

    public static ResultBean<?> error500() {
        return createResultBean("500", "服务器内部错误！", null);
    }

    public static <T> ResultBean<T> result(String status, String msg, T data) {
        return createResultBean(status, msg, data);
    }

    public static ResultBean result(String status, String msg) {
        return createResultBean(status, msg, null);
    }

    public static ResultBean result(ResultStatusEnum resultStatusEnum) {
        return createResultBean(resultStatusEnum.getStatus(), resultStatusEnum.getMessage(), null);
    }

    public static <T> ResultBean<T> result(ResultStatusEnum resultStatusEnum, T data) {
        return createResultBean(resultStatusEnum.getStatus(), resultStatusEnum.getMessage(), data);
    }

    public static <T> ResultBean<List<T>> result(ResultStatusEnum resultStatusEnum, List<T> dataList) {
        return (ResultBean<List<T>>) createResultBean(resultStatusEnum.getStatus(), resultStatusEnum.getMessage(), dataList);
    }

    public static Boolean isSuccess(ResultBean<?> data) {
        if (data == null) {
            return Boolean.FALSE;
        } else {
            return ResultStatusEnum.SUCCESS.getStatus().equals(data.getStatus()) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static <T> ResultBean<T> createResultBean(String status, String msg, T data) {
        ResultBean<T> resultBean = new ResultBean();
        resultBean.setStatus(status);
        resultBean.setMessage(msg);
        resultBean.setData(data);
        return resultBean;
    }

    private static <T> ResultBean<T> createResultBean(String status, String msg, List<T> dataList) {
        ResultBean<T> resultBean = new ResultBean();
        resultBean.setStatus(status);
        resultBean.setMessage(msg);
        resultBean.setDataList(dataList);
        return resultBean;
    }

}
