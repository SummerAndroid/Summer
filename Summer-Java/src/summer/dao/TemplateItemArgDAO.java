package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.TemplateItemArg;

/**
 * A data access object (DAO) providing persistence and search support for
 * TemplateItemArg entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see summer.pojo.TemplateItemArg
 * @author MyEclipse Persistence Tools
 */
public class TemplateItemArgDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TemplateItemArgDAO.class);
	// property constants
	public static final String TEMPLATE_ITEM_ID = "templateItemId";
	public static final String NAME = "name";
	public static final String VALUE = "value";

	public void save(TemplateItemArg transientInstance) {
		log.debug("saving TemplateItemArg instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TemplateItemArg persistentInstance) {
		log.debug("deleting TemplateItemArg instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TemplateItemArg findById(java.lang.Long id) {
		log.debug("getting TemplateItemArg instance with id: " + id);
		try {
			TemplateItemArg instance = (TemplateItemArg) getSession().get(
					"summer.pojo.TemplateItemArg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TemplateItemArg instance) {
		log.debug("finding TemplateItemArg instance by example");
		try {
			List results = getSession()
					.createCriteria("summer.pojo.TemplateItemArg")
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
		log.debug("finding TemplateItemArg instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TemplateItemArg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTemplateItemId(Object templateItemId) {
		return findByProperty(TEMPLATE_ITEM_ID, templateItemId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all TemplateItemArg instances");
		try {
			String queryString = "from TemplateItemArg";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TemplateItemArg merge(TemplateItemArg detachedInstance) {
		log.debug("merging TemplateItemArg instance");
		try {
			TemplateItemArg result = (TemplateItemArg) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TemplateItemArg instance) {
		log.debug("attaching dirty TemplateItemArg instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TemplateItemArg instance) {
		log.debug("attaching clean TemplateItemArg instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}