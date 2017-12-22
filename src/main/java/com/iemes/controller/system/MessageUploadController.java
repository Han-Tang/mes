package com.iemes.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.base.ConstantController;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.BomFormMap;
import com.iemes.entity.ItemBomFormMap;
import com.iemes.entity.ItemFormMap;
import com.iemes.entity.NcCodeFormMap;
import com.iemes.entity.NcCodeGroupFormMap;
import com.iemes.entity.NcCodeGroupRelFormMap;
import com.iemes.entity.OperationFormMap;
import com.iemes.entity.OperationPodFormMap;
import com.iemes.entity.OperationResourceFormMap;
import com.iemes.entity.PodButtonFormMap;
import com.iemes.entity.RoleFormMap;
import com.iemes.entity.RoutingFormMap;
import com.iemes.entity.RoutingStepFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.entity.UserGroupsFormMap;
import com.iemes.entity.WorkCenterFormMap;
import com.iemes.entity.WorkCenterRelationFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.mapper.RoleMapper;
import com.iemes.mapper.UserMapper;
import com.iemes.util.Common;
import com.iemes.util.PasswordHelper;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/message/")
public class MessageUploadController extends BaseController {

	@Inject
	private UserMapper userMapper;
	@Inject
	private RoleMapper roleMapper;
	@Inject
	private BaseExtMapper baseExtMapper;

	/**
	 * 学生信息导出弹出框
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("upload")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/messageUpload/upload";
	}

	/**
	 *  用户信息 上传
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("studentInfoUpload")
	public BaseResponse studentInfoUpload(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
		BaseResponse baseResponse = new BaseResponse();
		String filename = file.getOriginalFilename();
		try {
			getUserAndRoleByExcel(file.getInputStream(),filename);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		baseResponse.setMessage(ConstantController.SUCCESS);
		baseResponse.setResult(true);
		return baseResponse;
	}


	/**
	 * UserInfo数据
	 * 将excel数据传到数据库
	 * @param is
	 * @param fileName
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private void getUserAndRoleByExcel(InputStream is ,String fileName) throws InvalidFormatException, IOException{
		inputstreamtofile(is,fileName);
		InputStream fis = null;
		File file  = new File(fileName);

		fis = new FileInputStream(file); 
		Workbook book = WorkbookFactory.create(fis);

		//第一个sheet
		Sheet sheet1 = book.getSheetAt(0); //或者book.createSheet("Sheet"+number);
		int lastRowIndex1 = sheet1.getLastRowNum();    //最后一行 
		Row firstRow1 = sheet1.getRow(0);              // 读取首行
		int lastColumnIndex1 = firstRow1.getLastCellNum(); // 最后一列  
		if(lastRowIndex1 >= 1){
			for (int i = 1; i <= lastRowIndex1; i++) {
				Row row = sheet1.getRow(i);
				for (int j = 0; j < lastColumnIndex1; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String userName = row.getCell(j++).getStringCellValue();
					String accountName = row.getCell(j++).getStringCellValue();
					String password = row.getCell(j++).getStringCellValue();
					String credentialsSalt = row.getCell(j++).getStringCellValue();
					String description = row.getCell(j++).getStringCellValue();
					String locked = row.getCell(j++).getStringCellValue();
					String siteA =  row.getCell(j++).getStringCellValue();
					String roleName =  row.getCell(j++).getStringCellValue();
					UserFormMap user = new UserFormMap();
					user.set("userName", userName);
					user.set("accountName", accountName);
					user.set("password", password);
					user.set("credentialsSalt", credentialsSalt);
					user.set("description", description);
					user.set("locked", locked);
					user.set("site",siteA);
					PasswordHelper passwordHelper = new PasswordHelper();
					passwordHelper.encryptPassword(user);
					try {
						List<UserFormMap>  tempUserList  = userMapper.findUserPage(user);
						if(null == tempUserList || tempUserList.size() == 0){
							baseMapper.addEntity(user);
							List<RoleFormMap> roleList =  roleMapper.findByAttribute("name", roleName, RoleFormMap.class);
							Long roleId = Long.parseLong(roleList.get(0).get("id").toString());
							UserGroupsFormMap userGroupsFormMap = new UserGroupsFormMap();
							userGroupsFormMap.put("userId", user.get("id"));
							userGroupsFormMap.put("roleId", roleId);
							baseMapper.addEntity(userGroupsFormMap);
						};
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}


		return ;
	}


	/**
	 * 将输入流 转成临时文件
	 * @param ins
	 * @param fileName
	 */
	private static void inputstreamtofile(InputStream ins ,String fileName) {
		try {
			File file = new File(fileName);
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *  用户信息模板下载
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("userInfoDownLoad")
	public void userInfoDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("multipart/form-data");
		// 得到要下载的文件名
		String fileName = "userInfo.xlsx"; 
		// 文件保存在uploadFile目录下的子目录当中
		String fileSaveRootPath = request.getServletContext().getRealPath("/uploadFile");
		// 设置响应头，控制浏览器下载该文件
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 读取要下载的文件，保存到文件输入流
		File file = new File(fileSaveRootPath + "\\" + fileName);
		FileInputStream in = new FileInputStream(file);
		// 创建输出流
		OutputStream out = response.getOutputStream();
		// 创建缓冲区
		byte buffer[] = new byte[102400];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			// 输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		// 关闭文件输入流
		in.close();
		// 关闭输出流
		out.close();
	}


	@RequestMapping("mainUpload")
	public String mainlistUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/messageUpload/mainUpload";
	}

