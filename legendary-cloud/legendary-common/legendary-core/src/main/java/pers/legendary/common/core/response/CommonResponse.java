package pers.legendary.common.core.response;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: 参照Sa-Token中的CommonResponse
 * 对Ajax请求返回Json格式数据的简易封装 <br>
 * 所有预留字段：<br>
 * code=状态码 <br>
 * msg=描述信息 <br>
 * data=携带对象 <br>
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/3/17 23:56
 */
public class CommonResponse extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    public CommonResponse(int code, String msg, Object data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    /**
     * 获取code
     *
     * @return code
     */
    public Integer getCode() {
        return (Integer) this.get("code");
    }

    /**
     * 获取msg
     *
     * @return msg
     */
    public String getMsg() {
        return (String) this.get("msg");
    }

    /**
     * 获取data
     *
     * @return data
     */
    public Object getData() {
        return this.get("data");
    }

    /**
     * 给code赋值，连缀风格
     *
     * @param code code
     * @return 对象自身
     */
    public CommonResponse setCode(int code) {
        this.put("code", code);
        return this;
    }

    /**
     * 给msg赋值，连缀风格
     *
     * @param msg msg
     * @return 对象自身
     */
    public CommonResponse setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    /**
     * 给data赋值，连缀风格
     *
     * @param data data
     * @return 对象自身
     */
    public CommonResponse setData(Object data) {
        this.put("data", data);
        return this;
    }

    /**
     * 写入一个值 自定义key, 连缀风格
     *
     * @param key  key
     * @param data data
     * @return 对象自身
     */
    public CommonResponse set(String key, Object data) {
        this.put(key, data);
        return this;
    }

    /**
     * 写入一个Map, 连缀风格
     *
     * @param map map
     * @return 对象自身
     */
    public CommonResponse setMap(Map<String, ?> map) {
        for (String key : map.keySet()) {
            this.put(key, map.get(key));
        }
        return this;
    }


    // ============================  构建  ================================== 

    /**
     * 构建成功
     */
    public static CommonResponse ok() {
        return new CommonResponse(CODE_SUCCESS, "ok", null);
    }

    public static CommonResponse ok(String msg) {
        return new CommonResponse(CODE_SUCCESS, msg, null);
    }

    public static CommonResponse code(int code) {
        return new CommonResponse(code, null, null);
    }

    public static CommonResponse data(Object data) {
        return new CommonResponse(CODE_SUCCESS, "ok", data);
    }

    /**
     * 构建失败
     */
    public static CommonResponse error() {
        return new CommonResponse(CODE_ERROR, "error", null);
    }

    public static CommonResponse error(String msg) {
        return new CommonResponse(CODE_ERROR, msg, null);
    }

    public static CommonResponse error(int code, String msg) {
        return new CommonResponse(code, msg, null);
    }

    /**
     * 构建指定状态码
     */
    public static CommonResponse get(int code, String msg, Object data) {
        return new CommonResponse(code, msg, data);
    }


    @Override
    public String toString() {
        return "{"
                + "\"code\": " + this.getCode()
                + ", \"msg\": \"" + this.getMsg() + "\""
                + ", \"data\": " + this.getData() + ""
                + "}";
    }
}

