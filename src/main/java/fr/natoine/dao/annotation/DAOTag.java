/*
 * Copyright 2010 Antoine Seilles (Natoine)
 *   This file is part of dao-annotation.

    controler-resource is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    controler-resource is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with dao-annotation.  If not, see <http://www.gnu.org/licenses/>.

 */
package fr.natoine.dao.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.natoine.model_annotation.Domain;
import fr.natoine.model_annotation.Judgment;
import fr.natoine.model_annotation.Mood;
import fr.natoine.model_annotation.Tag;
import fr.natoine.model_annotation.TagAgent;
import fr.natoine.model_resource.URI;
import fr.natoine.model_user.Agent;
import fr.natoine.model_user.AgentStatus;
import fr.natoine.stringOp.StringOp;

public class DAOTag 
{
	private EntityManagerFactory emf = null ;
	
	public DAOTag(EntityManagerFactory _emf)
	{
		emf = _emf ;
	}
	
	//CreateTag
	/**
	 * Creates a Tag persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public boolean createTag(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Tag _tag = new Tag();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createTag] fails to create tag"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTag.createTag] unable to persist tag"
					+ " not a valid label : " + label);
			return false;
		}
	}

	/**
	 * Creates a Tag persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public Tag createAndGetTag(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Tag _tag = new Tag();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return _tag ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createTag] fails to create tag"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Tag();
			}
		}
		else
		{
			System.out.println("[CreateTag.createTag] unable to persist tag"
					+ " not a valid label : " + label);
			return new Tag();
		}
	}

	/**
	 * Create a Tag persistent in the Database that means the agent is author of resources annotated with this tag
	 * @param agent
	 * @param context_creation
	 * @param representsResource
	 * @param status
	 * @return
	 */
	public boolean createTagAgentIsAuthor(Agent agent, String context_creation, URI representsResourceTag, URI representsResourceDefinition)
	{
		AgentStatus status = new AgentStatus();
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			status = (AgentStatus) em.createQuery("from AgentStatus where label = ?").setParameter(1, "isAuthor").getSingleResult();
			tx.commit();
			//em.close();
		}
		catch(Exception e)
		{
			System.out.println("[CreateTag.createTagAgentIsAuthor] unable to retrieve AgentStatus isAuthor");
			tx.rollback();
			//em.close();
			status.setLabel("isAuthor");
			status.setComment("permet de dire qu'un agent est autheur");
		}
		return this.createTagAgent(context_creation, representsResourceTag, agent, status);
	}
	/**
	 * Creates and returns a Tag Agent persistent in the Database
	 * @param agent
	 * @param context_creation
	 * @param representsResourceTag
	 * @param representsResourceDefinition
	 * @return
	 */
	public TagAgent createAndGetTagAgentIsAuthor(Agent agent , String context_creation , URI representsResourceTag, URI representsResourceDefinition)
	{
		AgentStatus status = new AgentStatus();
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			status = (AgentStatus) em.createQuery("from AgentStatus where label = ?").setParameter(1, "isAuthor").getSingleResult();
			tx.commit();
			//em.close();
		}
		catch(Exception e)
		{
			System.out.println("[CreateTag.createTagAgentIsAuthor] unable to retrieve AgentStatus isAuthor");
			tx.rollback();
			//em.close();
			status.setLabel("isAuthor");
			status.setComment("permet de dire qu'un agent est autheur");
		}
		return this.createAndGetTagAgent(context_creation, representsResourceTag, agent, status);
	}

	/**
	 * Creates a Tag persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists.
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param agent
	 * @param status
	 * @return
	 */
	public boolean createTagAgent(String label, String context_creation, URI representsResource, Agent agent, AgentStatus status)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			TagAgent _tagA = new TagAgent();
			_tagA.setContextCreation(context_creation);
			_tagA.setCreation(new Date());
			_tagA.setLabel(label);
			_tagA.setRepresentsResource(representsResource);
			_tagA.setStatus(status);
			_tagA.setAgent(agent);
		//	EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tagA.setRepresentsResource(_synchro_represents_resource);
				}
				if(agent.getId() != null)
				{
					Agent _synchro_agent = em.find(agent.getClass(), agent.getId());
					if(_synchro_agent != null) _tagA.setAgent(_synchro_agent);
				}
				if(status.getId() != null)
				{
					AgentStatus _synchro_status = em.find(status.getClass(), status.getId());
					if(_synchro_status != null) _tagA.setStatus(_synchro_status);
				}
				em.persist(_tagA);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createTagAgent] fails to create TagAgent"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTag.createTag] unable to persist TagAgent"
					+ " not a valid label : " + label);
			return false;
		}
	}
	
	/**
	 * Creates a Tag persistent in the Database with an automatic labeling. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists.
	 * @param context_creation
	 * @param representsResource
	 * @param agent
	 * @param status
	 * @return
	 */
	public boolean createTagAgent(String context_creation, URI representsResource, Agent agent, AgentStatus status)
	{
		String label = status.getLabel() + agent.getLabel() + agent.getId();//garantit l'unicité du nom de ce tag
		return createTagAgent(label, context_creation, representsResource, agent, status);
	}
	/**
	 * Creates and returns a TagAgent
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param agent
	 * @param status
	 * @return
	 */
	public TagAgent createAndGetTagAgent(String label, String context_creation, URI representsResource, Agent agent, AgentStatus status)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			TagAgent _tagA = new TagAgent();
			_tagA.setContextCreation(context_creation);
			_tagA.setCreation(new Date());
			_tagA.setLabel(label);
			_tagA.setRepresentsResource(representsResource);
			_tagA.setStatus(status);
			_tagA.setAgent(agent);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tagA.setRepresentsResource(_synchro_represents_resource);
				}
				if(agent.getId() != null)
				{
					Agent _synchro_agent = em.find(agent.getClass(), agent.getId());
					if(_synchro_agent != null) _tagA.setAgent(_synchro_agent);
				}
				if(status.getId() != null)
				{
					AgentStatus _synchro_status = em.find(status.getClass(), status.getId());
					if(_synchro_status != null) _tagA.setStatus(_synchro_status);
				}
				em.persist(_tagA);
				tx.commit();
				//em.close();
				return _tagA ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createTagAgent] fails to create TagAgent"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new TagAgent();
			}
		}
		else
		{
			System.out.println("[CreateTag.createTag] unable to persist TagAgent"
					+ " not a valid label : " + label);
			return new TagAgent();
		}
	}
	/**
	 * Creates and returns a TagAgent and generates its label
	 * @param context_creation
	 * @param representsResource
	 * @param agent
	 * @param status
	 * @return
	 */
	public TagAgent createAndGetTagAgent(String context_creation, URI representsResource, Agent agent, AgentStatus status)
	{
		String label = status.getLabel() + agent.getLabel() + agent.getId();//garantit l'unicité de ce tagAgent
		return this.createAndGetTagAgent(label , context_creation, representsResource, agent, status);
	}
	/**
	 * Creates a Mood
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public boolean createMood(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Mood _tag = new Mood();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createMood] fails to create mood"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTag.createMood] unable to persist mood"
					+ " not a valid label : " + label);
			return false;
		}
	}
	/**
	 * Creates and returns a Mood
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public Mood createAndGetMood(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Mood _tag = new Mood();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return _tag ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createMood] fails to create mood"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Mood();
			}
		}
		else
		{
			System.out.println("[CreateTag.createMood] unable to persist mood"
					+ " not a valid label : " + label);
			return new Mood();
		}
	}
	/**
	 * Creates a Judgment
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public boolean createJudgment(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Judgment _tag = new Judgment();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createJudgment] fails to create judgment"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTag.createJudgment] unable to persist judgment"
					+ " not a valid label : " + label);
			return false;
		}
	}
	/**
	 * Creates and returns a Judgment
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public Judgment createAndGetJudgment(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Judgment _tag = new Judgment();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return _tag ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createJudgment] fails to create judgment"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Judgment();
			}
		}
		else
		{
			System.out.println("[CreateTag.createJudgment] unable to persist judgment"
					+ " not a valid label : " + label);
			return new Judgment();
		}
	}
	/**
	 * Creates a Domain
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public boolean createDomain(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Domain _tag = new Domain();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createDomain] fails to create domain"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTag.createDomain] unable to persist domain"
					+ " not a valid label : " + label);
			return false;
		}
	}
	/**
	 * Creates and returns a Domain
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @return
	 */
	public Domain createAndGetDomain(String label, String context_creation, URI representsResource)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Domain _tag = new Domain();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return _tag ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createDomain] fails to create domain"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Domain();
			}
		}
		else
		{
			System.out.println("[CreateTag.createDomain] unable to persist domain"
					+ " not a valid label : " + label);
			return new Domain();
		}
	}
	/**
	 * Creates a Domain that extends a specified already existing Domain
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param father
	 * @return
	 */
	public boolean createDomainChild(String label, String context_creation, URI representsResource, Domain father)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Domain _tag = new Domain();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			_tag.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				if(father.getId() != null)
				{
					Domain _synchro_father = em.find(father.getClass(), father.getId());
					if(_synchro_father != null) _tag.setFather(_synchro_father);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createDomainChild] fails to create domain"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTag.createDomain] unable to persist domain"
					+ " not a valid label : " + label);
			return false;
		}
	}
	/**
	 * Creates and returns a Domain that extends an already existing Domain
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param father
	 * @return
	 */
	public Domain createAndGetDomainChild(String label, String context_creation, URI representsResource, Domain father)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Domain _tag = new Domain();
			_tag.setContextCreation(context_creation);
			_tag.setCreation(new Date());
			_tag.setLabel(label);
			_tag.setRepresentsResource(representsResource);
			_tag.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _tag.setRepresentsResource(_synchro_represents_resource);
				}
				if(father.getId() != null)
				{
					Domain _synchro_father = em.find(father.getClass(), father.getId());
					if(_synchro_father != null) _tag.setFather(_synchro_father);
				}
				em.persist(_tag);
				tx.commit();
				//em.close();
				return _tag ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTag.createDomainChild] fails to create domain"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Domain();
			}
		}
		else
		{
			System.out.println("[CreateTag.createDomain] unable to persist domain"
					+ " not a valid label : " + label);
			return new Domain();
		}
	}
	
	//RetrieveTag
	public List<Tag> retrieveAllTag()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Tag> tags = em.createQuery("from Tag").getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveAllTag] unable to retrieve Tag"
					+ " cause : " + e.getMessage());
			return new ArrayList<Tag>();
		}
	}
	
	/**
	 * Retrieves a list of tag with the specified label
	 * @param label
	 * @return
	 */
	public List<Tag> retrieveTag(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Tag> tags = em.createQuery("from Tag where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveTag] unable to retrieve Tag"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Tag>();
		}
	}
	
	public List<Tag> retrieveAllDomain()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Tag> tags = em.createQuery("from Domain").getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveAllDomain] unable to retrieve Tag"
					+ " cause : " + e.getMessage());
			return new ArrayList<Tag>();
		}
	}
	/**
	 * Retrieves a list of domain with the specified label
	 * @param label
	 * @return
	 */
	public List<Domain> retrieveDomain(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Domain> tags = em.createQuery("from Domain where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveDomain] unable to retrieve Domain"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Domain>();
		}
	}
	
	public List<Tag> retrieveAllJudgment()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Tag> tags = em.createQuery("from Judgment").getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveAllJudgment] unable to retrieve Tag"
					+ " cause : " + e.getMessage());
			return new ArrayList<Tag>();
		}
	}
	/**
	 * Retrieves a list of judgment with the specified label
	 * @param label
	 * @return
	 */
	public List<Judgment> retrieveJudgment(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Judgment> tags = em.createQuery("from Judgment where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveJudgment] unable to retrieve Judgment"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Judgment>();
		}
	}
	
	public List<Tag> retrieveAllMood()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Tag> tags = em.createQuery("from Mood").getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveAllMood] unable to retrieve Tag"
					+ " cause : " + e.getMessage());
			return new ArrayList<Tag>();
		}
	}
	
	/**
	 * Retrieves a list of mood with the specified label
	 * @param label
	 * @return
	 */
	public List<Mood> retrieveMood(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Mood> tags = em.createQuery("from Mood where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return tags;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTag.retrieveMood] unable to retrieve Mood"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Mood>();
		}
	}
	
	/**
	 * Retrieves a TagAgent with the specified label
	 * @param label
	 * @return
	 */
	public TagAgent retrieveTagAgent(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			TagAgent tag = (TagAgent)em.createQuery("from TagAgent where label = ?").setParameter(1, label).getSingleResult();
			tx.commit();
			return tag;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTagAgent.retrieveTagAgent] unable to retrieve TagAgent"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new TagAgent();
		}
	}
	/**
	 * Retrieves a TagAgent with the specified label and the specified AgentStatus
	 * @param label
	 * @param agent
	 * @param status
	 * @return
	 */
	public TagAgent retrieveTagAgent(String label, Agent agent, AgentStatus status)
	{
		if(agent.getId()!=null && status.getId()!=null)
		{
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				Agent synchro_agent = em.find(agent.getClass(), agent.getId());
				if(synchro_agent != null) agent = synchro_agent ;
				else
				{
					tx.commit();
					System.out.println("[RetrieveTagAgent.retrieveTagAgent] unable to retrieve TagAgent"
							+ " label : " + label
							+ " cause : " + "the agent : " + agent + " doesn't exist.");
					return new TagAgent();
				}
				AgentStatus _synchro_status = em.find(status.getClass(), status.getId());
				if(_synchro_status != null ) status = _synchro_status ;
				else
				{
					tx.commit();
					System.out.println("[RetrieveTagAgent.retrieveTagAgent] unable to retrieve TagAgent"
							+ " label : " + label
							+ " cause : " + "the status : " + status + " doesn't exist.");
					return new TagAgent();
				}
				//TagAgent tag =(TagAgent) em.createQuery("from TagAgent where label = ? and agent = ? and status = ?").setParameter(1, label).setParameter(2, agent).setParameter(3, status).getSingleResult();
				List<TagAgent> tags = em.createQuery("from TagAgent where label = ? and agent = ? and status = ?").setParameter(1, label).setParameter(2, agent).setParameter(3, status).getResultList();
				TagAgent tag = new TagAgent();
				if(tags.size() > 0) tag = (TagAgent)tags.get(0);
				tx.commit();
				if(tag.getId() != null) return tag;
				else 
				{
					System.out.println("[RetrieveTagAgent.retrieveTagAgent] unable to retrieve TagAgent"
							+ " label : " + label
							+ " cause : " + "no result to the request.");
					return new TagAgent();
				}
			}
			catch(Exception e)
			{
				tx.rollback();
				//em.close();
				System.out.println("[RetrieveTagAgent.retrieveTagAgent] unable to retrieve TagAgent"
						+ " label : " + label
						+ " cause : " + e.getMessage());
				return new TagAgent();
			}
		}
		else
		{
			System.out.println("[RetrieveTagAgent.retrieveTagAgent] unable to retrieve TagAgent"
					+ " label : " + label
					+ " cause : " + "agent : " + agent + " or status : " + status +" doesn't exist");
			return new TagAgent();
		}
	}
	/**
	 * Retrieves TagAgent Is Author for a specified Agent
	 * @param agent
	 * @return
	 */
	public TagAgent retrieveTagAgentIsAuthor(Agent agent)
	{
		return this.retrieveTagAgent("isAuthor" + agent.getLabel() + agent.getId());
		//return this.retrieveTagAgent("isAuthor", agent, status);
	}
}