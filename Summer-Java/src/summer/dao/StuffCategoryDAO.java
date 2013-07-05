package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.StuffCategory;

/**
 * A data access object (DAO) providing persistence and search support for
 * StuffCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see summer.pojo.StuffCategory
 * @author MyEclipse Persistence Tools
 */
public class StuffCategoryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StuffCategoryDAO.class);
	// property constants
	public static final String TEMPLATE_ITEM_ID = "templateItemId";
	public static final String NAME = "name";

	public void save(StuffCategory transientInstance) {
		log.debug("saving StuffCategory instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StuffCategory persistentInstance) {
		log.debug("deleting StuffCategory instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StuffCategory findById(java.lang.Long id) {
		log.debug("getting StuffCategory instance with id: " + id);
		try {
			StuffCategory instance = (StuffCategory) getSession().get(
					"summer.pojo.StuffCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StuffCategory instance) {
		log.debug("finding StuffCategory instance by example");
		try {
			List results = getSession()
					.createCriteria("summer.pojo.StuffCategory")
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
		log.debug("finding StuffCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from StuffCategory as model where model."
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

	public List findAll() {
		log.debug("finding all StuffCategory instances");
		try {
			String queryString = "from StuffCategory";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StuffCategory merge(StuffCategory detachedInstance) {
		log.debug("merging StuffCategory instance");
		try {
			StuffCategory result = (StuffCategory) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StuffCategory instance) {
		log.debug("attaching dirty StuffCategory instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StuffCategory instance) {
		log.debug("attaching clean StuffCategory instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}