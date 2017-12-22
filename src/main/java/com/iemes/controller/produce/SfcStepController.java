package com.iemes.controller.produce;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.LogFormMap;
import com.iemes.entity.SiteFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.exception.SystemException;
import com.iemes.mapper.LogMapper;
import com.iemes.mapper.base.BaseMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/produce/")
public class SfcStepController extends BaseController {
	
	@RequestMapping("sfc_step")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/produce/sfc_step";
	}

}