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

import fr.natoine.model_annotation.Topic;
import fr.natoine.model_resource.URI;
import fr.natoine.stringOp.StringOp;

public class DAOTopic 
{
	private EntityManagerFactory emf = null ;
	
	public DAOTopic(EntityManagerFactory _emf)
	{
		emf = _emf ;
	}
	
	//CreateTopic
	/**
	 * Creates a Topic persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param description
	 * @return
	 */
	public boolean createTopic(String label, String context_creation, URI representsResource, String description)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Topic _topic = new Topic();
			_topic.setContextCreation(context_creation);
			_topic.setCreation(new Date());
			_topic.setLabel(label);
			_topic.setRepresentsResource(representsResource);
			_topic.setDescription(StringOp.deleteBlanks(description));
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _topic.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_topic);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTopic.createTopic] fails to create topic"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " description : " + description
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTopic.createTopic] unable to persist topic"
					+ " not a valid label : " + label);
			return false;
		}
	}

	/**
	 * Creates a Topic persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param description
	 * @return
	 */
	public Topic createAndGetTopic(String label, String context_creation, URI representsResource, String description)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Topic _topic = new Topic();
			_topic.setContextCreation(context_creation);
			_topic.setCreation(new Date());
			_topic.setLabel(label);
			_topic.setRepresentsResource(representsResource);
			_topic.setDescription(StringOp.deleteBlanks(description));
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _topic.setRepresentsResource(_synchro_represents_resource);
				}
				em.persist(_topic);
				tx.commit();
				//em.close();
				return _topic ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTopic.createTopic] fails to create topic"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " description : " + description
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Topic();
			}
		}
		else
		{
			System.out.println("[CreateTopic.createTopic] unable to persist topic"
					+ " not a valid label : " + label);
			return new Topic();
		}
	}

	/**
	 * Creates a Topic persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param description
	 * @param father
	 * @return
	 */
	public boolean createTopicChild(String label, String context_creation, URI representsResource, String description, Topic father)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Topic _topic = new Topic();
			_topic.setContextCreation(context_creation);
			_topic.setCreation(new Date());
			_topic.setLabel(label);
			_topic.setRepresentsResource(representsResource);
			_topic.setDescription(StringOp.deleteBlanks(description));
			_topic.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _topic.setRepresentsResource(_synchro_represents_resource);
				}
				if(father.getId() != null)
				{
					Topic _synchro_father = em.find(father.getClass(), father.getId());
					if(_synchro_father != null) _topic.setFather(_synchro_father);
				}
				em.persist(_topic);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTopic.createTopic] fails to create topic"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " description : " + description
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTopic.createTopic] unable to persist topic"
					+ " not a valid label : " + label);
			return false;
		}
	}

	/**
	 * Creates a Topic persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param description
	 * @return
	 */
	public Topic createAndGetTopicChild(String label, String context_creation, URI representsResource, String description, Topic father)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Topic _topic = new Topic();
			_topic.setContextCreation(context_creation);
			_topic.setCreation(new Date());
			_topic.setLabel(label);
			_topic.setRepresentsResource(representsResource);
			_topic.setDescription(StringOp.deleteBlanks(description));
			_topic.setFather(father);
		//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _topic.setRepresentsResource(_synchro_represents_resource);
				}
				if(father.getId() != null)
				{
					Topic _synchro_father = em.find(father.getClass(), father.getId());
					if(_synchro_father != null) _topic.setFather(_synchro_father);
				}
				em.persist(_topic);
				tx.commit();
				//em.close();
				return _topic ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTopic.createTopic] fails to create topic"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " description : " + description
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Topic();
			}
		}
		else
		{
			System.out.println("[CreateTopic.createTopic] unable to persist topic"
					+ " not a valid label : " + label);
			return new Topic();
		}
	}

	/**
	 * Creates a Topic persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param description
	 * @param father_id
	 * @return
	 */
	public boolean createTopicChild(String label, String context_creation, URI representsResource, String description, long father_id)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Topic _topic = new Topic();
			_topic.setContextCreation(context_creation);
			_topic.setCreation(new Date());
			_topic.setLabel(label);
			_topic.setRepresentsResource(representsResource);
			_topic.setDescription(StringOp.deleteBlanks(description));
			//_topic.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _topic.setRepresentsResource(_synchro_represents_resource);
				}
				Topic _synchro_father = em.find(Topic.class, father_id);
				if(_synchro_father != null) _topic.setFather(_synchro_father);
				em.persist(_topic);
				tx.commit();
				//em.close();
				return true ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTopic.createTopic] fails to create topic"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " description : " + description
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return false;
			}
		}
		else
		{
			System.out.println("[CreateTopic.createTopic] unable to persist topic"
					+ " not a valid label : " + label);
			return false;
		}
	}

	/**
	 * Creates and gets a Topic persistent in the Database. 
	 * The URI used to represent the resource is created if it doesn't exist, resynchronised if it already exists. 
	 * @param label
	 * @param context_creation
	 * @param representsResource
	 * @param description
	 * @param father_id
	 * @return
	 */
	public Topic createAndGetTopicChild(String label, String context_creation, URI representsResource, String description, long father_id)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Topic _topic = new Topic();
			_topic.setContextCreation(context_creation);
			_topic.setCreation(new Date());
			_topic.setLabel(label);
			_topic.setRepresentsResource(representsResource);
			_topic.setDescription(StringOp.deleteBlanks(description));
			//_topic.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try{
				tx.begin();
				if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _topic.setRepresentsResource(_synchro_represents_resource);
				}
				Topic _synchro_father = em.find(Topic.class, father_id);
				if(_synchro_father != null) _topic.setFather(_synchro_father);
				em.persist(_topic);
				tx.commit();
				//em.close();
				return _topic ;
			}
			catch(Exception e)
			{
				System.out.println("[CreateTopic.createTopic] fails to create topic"
						+ " context creation : " + context_creation
						+ " label : " + label
						+ " description : " + description
						+ " cause : " + e.getMessage());
				tx.rollback();
				//em.close();
				return new Topic();
			}
		}
		else
		{
			System.out.println("[CreateTopic.createTopic] unable to persist topic"
					+ " not a valid label : " + label);
			return new Topic();
		}
	}
	
	//RetrieveTopic
	/**
	 * Retrieves a list of topic with the specified label
	 * @param label
	 * @return
	 */
	public List<Topic> retrieveTopic(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			List<Topic> topics = em.createQuery("from Topic where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			return topics;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTopic.retrieveTopic] unable to retrieve Topic"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Topic>();
		}
	}
	/**
	 * Retrieves all the Topics
	 * @return
	 */
	public List<Topic> retrieveAllTopTopics()
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Topic> topics = em.createQuery("from Topic where father = null").getResultList();
			tx.commit();
			return topics;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTopic.retrieveTopic] unable to retrieve Topics"
					+ " cause : " + e.getMessage());
			return new ArrayList<Topic>();
		}
	}
	/**
	 * Retrieves all the extending topics of a topic, all its children
	 * @param father
	 * @return
	 */
	public List<Topic> retrieveChildTopics(Topic father)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			List<Topic> topics = em.createQuery("from Topic where father = ?").setParameter(1, father).getResultList();
			tx.commit();
			return topics;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveTopic.retrieveChildTopics] unable to retrieve Topics"
					+ " cause : " + e.getMessage());
			return new ArrayList<Topic>();
		}
	}
}