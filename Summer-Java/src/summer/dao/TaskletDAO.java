package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.inf.I.Req;
import summer.pojo.Tasklet;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tasklet entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see summer.pojo.Tasklet
 * @author MyEclipse Persistence Tools
 */
public class TaskletDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TaskletDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String NAME = "name";
	public static final String CYCLE = "cycle";
	public static final String ACCOUNT = "account";
	public static final String LAST_TIME = "last_time";

	public List<Tasklet> findBy(long userId, long start, long end,
			boolean isFinish, String order) {
		// TODO:对cycle的使用错了。记得改
		log.debug("find Tasklet instance");
		String sql = createSql(start, end, isFinish);
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setLong(0, userId);
		if (start == end && start == Req.TASKLET_ALL) {// 我是很不想重复这个判断的...
			query.setString(1, order);
		} else {
			query.setLong(1, start);
			query.setLong(2, end);
			query.setString(3, order);
		}
		query.addEntity(Tasklet.class);
		log.info(query.toString());
		@SuppressWarnings("unchecked") List<Tasklet> tasklets = query.list();
		return tasklets;
	}

	public void save(Tasklet transientInstance) {
		log.debug("saving Tasklet instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tasklet persistentInstance) {
		log.debug("deleting Tasklet instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tasklet findById(java.lang.Long id) {
		log.debug("getting Tasklet instance with id: " + id);
		try {
			Tasklet instance = (Tasklet) getSession().get(
					"summer.pojo.Tasklet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tasklet instance) {
		log.debug("finding Tasklet instance by example");
		try {
			List results = getSession().createCriteria("summer.pojo.Tasklet")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tasklet instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tasklet as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByCycle(Object cycle) {
		return findByProperty(CYCLE, cycle);
	}

	public List findAll() {
		log.debug("finding all Tasklet instances");
		try {
			String queryString = "from Tasklet";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tasklet merge(Tasklet detachedInstance) {
		log.debug("merging Tasklet instance");
		try {
			Tasklet result = (Tasklet) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tasklet instance) {
		log.debug("attaching dirty Tasklet instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tasklet instance) {
		log.debug("attaching clean Tasklet instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @param isFinish
	 */
	private String createSql(long start, long end, boolean isFinish) {
		// select id,name,cycle,account,last_time from tasklet where user_id = ? and last_time >= ? and last_time <= ? and (account = 0 or currentTime < last_time+cycle )group by ?;
		// select id,name,cycle,account,last_time from tasklet where user_id = ? and last_time >= ? and last_time <= ? and account=|<>0 group by ?;
		// select id,name,cycle,account,last_time from tasklet where user_id = ? and account=|<>0 group by ?;

		long currentTime = System.currentTimeMillis();

		StringBuilder builder = new StringBuilder();
		builder.append("select id,user_id,");
		builder.append(NAME);
		builder.append(",");
		builder.append(CYCLE);
		builder.append(",");
		builder.append(ACCOUNT);
		builder.append(",");
		builder.append(LAST_TIME);
		builder.append(" from tasklet where user_id = ? and ");
		// 用&&比较好理解，如果start == end && start == Req.TASKLET_ALL,则说明要获取所有的任务
		if (start == end && start == Req.TASKLET_ALL) {} else {
			builder.append(LAST_TIME);
			builder.append(" >= ? and ");
			builder.append(LAST_TIME);
			builder.append(" <= ? and ");
		}
		builder.append(ACCOUNT);
		builder.append(isFinish ? " = 0 " : " <> 0 ");// isFinish = true,则需要查询完成的任务， isFinish = false，则需要查询未完成任务
		builder.append("order by ? ;");
		return builder.toString();
	}
}