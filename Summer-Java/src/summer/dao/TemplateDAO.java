package summer.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import summer.pojo.Template;

/**
 * A data access object (DAO) providing persistence and search support for
 * Template entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see summer.pojo.Template
 * @author MyEclipse Persistence Tools
 */
public class TemplateDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TemplateDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(Template transientInstance) {
		log.debug("saving Template instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(long id) {
		log.debug("deleting template instance");
		try {
			SQLQuery sqlQuery = getSession().createSQLQuery(
					"delete from template where id = ?");
			sqlQuery.setLong(0, id);
			sqlQuery.addEntity(Template.class);
			sqlQuery.executeUpdate();
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(Template persistentInstance) {
		log.debug("deleting Template instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Template findById(java.lang.Long id) {
		log.debug("getting Template instance with id: " + id);
		try {
			Template instance = (Template) getSession().get(
					"summer.pojo.Template", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Template instance) {
		log.debug("finding Template instance by example");
		try {
			List results = getSession()
.createCriteria("summer.pojo.Template")
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
		log.debug("finding Template instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Template as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Template instances");
		try {
			String queryString = "from Template";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Template merge(Template detachedInstance) {
		log.debug("merging Template instance");
		try {
			Template result = (Template) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Template instance) {
		log.debug("attaching dirty Template instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Template instance) {
		log.debug("attaching clean Template instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}