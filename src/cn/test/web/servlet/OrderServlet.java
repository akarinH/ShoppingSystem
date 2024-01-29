package cn.test.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.test.pojo.Cart;
import cn.test.pojo.CartItem;
import cn.test.pojo.Order;
import cn.test.pojo.OrderItem;
import cn.test.pojo.Product;
import cn.test.pojo.User;
import cn.test.service.OrderService;
import cn.test.utils.DataSourceUtils;

public class OrderServlet extends BasicServlet{
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//确认订单,将订单信息提交到数据库
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		order.setOrdertime(format.format(new Date()));
		//将order传入到数据库
		try {
			/****************开启事务***************************/
			DataSourceUtils.startTransaction();
			OrderService service = new OrderService();
			List<OrderItem> list = order.getList();
			service. setOrderItem(order);                 //先将order放入数据库
			for (OrderItem orderItem : list) {
				orderItem.setOrder(order);
				service.setOrderItem(orderItem);
			}
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect(request.getContextPath()+"/over.jsp");


	}
	
	
	/**
	 * 确认订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			//用户未登陆
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}else {
			//将购物车里的购物项封装到订单项中
			Cart cart = (Cart) session.getAttribute("cart");
			Map<String, CartItem> cartMap = cart.getCartMap();
			Order order = new Order();                       //订单对象
			order.setOid(UUID.randomUUID().toString());
			order.setState(0);
			order.setUid(user.getUid());
			order.setTotal(cart.getTotal());
			List<OrderItem> list = new ArrayList<OrderItem>();
			for (Map.Entry<String, CartItem> entry:cartMap.entrySet()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(entry.getValue().getProduct());
				orderItem.setItemid(UUID.randomUUID().toString());
				orderItem.setOrder(order);
				orderItem.setProductNum(entry.getValue().getProductNum());
				orderItem.setSubTotal(entry.getValue().getSubTotal());
				list.add(orderItem);
			}
			order.setList(list);
			cartMap.clear();
			cart.setCartMap(cartMap);
			session.setAttribute("order", order);
			response.sendRedirect(request.getContextPath()+"/order_info.jsp");
		}
	}
	/**
	 * 我的订单 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void myOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//从session中拿到order,根据其中的uid查询数据库找到orderList,然后遍历每一个order
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			//用户未登陆
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			//用户登陆了
			String uid = user.getUid();
			OrderService service = new OrderService();
			List<Order> orderList = service.findAllOrderByUid(uid);
			for(Order order : orderList) {
				//为每一个Order封装List<OrderItem> list,还要未每一个orderItem封装Product product
				List<Map<String, Object>> maplist = service.findAllOrderItemByOid(order.getOid());
				//遍历封装
				for(Map<String,Object> map : maplist) {
					try {
						Product product = new Product();
						OrderItem orderItem = new OrderItem();
						BeanUtils.populate(product, map);
						BeanUtils.populate(orderItem, map);
						orderItem.setProduct(product);
						order.getList().add(orderItem);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			session.setAttribute("orderList", orderList);
			response.sendRedirect(request.getContextPath()+"/order_list.jsp");
		}
		
		
		
	}
	
	
	
	
	
}
