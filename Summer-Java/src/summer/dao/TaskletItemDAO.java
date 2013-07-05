package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.TaskletItem;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskletItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see summer.pojo.TaskletItem
 * @author MyEclipse Persistence Tools
 */
public class TaskletItemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TaskletItemDAO.class);
	// property constants
	public static final String TASKLET_ID = "taskletId";
	public static final String STUFF_ID = "stuffId";
	public static final String NAME = "name";

	public void save(TaskletItem transientInstance) {
		log.debug("saving TaskletItem instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskletItem persistentInstance) {
		log.debug("deleting TaskletItem instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskletItem findById(java.lang.Long id) {
		log.debug("getting TaskletItem instance with id: " + id);
		try {
			TaskletItem instance = (TaskletItem) getSession().get(
					"summer.pojo.TaskletItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskletItem instance) {
		log.debug("finding TaskletItem instance by example");
		try {
			List results = getSession()
					.createCriteria("summer.pojo.TaskletItem")
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
		log.debug("finding TaskletItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TaskletItem as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTaskletId(Object taskletId) {
		return findByProperty(TASKLET_ID, taskletId);
	}

	public List findByStuffId(Object stuffId) {
		return findByProperty(STUFF_ID, stuffId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all TaskletItem instances");
		try {
			String queryString = "from TaskletItem";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskletItem merge(TaskletItem detachedInstance) {
		log.debug("merging TaskletItem instance");
		try {
			TaskletItem result = (TaskletItem) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskletItem instance) {
		log.debug("attaching dirty TaskletItem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskletItem instance) {
		log.debug("attaching clean TaskletItem instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}