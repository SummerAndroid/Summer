package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.StuffArg;

/**
 * A data access object (DAO) providing persistence and search support for
 * StuffArg entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see summer.pojo.StuffArg
 * @author MyEclipse Persistence Tools
 */
public class StuffArgDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StuffArgDAO.class);
	// property constants
	public static final String STUFF_ID = "stuffId";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String COMMENT = "comment";

	public void save(StuffArg transientInstance) {
		log.debug("saving StuffArg instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(long id) {
		log.debug("deleting stuff_arg instance");
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(
					"delete from stuff_arg where id = ?");
			sqlQuery.setLong(0, id);
			sqlQuery.addEntity(StuffArg.class);
			sqlQuery.executeUpdate();
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(StuffArg persistentInstance) {
		log.debug("deleting StuffArg instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StuffArg findById(java.lang.Long id) {
		log.debug("getting StuffArg instance with id: " + id);
		try {
			StuffArg instance = (StuffArg) getSession().get(
					"summer.pojo.StuffArg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StuffArg instance) {
		log.debug("finding StuffArg instance by example");
		try {
			List results = getSession().createCriteria("summer.pojo.StuffArg")
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
		log.debug("finding StuffArg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from StuffArg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStuffId(Object stuffId) {
		return findByProperty(STUFF_ID, stuffId);
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
		log.debug("finding all StuffArg instances");
		try {
			String queryString = "from StuffArg";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StuffArg merge(StuffArg detachedInstance) {
		log.debug("merging StuffArg instance");
		try {
			StuffArg result = (StuffArg) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StuffArg instance) {
		log.debug("attaching dirty StuffArg instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StuffArg instance) {
		log.debug("attaching clean StuffArg instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}