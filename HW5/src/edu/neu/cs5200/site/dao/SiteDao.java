package edu.neu.cs5200.site.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.site.models.*;

@Path("/site")
public class SiteDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("HW5");
	EntityManager em = null;
	
	@GET
	@Path("/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("ID")int siteId) {
		em = factory.createEntityManager();
		Site site =null;
		em.getTransaction().begin();
		site = em.find(Site.class, siteId);
		em.getTransaction().commit();
		em.close();
		return site;

	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		List<Site> sites = new ArrayList<Site>();
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findAllSites");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id")int siteId, Site site) {
		List<Site> sites = new ArrayList<Site>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		site.setId(siteId);
		em.merge(site);
		Query query = em.createNamedQuery("findAllSites");
		sites = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id")int siteId){
		List<Site> sites = new ArrayList<Site>();
		Site site = null;
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		site = em.find(Site.class, siteId);
		em.remove(site);
		
		Query query = em.createNamedQuery("findAllSites");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site){
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(site);
		Query query = em.createNamedQuery("findAllSites");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	public static void main(String[] args){
		SiteDao dao = new SiteDao();
		Site site = dao.findSite(2);
		System.out.println(site.getLatitude());
		
	}

}
