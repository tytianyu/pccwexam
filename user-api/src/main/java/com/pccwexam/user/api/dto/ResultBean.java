package com.pccwexam.user.api.dto;

import com.pccwexam.user.api.enums.ResultStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "返回结果集")
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = -7602280271453240278L;

    public static ThreadLocal<String> STATUS = new ThreadLocal<String>();
    public static ThreadLocal<String> MESSAGE = new ThreadLocal<String>();

    @ApiModelProperty(value = "返回提示", allowEmptyValue = true)
    private String message = ResultStatusEnum.SUCCESS.getMessage();

    @ApiModelProperty(value = "返回状态码 1:成功状态 0:失败", allowEmptyValue = true)
    private String status = ResultStatusEnum.SUCCESS.getStatus();

    /**
     * 业务流水号，请求和返回一致表示是同一个请求
     */
    @ApiModelProperty(value = "业务流水号，请求和返回一致表示是同一个请求", allowEmptyValue = true)
    private String traceId;

    @ApiModelProperty(value = "返回包装类", allowEmptyValue = true)
    private T data;

    @ApiModelProperty(value = "返回包装类list", allowEmptyValue = true)
    private List<T> dataList;

    /**
     * 请求返回时间
     */
    @ApiModelProperty(value = "请求返回时间", allowEmptyValue = true)
    private Long timestamp = System.currentTimeMillis();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        ResultBean.MESSAGE.set(message);
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        ResultBean.STATUS.set(status);
        this.status = status;
    }

    /**
     * 失败 status = 0
     *
     * @return boolean
     */
    public boolean isFailed() {
        return this.status.equals(ResultStatusEnum.ERROR.getStatus());
    }

    public void setFailed(Boolean b) {
    }

    /**
     * 不成功 status != 1
     *
     * @return boolean
     */
    public boolean isNotSuccess() {
        return !status.equals(ResultStatusEnum.SUCCESS.getStatus());
    }

    public void setNotSuccess(Boolean b) {
    }

    @Override
    public String toString() {
        return "ResultData [message=" + message + ", status=" + status + ", traceId=" + traceId + ", data=" + data
                + ", timestamp=" + timestamp + "]";
    }

    public ResultBean() {
    }

    public static void remove() {
        STATUS.remove();
        MESSAGE.remove();
    }
}
