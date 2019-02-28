//package com;
//import java.io.PrintWriter;
//import java.lang.reflect.Field;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.ModelAndView;
//import org.uz.dxt.common.Result;
//import org.uz.dxt.model.golddb.CuishouCompany;
//import org.uz.dxt.model.golddb.TBankRegisterInfo;
//import org.uz.dxt.model.user.Permission;
//import org.uz.dxt.model.zwy.User;
//import org.uz.dxt.service.golddb.CuishouCompanyService;
//import org.uz.dxt.service.golddb.SecurityFunctionService;
//import org.uz.dxt.service.golddb.TBankCompanyService;
//import org.uz.dxt.service.user.PermissionService;
//import com.alibaba.fastjson.JSON;
//
//public class ProfessionInterceptor implements  HandlerInterceptor {
//public static Set<String> validProfessionTargets = new HashSet<>();
//
//private static final Logger log = LoggerFactory.getLogger(ProfessionInterceptor.class);
//@Autowired
//private PermissionService permissionService;//查询权限
//
//@Autowired
//private SecurityFunctionService securityFunctionService;
//
//@Autowired
//private CuishouCompanyService cuishouCompanyService;//查询催收公司
//
//@Autowired
//private TBankCompanyService tBankCompanyService;//查询金融机构
//
//@Override
//public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
//        throws Exception {
//
//        //验证
//        Result validRe = this.validOperation(req);
//        int status = validRe.getStatus();
//        if(status == 10001){
//        return true;
//        }
//
//        String wrongName = getWrongNameByCode(status);
//
//        try {
//        //如果是ajax请求响应头会有x-requested-with
//        if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
//        log.error("[ajax] 专业付费功能验证权限失败， 错误码："+status);
//        resp.setContentType("text/html; charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write(JSON.toJSONString(validRe));
//        out.flush();
//        out.close();
//        return false;
//
//        }else{
//        log.error("[非 ajax] 专业付费功能验证权限失败， 错误码："+status);
//        resp.setContentType("text/html; charset=utf-8");
//        PrintWriter out = resp.getWriter();
//
//        out.write("<p>专业会员功能验证失败，错误码: "+status+", 错误原因：" + wrongName +"</p>");
//        out.flush();
//        out.close();
//        return false;
//
//        }
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//
//        return true;
//        }
//
//@Override
//public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//        ModelAndView modelAndView) throws Exception {
//        // TODO Auto-generated method stub
//
//        }
//
//@Override
//public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//        throws Exception {
//        // TODO Auto-generated method stub
//
//        }
//
///**
// *
// * @param req
// * @return result
// * status
// * 10001-success
// * 40002-未提供验证参数，验证失败
// *
// */
//private Result validOperation(HttpServletRequest req){
//
//        Result result = new Result();
//
//        //1. 未开启验证通过
//        boolean sensitiveValidOpen = this.isProfessionValidOpen();
//        if(!sensitiveValidOpen){
//        result.setStatus(10001);
//        return result;
//        }
//
//        //2. 判断请求url  不在设定的拦截uri内  通过
//        if(validProfessionTargets.isEmpty()){
//        Permission t = new Permission();
//        t.setOprtTypeId((short)5);//权限类别: 1-菜单 2-页面 3-需ukey验证的敏感操作 4-付费高级功能 5-付费专业功能
//        t.setDataState((short)1);
//        List<Permission> pers = permissionService.queryListByWhere(t);
//        for (Permission permission : pers) {
//        String perUrl = permission.getPerUrl();
//        if(perUrl != null){
//        validProfessionTargets.add(perUrl.trim());
//        }
//        }
//        }
//
//        String requestURI = req.getRequestURI();
//        if(! validProfessionTargets.contains(requestURI)){
//        result.setStatus(10001);
//        return result;
//        }
//
//
//        //3. 请求参数在设定uri内验证 vipstate=0 普通会员 1高级会员 2专业会员
//        HttpSession session = req.getSession();
//
//        Integer vipstate = getProfessionStateFromSession(session);
//        if(vipstate == 0){//vipstate=0普通用户  1付费高级 2 付费专业
//        result.setStatus(40002);
//        } else {
//        result.setStatus(10001);
//        }
//
//        return result;
//
//
//        }
//
//
//
///**
// * 验证全局敏感操作验证是否开启
// * @param req
// * @return
// */
//private boolean isProfessionValidOpen(){
//
//        boolean ProfessionValidOpen = securityFunctionService.isProfessionValidOpen();
//
//        return ProfessionValidOpen;
//
//
//        }
//
//
///**
// * 查询催收公司和金融机构是否是专业级别的公司
// * @param req
// * @return
// */
//private Integer getProfessionStateFromSession(HttpSession session) {
//        Object obj = session.getAttribute("user");
//
//        Integer vipstate = null;
//        if(obj instanceof CuishouCompany){//如果是催收公司管理员
//        CuishouCompany company = (CuishouCompany)obj;
//
//        vipstate = company.getVipstate();
//        } else if(obj instanceof TBankRegisterInfo){//如果是金融机构
//        TBankRegisterInfo bank = (TBankRegisterInfo)obj;
//        vipstate = bank.getVipstate();
//
//        } else if(obj instanceof User){//如果是用户
//
//        User user = (User)obj;
//        String companyCode = user.getIsCuishouId();
//        String bankCode = user.getBankCode();
//        if(companyCode != null && !"".equals(companyCode)){//如果是催收公司
//        CuishouCompany company = new CuishouCompany();
//        company.setCompanyid(companyCode);
//        List<CuishouCompany> lst = cuishouCompanyService.queryListByWhere(company);
//        if(lst != null && !lst.isEmpty()){
//        vipstate = lst.get(0).getVipstate();
//        }
//        } else if(bankCode != null && !"".equals(bankCode)){//如果是金融机构
//        TBankRegisterInfo tb = new TBankRegisterInfo();
//        tb.setBankcode(bankCode);
//        List<TBankRegisterInfo> lst = tBankCompanyService.queryListByWhere(tb);
//        if(lst != null && !lst.isEmpty()){
//        vipstate = lst.get(0).getVipstate();
//        }
//        }
//        }
//
//        return vipstate;
//        }
//
//
//private static String getWrongNameByCode(int status)
//        throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        String wrongName = "";
//
//        Class<?> clazz = Class.forName("org.uz.dxt.interceptor.param.ProfessionValidWrongCode");
//        Object obj = clazz.newInstance();
//        Field[] fields = clazz.getDeclaredFields();
//
//        for (Field field : fields) {
//
//        field.setAccessible(true);
//        String name = field.getName();
//        if(name.equals("CODE_"+status)){
//        wrongName = (String)field.get(obj);
//        }
//        }
//        return wrongName;
//        }
//        }