package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussershoushangpinEntity;
import com.entity.view.DiscussershoushangpinView;

import com.service.DiscussershoushangpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 二手汽车评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-23 10:15:17
 */
@RestController
@RequestMapping("/discussershoushangpin")
public class DiscussershoushangpinController {
    @Autowired
    private DiscussershoushangpinService discussershoushangpinService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussershoushangpinEntity discussershoushangpin,
		HttpServletRequest request){
        EntityWrapper<DiscussershoushangpinEntity> ew = new EntityWrapper<DiscussershoushangpinEntity>();

		PageUtils page = discussershoushangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussershoushangpin), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussershoushangpinEntity discussershoushangpin, 
		HttpServletRequest request){
        EntityWrapper<DiscussershoushangpinEntity> ew = new EntityWrapper<DiscussershoushangpinEntity>();

		PageUtils page = discussershoushangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussershoushangpin), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussershoushangpinEntity discussershoushangpin){
       	EntityWrapper<DiscussershoushangpinEntity> ew = new EntityWrapper<DiscussershoushangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussershoushangpin, "discussershoushangpin")); 
        return R.ok().put("data", discussershoushangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussershoushangpinEntity discussershoushangpin){
        EntityWrapper< DiscussershoushangpinEntity> ew = new EntityWrapper< DiscussershoushangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussershoushangpin, "discussershoushangpin")); 
		DiscussershoushangpinView discussershoushangpinView =  discussershoushangpinService.selectView(ew);
		return R.ok("查询二手汽车评论表成功").put("data", discussershoushangpinView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussershoushangpinEntity discussershoushangpin = discussershoushangpinService.selectById(id);
        return R.ok().put("data", discussershoushangpin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussershoushangpinEntity discussershoushangpin = discussershoushangpinService.selectById(id);
        return R.ok().put("data", discussershoushangpin);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussershoushangpinEntity discussershoushangpin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussershoushangpin);
        discussershoushangpinService.insert(discussershoushangpin);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussershoushangpinEntity discussershoushangpin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussershoushangpin);
        discussershoushangpinService.insert(discussershoushangpin);
        return R.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussershoushangpinEntity discussershoushangpin = discussershoushangpinService.selectOne(new EntityWrapper<DiscussershoushangpinEntity>().eq("", username));
        return R.ok().put("data", discussershoushangpin);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussershoushangpinEntity discussershoushangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussershoushangpin);
        discussershoushangpinService.updateById(discussershoushangpin);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussershoushangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussershoushangpinEntity discussershoushangpin, HttpServletRequest request,String pre){
        EntityWrapper<DiscussershoushangpinEntity> ew = new EntityWrapper<DiscussershoushangpinEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussershoushangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussershoushangpin), params), params));
        return R.ok().put("data", page);
    }










}
