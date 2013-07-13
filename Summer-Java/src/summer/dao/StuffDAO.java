package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.Stuff;

/**
 * A data access object (DAO) providing persistence and search support for Stuff
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see summer.pojo.Stuff
 * @author MyEclipse Persistence Tools
 */
public class StuffDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StuffDAO.class);
	// property constants
	public static final String STUFF_CATEGORY_ID = "stuffCategoryId";
	public static final String CODE = "code";
	public static final String PRICE = "price";
	public static final String LIFE = "life";
	public static final String ADDRESS = "address";
	public static final String FACTORY = "factory";
	public static final String ZXING = "zxing";

	public Stuff save(Stuff transientInstance) {
		log.debug("saving Stuff instance");
		Stuff user;
		try {
			user = (Stuff) getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			return null;
		}
		return user;
	}

	public void delete(long id) {
		log.debug("deleting stuff instance");
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(
					"delete from stuff where id = ?");
			sqlQuery.setLong(0, id);
			sqlQuery.addEntity(Stuff.class);
			sqlQuery.executeUpdate();
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(Stuff persistentInstance) {
		log.debug("deleting Stuff instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Stuff findById(java.lang.Long id) {
		log.debug("getting Stuff instance with id: " + id);
		try {
			Stuff instance = (Stuff) getSession().get("summer.pojo.Stuff",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Stuff instance) {
		log.debug("finding Stuff instance by example");
		try {
			List results = getSession().createCriteria("summer.pojo.Stuff")
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
		log.debug("finding Stuff instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Stuff as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStuffCategoryId(Object stuffCategoryId) {
		return findByProperty(STUFF_CATEGORY_ID, stuffCategoryId);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByLife(Object life) {
		return findByProperty(LIFE, life);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByFactory(Object factory) {
		return findByProperty(FACTORY, factory);
	}

	public List findByZxing(Object zxing) {
		return findByProperty(ZXING, zxing);
	}

	public List findAll() {
		log.debug("finding all Stuff instances");
		try {
			String queryString = "from Stuff";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Stuff merge(Stuff detachedInstance) {
		log.debug("merging Stuff instance");
		try {
			Stuff result = (Stuff) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Stuff instance) {
		log.debug("attaching dirty Stuff instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Stuff instance) {
		log.debug("attaching clean Stuff instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}