	/**
	 *  工厂主数据模板下载
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("masterDataDownLoad")
	public void masterDataDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("multipart/form-data");
		// 得到要下载的文件名
		String fileName = "masterData.xlsx"; 
		// 文件保存在uploadFile目录下的子目录当中
		String fileSaveRootPath = request.getServletContext().getRealPath("/uploadFile");
		// 设置响应头，控制浏览器下载该文件
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 读取要下载的文件，保存到文件输入流
		File file = new File(fileSaveRootPath + "\\" + fileName);
		FileInputStream in = new FileInputStream(file);
		// 创建输出流
		OutputStream out = response.getOutputStream();
		// 创建缓冲区
		byte buffer[] = new byte[102400];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			// 输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		// 关闭文件输入流
		in.close();
		// 关闭输出流
		out.close();
	}

	/**
	 *  工厂主数据导入
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("masterDataUpload")
	public BaseResponse masterDataUpload(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
		BaseResponse baseResponse = new BaseResponse();
		String filename = file.getOriginalFilename();
		try {
			getMasterDataByExcel(file.getInputStream(),filename);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		baseResponse.setMessage(ConstantController.SUCCESS);
		baseResponse.setResult(true);
		return baseResponse;
	}


	private void getMasterDataByExcel(InputStream is ,String fileName) throws Exception{
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		UserFormMap user = (UserFormMap)Common.findUserSession(request);
		String byUser = user.getStr("accountName");

		inputstreamtofile(is,fileName);
		InputStream fis = null;
		File file  = new File(fileName);

		fis = new FileInputStream(file); 
		Workbook book = WorkbookFactory.create(fis);

		//第一个sheet  工作中心
		Sheet workcenterSheet = book.getSheetAt(0); //或者book.createSheet("Sheet"+number);
		int lastRowIndex = workcenterSheet.getLastRowNum();    //最后一行 
		Row firstRow = workcenterSheet.getRow(0);              // 读取首行
		int lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = workcenterSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String workcenter_no = row.getCell(j++).getStringCellValue();
					String workcenter_name = row.getCell(j++).getStringCellValue();
					String workcenter_desc = row.getCell(j++).getStringCellValue();
					String workcenter_version = row.getCell(j++).getStringCellValue();
					String workcenter_class = row.getCell(j++).getStringCellValue();
					String workcenter_type = row.getCell(j++).getStringCellValue();
					Cell ce = row.getCell(j++);
					String workcenter_parent = ce == null ? null : ce.getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					if(workcenter_class.equals("车间")){
						workcenter_class = "workshop";
					}else{
						workcenter_class = "production_line";
					}

					if(workcenter_type.equals("装配")){
						workcenter_type = "assembly";
					}
					else if(workcenter_type.equals("调度")){
						workcenter_type = "schedule";
					}
					else if(workcenter_type.equals("装运")){
						workcenter_type = "ship";
					}
					else {
						workcenter_type = "shift";
					}
					if(state.equals("是")){
						state = "1";
					}else{
						state = "0";
					}
					WorkCenterFormMap workCenterFormMap = new WorkCenterFormMap();
					workCenterFormMap.put("workcenter_no", workcenter_no);
					workCenterFormMap.put("site", site);
					List<WorkCenterFormMap> tempWorkcenterList = baseMapper.findByNames(workCenterFormMap);
					if(CollectionUtils.isEmpty(tempWorkcenterList)){
						workCenterFormMap.put("workcenter_name", workcenter_name);
						workCenterFormMap.put("workcenter_description", workcenter_desc);
						workCenterFormMap.put("workcenter_version", workcenter_version);
						workCenterFormMap.put("workcenter_class", workcenter_class);
						workCenterFormMap.put("workcenter_type", workcenter_type);
						workCenterFormMap.put("state", state);
						workCenterFormMap.put("byUser", byUser);
						try {
							baseMapper.addEntity(workCenterFormMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(!StringUtils.isEmpty(workcenter_parent)){
							WorkCenterRelationFormMap  workCenterRelationFormMap = new WorkCenterRelationFormMap();
							workCenterRelationFormMap.put("workcenter", workcenter_parent);
							workCenterRelationFormMap.put("workcenter_child", workcenter_no);
							workCenterRelationFormMap.put("site", site);
							try {
								baseMapper.addEntity(workCenterRelationFormMap);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					}
				}
			}
		}

		//第二个 sheet 操作 
		//开始
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("pod_button_name", "开始");
		String  startId = baseExtMapper.findPodButtonIdByName(map);
		map.put("pod_button_name", "完成");
		String  finishId = baseExtMapper.findPodButtonIdByName(map);
		map.put("pod_button_name", "装配");
		String  assenbleId = baseExtMapper.findPodButtonIdByName(map);
		map.put("pod_button_name", "记录不良");
		String  ncId = baseExtMapper.findPodButtonIdByName(map);

		Sheet operationSheet = book.getSheetAt(1); //或者book.createSheet("Sheet"+number);
		lastRowIndex = operationSheet.getLastRowNum();    //最后一行 
		firstRow = operationSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = operationSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String operation_no = row.getCell(j++).getStringCellValue();
					String operation_description = row.getCell(j++).getStringCellValue();
					String operation_type = row.getCell(j++).getStringCellValue();
					String resource_type = row.getCell(j++).getStringCellValue();
					String default_resource = row.getCell(j++).getStringCellValue();
					String workcenter = row.getCell(j++).getStringCellValue();
					String podBtn = row.getCell(j++).getStringCellValue();
					if(operation_type.equals("正常")){
						operation_type = "common";
					}
					if(resource_type.equals("默认")){
						resource_type = "default";
					}
					OperationFormMap operationFormMap = new OperationFormMap();
					operationFormMap.put("operation_no", operation_no);
					operationFormMap.put("site", site);
					List<OperationFormMap> tempOperationList = baseMapper.findByNames(operationFormMap);
					if(CollectionUtils.isEmpty(tempOperationList)){
						operationFormMap.put("operation_description", operation_description);
						operationFormMap.put("operation_type", operation_type);
						operationFormMap.put("default_resource", default_resource);
						operationFormMap.put("resoucre_type", resource_type);
						operationFormMap.put("workcenter", workcenter);
						operationFormMap.put("state", "1");
						operationFormMap.put("byUser", byUser);

						baseMapper.addEntity(operationFormMap);
						OperationPodFormMap operationPodFormMap = new OperationPodFormMap();
						operationPodFormMap.put("operation_no",operationFormMap.get("id"));
						String[] pod = podBtn.split(",");
						for(String p : pod){
							if(p.equals("开始")){
								operationPodFormMap.put("pod_button_no",startId);
								baseMapper.addEntity(operationPodFormMap);
							}
							else if(p.equals("完成")){
								operationPodFormMap.put("pod_button_no",finishId);
								baseMapper.addEntity(operationPodFormMap);
							}
							else if(p.equals("装配")){
								operationPodFormMap.put("pod_button_no",assenbleId);
								baseMapper.addEntity(operationPodFormMap);
							}else{
								operationPodFormMap.put("pod_button_no",ncId);
								baseMapper.addEntity(operationPodFormMap);
							}
						}
					}

				}
			}
		}

		// 第3个 sheet 操作资源  
		Sheet resourceSheet = book.getSheetAt(2); //或者book.createSheet("Sheet"+number);
		lastRowIndex = resourceSheet.getLastRowNum();    //最后一行 
		firstRow = resourceSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = resourceSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String operationResource_no = row.getCell(j++).getStringCellValue();
					String operationResource_desc = row.getCell(j++).getStringCellValue();
					String operation = row.getCell(j++).getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					state = state.equals("是")?"1":"0";
					OperationResourceFormMap operationResourceFormMap = new OperationResourceFormMap();
					operationResourceFormMap.put("operation_no", operationResource_no);
					operationResourceFormMap.put("site", site);
					List<OperationResourceFormMap> tempOperationResourceList = baseMapper.findByNames(operationResourceFormMap);
					if(CollectionUtils.isEmpty(tempOperationResourceList)){
						operationResourceFormMap.put("operationResource_desc", operationResource_desc);
						operationResourceFormMap.put("operation", operation);
						operationResourceFormMap.put("state", state);
						operationResourceFormMap.put("byUser", byUser);
						baseMapper.addEntity(operationResourceFormMap);
					}
				}
			}
		}

		// 第4个 sheet 工艺路线
		Sheet routeSheet = book.getSheetAt(3); //或者book.createSheet("Sheet"+number);
		lastRowIndex = routeSheet.getLastRowNum();    //最后一行 
		firstRow = routeSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = routeSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String process_route = row.getCell(j++).getStringCellValue();
					String process_route_desc = row.getCell(j++).getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					int allowpass =(int) row.getCell(j++).getNumericCellValue();
					String step = row.getCell(j++).getStringCellValue();
					state = state.equals("可用")?"1":"0";

					RoutingFormMap routingFormMap = new RoutingFormMap();
					routingFormMap.put("process_route", process_route);
					routingFormMap.put("siteBo", site);
					List<RoutingFormMap> tempRoutingFormMapList = baseMapper.findByNames(routingFormMap);
					if(CollectionUtils.isEmpty(tempRoutingFormMapList)){
						String[] routeStep = step.split(",");
						int initNum = routeStep.length;
						int top = 10;
						String data =  "{\"title\": \"工艺路线绘制\",\"areas\": {},\"initNum\": "+initNum+",";
						String nodes = "\"nodes\": {";
						String lines = "\"lines\": {";
						for(int m = 0 ;m < initNum - 1; m++){
							String node = routeStep[m];
							String nextNode = routeStep[m+1];
							nodes = nodes + " \""+node+"\":{ \"name\": \""+node+"\", \"left\": 10, \"top\": "+(top*(m+10)+30)+", \"type\": \""+node+"\", \"width\": 86,\"height\": 24,\"alt\": true},";
							nodes = nodes + " \""+nextNode+"\":{ \"name\": \""+nextNode+"\", \"left\": 10, \"top\": "+(top*(m+15)+30)+", \"type\": \""+nextNode+"\", \"width\": 86,\"height\": 24,\"alt\": true},";
							lines = lines + " \"demo_line_"+m+"\": { \"type\": \"sl\", \"from\": \""+node+"\", \"to\": \""+nextNode+"\", \"name\": \"\",\"alt\": true }},";
							if(m == initNum-2){
								nodes = nodes.substring(0, nodes.length()-1);
								lines = lines.substring(0, lines.length()-1);
							}

							RoutingStepFormMap routingStepFormMap = new RoutingStepFormMap();
							routingStepFormMap.put("process_route", process_route);
							routingStepFormMap.put("operation", node);
							routingStepFormMap.put("next_operation", nextNode);
							routingStepFormMap.put("state", state);
							routingStepFormMap.put("site", site);
							baseMapper.addEntity(routingStepFormMap);
						}
						data = data + nodes +"}," + lines + "} ";

						routingFormMap.put("process_route_desc", process_route_desc);
						routingFormMap.put("allowpass", String.valueOf(allowpass));
						routingFormMap.put("state", state);
						routingFormMap.put("createUser", byUser);
						routingFormMap.put("data", data);
						try {
							baseMapper.addEntity(routingFormMap);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

		// 第5个 sheet 物料信息 
		Sheet itemSheet = book.getSheetAt(4); //或者book.createSheet("Sheet"+number);
		lastRowIndex = itemSheet.getLastRowNum();    //最后一行 
		firstRow = itemSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = itemSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String item_no = row.getCell(j++).getStringCellValue();
					String item_name = row.getCell(j++).getStringCellValue();
					String item_desc = row.getCell(j++).getStringCellValue();
					Cell ce = row.getCell(j++);
					String bom_no = ce == null ? null : ce.getStringCellValue();
					String item_type = row.getCell(j++).getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					item_type = item_type.equals("自生产")?"machining":"purchase";
					state = state.equals("是")?"1":"0";
					ItemFormMap itemFormMap = new ItemFormMap();
					itemFormMap.put("item_no", item_no);
					itemFormMap.put("site", site);
					List<ItemFormMap> tempItemList = baseMapper.findByNames(itemFormMap);
					if(CollectionUtils.isEmpty(tempItemList)){
						itemFormMap.put("item_name", item_name);
						itemFormMap.put("item_desc", item_desc);
						itemFormMap.put("bom_no", bom_no);
						itemFormMap.put("item_type", item_type);
						itemFormMap.put("state", state);
						itemFormMap.put("by_user", byUser);
						baseMapper.addEntity(itemFormMap);
					}
				}
			}
		}


		// 第6个 sheet 物料BOM信息 
		Sheet itemBomSheet = book.getSheetAt(5); //或者book.createSheet("Sheet"+number);
		lastRowIndex = itemBomSheet.getLastRowNum();    //最后一行 
		firstRow = itemBomSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = itemBomSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String item_bom_no = row.getCell(j++).getStringCellValue();
					String item_bom_name = row.getCell(j++).getStringCellValue();
					String item_bom_desc = row.getCell(j++).getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					String item_no = row.getCell(j++).getStringCellValue();
					int user_number = (int)row.getCell(j++).getNumericCellValue();
					int balance_up = (int)row.getCell(j++).getNumericCellValue();
					int balance_down = (int)row.getCell(j++).getNumericCellValue();
					state = state.equals("是")?"1":"0";

					BomFormMap bomFormMap = new BomFormMap();
					bomFormMap.put("item_bom_no", item_bom_no);
					bomFormMap.put("site", site);
					List<BomFormMap> tempBomList = baseMapper.findByNames(bomFormMap);
					if(CollectionUtils.isEmpty(tempBomList)){
						bomFormMap.put("item_bom_name", item_bom_name);
						bomFormMap.put("item_bom_desc", item_bom_desc);
						bomFormMap.put("state", state);
						bomFormMap.put("by_user", byUser);
						baseMapper.addEntity(bomFormMap);
					}
					ItemBomFormMap itemBomFormMap = new ItemBomFormMap();
					itemBomFormMap.put("item_bom_no", item_bom_no);
					itemBomFormMap.put("item_no", item_no);
					itemBomFormMap.put("site", site);
					List<ItemBomFormMap> tempItemBomList = baseMapper.findByNames(itemBomFormMap);
					if(CollectionUtils.isEmpty(tempItemBomList)){
						itemBomFormMap.put("user_number", user_number);
						itemBomFormMap.put("balance_up", balance_up);
						itemBomFormMap.put("balance_down", balance_down);
						baseMapper.addEntity(itemBomFormMap);
					}
				}
			}
		}


		// 第7个 sheet 不良代码组信息 
		Sheet ncGroupSheet = book.getSheetAt(6); //或者book.createSheet("Sheet"+number);
		lastRowIndex = ncGroupSheet.getLastRowNum();    //最后一行 
		firstRow = ncGroupSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = ncGroupSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String nc_code_group_no = row.getCell(j++).getStringCellValue();
					String nc_code_group_desc = row.getCell(j++).getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					state = state.equals("是")?"1":"0";

					NcCodeGroupFormMap ncCodeGroupFormMap = new NcCodeGroupFormMap();
					ncCodeGroupFormMap.put("nc_code_group_no", nc_code_group_no);
					ncCodeGroupFormMap.put("site", site);
					List<NcCodeGroupFormMap> tempNcCodeGroupList = baseMapper.findByNames(ncCodeGroupFormMap);
					if(CollectionUtils.isEmpty(tempNcCodeGroupList)){
						ncCodeGroupFormMap.put("nc_code_group_desc", nc_code_group_desc);
						ncCodeGroupFormMap.put("state", state);
						ncCodeGroupFormMap.put("by_user", byUser);
						baseMapper.addEntity(ncCodeGroupFormMap);
					}
				}
			}
		}


		// 第8个 sheet 不良代码信息 
		Sheet ncSheet = book.getSheetAt(7); //或者book.createSheet("Sheet"+number);
		lastRowIndex = ncSheet.getLastRowNum();    //最后一行 
		firstRow = ncSheet.getRow(0);              // 读取首行
		lastColumnIndex = firstRow.getLastCellNum(); // 最后一列  
		if(lastRowIndex >= 1){
			for (int i = 1; i <= lastRowIndex; i++) {
				Row row = ncSheet.getRow(i);
				for (int j = 0; j < lastColumnIndex; j++) {
					String titleCell = row.getCell(j).getStringCellValue();  
					if(null == titleCell || titleCell.trim().isEmpty()){
						//System.out.println("出现空字符");
						break;
					}
					String nc_code = row.getCell(j++).getStringCellValue();
					String nc_code_desc = row.getCell(j++).getStringCellValue();
					String nc_code_group = row.getCell(j++).getStringCellValue();
					String state = row.getCell(j++).getStringCellValue();
					state = state.equals("是")?"1":"0";

					NcCodeFormMap ncCodeFormMap = new NcCodeFormMap();
					ncCodeFormMap.put("nc_code", nc_code);
					ncCodeFormMap.put("site", site);
					List<NcCodeFormMap> tempNcCodeList = baseMapper.findByNames(ncCodeFormMap);
					if(CollectionUtils.isEmpty(tempNcCodeList)){
						ncCodeFormMap.put("nc_code_desc", nc_code_desc);
						ncCodeFormMap.put("state", state);
						ncCodeFormMap.put("by_user", byUser);
						baseMapper.addEntity(ncCodeFormMap);
					}
					NcCodeGroupRelFormMap ncCodeGroupRelFormMap = new NcCodeGroupRelFormMap();
					ncCodeGroupRelFormMap.put("nc_code_group", nc_code_group);
					ncCodeGroupRelFormMap.put("nc_code", nc_code);
					ncCodeGroupRelFormMap.put("site", site);
					List<NcCodeGroupRelFormMap> tempNcCodeGroupRelList = baseMapper.findByNames(ncCodeGroupRelFormMap);
					if(CollectionUtils.isEmpty(tempNcCodeGroupRelList)){
						baseMapper.addEntity(ncCodeGroupRelFormMap);
					}
				}
			}
		}

		return ;
	}

}