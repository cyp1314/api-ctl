package com.chen.app.util;

import com.chen.app.apidata.IdcarInfo;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("resultcode", 200);
        put("reason", "成功的返回");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int resultcode, String reason) {
        R r = new R();
        r.put("resultcode", resultcode);
        r.put("reason", reason);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /*********************************************************************************************/
    public static R err_1001() { return error(1001, "错误的请求KEY"); }
    public static R err_1002() { return error(1002, "该KEY无请求权限"); }
    public static R err_1003() {
        return error(1003, "KEY过期");
    }
    public static R err_1004() {
        return error(1004, "应用未审核超时，请提交认证");
    }
    public static R err_1005() {
        return error(1005, "被禁止的IP");
    }
    public static R err_1006() {
        return error(1006, "被禁止的KEY");
    }
    public static R err_1007() { return error(1007, "请求超过次数或时间段限制"); }
    public static R err_1008() {
        return error(1008, "测试KEY超过请求限制");
    }
    public static R err_1009() {
        return error(1009, "接口维护");
    }
    public static R err_1010() {
        return error(1010, "接口停用");
    }
    public static R err_1011() {
        return error(1011, "当前IP请求超过限制");
    }
    public static R err_1012() {
        return error(1012, "当前QPS超过限制");
    }
    public static R err_1022() {
        return error(1022, "系统内部异常,请联系本系统管理员");
    }
    /*********************************************************************************************/
    public static R err_203801() { return error(203801, "请输入正确的15或18位身份证"); }
    public static R err_203802() { return error(203802, "错误的身份证或无结果"); }
    public static R err_203803() { return error(203803, "身份证校验位不正确"); }
    public static R err_203804() { return error(203804, "查询失败"); }

}

