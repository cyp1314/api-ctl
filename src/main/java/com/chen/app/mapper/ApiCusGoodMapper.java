package com.chen.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.app.entity.ApiCusGood;
import com.chen.app.entity.ApiSysGoods;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApiCusGoodMapper extends BaseMapper<ApiCusGood> {

    @Select("select app_key from api_cus_good")
    List<String> findAllKeys();

    @Update("update api_cus_good a set a.api_numbers = #{api_numbers} where a.app_key = #{key}")
    int updateApiNumers(String key,Integer api_numbers);
}
