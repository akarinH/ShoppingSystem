package cn.test.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.test.pojo.User;
import cn.test.service.UserService;

@WebServlet("/UserServlet")
public class  UserServlet extends BasicServlet {

	public void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 从前台接收数据,并封装成User

		Map<String, String[]> properties = request.getParameterMap();

		User user = new User();
		try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// 手动封装
		user.setUid(UUID.randomUUID().toString());
		user.setTelephone("123");
		user.setState(1); // 1代表默认是激活
		String activeCode = UUID.randomUUID().toString(); // 激活码
		user.setCode(activeCode);
		// 将User传给service层进行注册
		UserService service = new UserService();
		boolean isRegister = service.register(user);
		if (isRegister) {
			// 注册成功
			response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
		} else {
			// 注册失败，跳转到失败页面
			response.sendRedirect(request.getContextPath() + "/registerFail.jsp");
		}
	}



	public void isUsernameExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		UserService service = new UserService();
		Boolean isExist = service.isExist(username);

		response.getWriter().write("{\"isExist\":" + isExist + "}");
	}

	public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> properties = request.getParameterMap();
		User loginUser = new User();
		try {
			BeanUtils.populate(loginUser, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		UserService service = new UserService();
		User user = service.login(loginUser);
		if (user == null) {
			// 表示用户不存在
			request.getSession().setAttribute("loginInfo", "false"); // 将登陆信息存到session中
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			//登陆成功
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath());

		}
	}

	public void quitUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 退出当前登陆
		request.getSession().setAttribute("user", null);
		response.sendRedirect(request.getContextPath()+"/product?method=index");
	}

	public void checkCodeVarify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String checkCode = (String) request.getParameter("checkCode");
		Boolean isRight = checkCode.equals(request.getSession().getAttribute("checkcode_session"));
		response.getWriter().write("{\"isRight\":" + isRight + "}");
	}
}
