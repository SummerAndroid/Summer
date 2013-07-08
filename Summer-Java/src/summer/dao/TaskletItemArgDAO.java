package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.TaskletItemArg;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskletItemArg entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see summer.pojo.TaskletItemArg
 * @author MyEclipse Persistence Tools
 */
public class TaskletItemArgDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TaskletItemArgDAO.class);
	// property constants
	public static final String TASKLET_ITEM_ID = "taskletItemId";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String COMMENT = "comment";

	public void save(TaskletItemArg transientInstance) {
		log.debug("saving TaskletItemArg instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskletItemArg persistentInstance) {
		log.debug("deleting TaskletItemArg instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskletItemArg findById(java.lang.Long id) {
		log.debug("getting TaskletItemArg instance with id: " + id);
		try {
			TaskletItemArg instance = (TaskletItemArg) getSession().get(
					"summer.pojo.TaskletItemArg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskletItemArg instance) {
		log.debug("finding TaskletItemArg instance by example");
		try {
			List results = getSession()
					.createCriteria("summer.pojo.TaskletItemArg")
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
		log.debug("finding TaskletItemArg instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TaskletItemArg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTaskletItemId(Object taskletItemId) {
		return findByProperty(TASKLET_ITEM_ID, taskletItemId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findAll() {
		log.debug("finding all TaskletItemArg instances");
		try {
			String queryString = "from TaskletItemArg";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskletItemArg merge(TaskletItemArg detachedInstance) {
		log.debug("merging TaskletItemArg instance");
		try {
			TaskletItemArg result = (TaskletItemArg) getSession().merge(
					detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskletItemArg instance) {
		log.debug("attaching dirty TaskletItemArg instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskletItemArg instance) {
		log.debug("attaching clean TaskletItemArg instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}