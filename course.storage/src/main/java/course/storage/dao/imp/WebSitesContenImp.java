package course.storage.dao.imp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import course.spider.entity.WebSitesContent;
import course.storage.dao.WebSitesContenDao;

/**
 * @author zhaojd
 * @date 2016年10月26日 下午7:49:15
 * @version 1.0
 */
public class WebSitesContenImp implements WebSitesContenDao {
	private SqlSessionFactory sqlSession;

	public WebSitesContenImp() {
		super();
		try {
			sqlSession = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(WebSitesContent record) {
		if (selectByTitle(record.getTitle()).size()==0) {
			SqlSession session = sqlSession.openSession();
			int insert = session.insert("insert", record);
			session.commit();
			session.close();
			return insert;
		} else {
			return -1;
		}

	}

	@Override
	public int insertSelective(WebSitesContent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WebSitesContent selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(WebSitesContent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(WebSitesContent record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void batchInsert(List<WebSitesContent> sitesContent) {
		// TODO Auto-generated method stub

	}

	@Override

	public List<WebSitesContent> selectByTitle(String title) {
		SqlSession session = sqlSession.openSession();
		return session.selectList("selectByTitle", title);
	}

	@Override
	public List<WebSitesContent> selectByDate() {
		SqlSession session = sqlSession.openSession();
		return session.selectList("selectByDate");
	}

	@Override
	public List<WebSitesContent> selectByDateAndSitesId(int sitesId) {
		SqlSession session = sqlSession.openSession();
		return session.selectList("selectByDateAndSitesId",sitesId);
	}

}
