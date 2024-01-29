package cn.test.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.test.pojo.Category;
import cn.test.pojo.Product;
import cn.test.utils.DataSourceUtils;

public class  ProductDao {

	public List<Product> findHotProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select  * from product where is_hot=1 LIMIT 9 OFFSET 1";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public List<Product> findNewProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select  * from product order by pdate desc LIMIT 9 OFFSET 1";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
		
	}

	public List<Category> findCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	public List<Product> findProductByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),cid);
	}

	public int findAllProductByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Number query = (Number)runner.query(sql, new ScalarHandler(), cid);
		return query.intValue();
	}

	public List<Product> findPageBeanProductList(int index, int currentCount,String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? order by pdate desc limit ?,?;";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid,index,currentCount);
	}
///临时的搜索
	public List<Product> findPageBeanProduct(int index, int currentCount, String key) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ? order by pdate desc limit ?,?;";
		key="%"+key+"%";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), key,index,currentCount);
	}
	public int findAllProductByKey(String key) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid like ?";
		key="%"+key+"%";
		Number query = (Number)runner.query(sql, new ScalarHandler(), key);
		return query.intValue();
	}
//////
	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		return runner.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	public Category findCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid=?";
		return runner.query(sql, new BeanHandler<Category>(Category.class),cid);
	}

	

	
	
}
