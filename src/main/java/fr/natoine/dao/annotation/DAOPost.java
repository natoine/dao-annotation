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

import fr.natoine.model_annotation.Definition;
import fr.natoine.model_annotation.Post;
import fr.natoine.model_annotation.PostStatus;
import fr.natoine.model_resource.URI;
import fr.natoine.model_user.Agent;
import fr.natoine.stringOp.StringOp;

public class DAOPost 
{
	private EntityManagerFactory emf = null ;
	
	public DAOPost(EntityManagerFactory _emf)
	{
		emf = _emf ;
	}
	
	//CreatePostStatus
	/**
	 * Creates a PostStatus 
	 * @param _label
	 * @param _comment
	 */
	public boolean createPostStatus(String _label, String _comment)
	{
		_label = StringOp.deleteBlanks(_label);
		if(!StringOp.isNull(_label))
		{
			_comment = StringOp.deleteBlanks(_comment);
			PostStatus _as = new PostStatus();
			_as.setComment(_comment);
			_as.setLabel(_label);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        em.persist(_as);
		        tx.commit();
		        //em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePostStatus.createPostStatus] fails to create PostStatus"
	        			+ " label : " + _label
	        			+ " comment : " + _comment
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        	//em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreatePostStatus.createPostStatus] unable to persist PostStatus"
					+ " not a valid label : " + _label);
			return false;
		}
	}
	/**
	 * Creates and returns a PostStatus
	 * @param label
	 * @param comment
	 * @return
	 */
	public PostStatus createAndGetPostStatus(String label, String comment)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			comment = StringOp.deleteBlanks(comment);
			PostStatus _as = new PostStatus();
			_as.setComment(comment);
			_as.setLabel(label);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        em.persist(_as);
		        tx.commit();
		        //em.close();
		        return _as ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePostStatus.createPostStatus] fails to create PostStatus"
	        			+ " label : " + label
	        			+ " comment : " + comment
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        	//em.close();
	        	return new PostStatus();
	        }
		}
		else
		{
			System.out.println("[CreatePostStatus.createPostStatus] unable to persist PostStatus"
					+ " not a valid label : " + label);
			return new PostStatus();
		}
	}
	/**
	 * Creates an AnnotationStatus that extends another AnnotationStatus
	 * @param label
	 * @param comment
	 * @param father
	 * @return
	 */
	public boolean createPostStatusChild(String label, String comment, PostStatus father)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			comment = StringOp.deleteBlanks(comment);
			PostStatus _as = new PostStatus();
			_as.setComment(comment);
			_as.setLabel(label);
			_as.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(father.getId() != null)
				{
		        	PostStatus _synchro_father = em.find(PostStatus.class, father.getId());
					if(_synchro_father != null) _as.setFather(_synchro_father);
				}
		        em.persist(_as);
		        tx.commit();
		        //em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePostStatus.createPostStatusChild] fails to create PostStatus"
	        			+ " label : " + label
	        			+ " comment : " + comment
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        	//em.close();
	        	return false;
	        }
	    }
		else
		{
			System.out.println("[CreatePostStatus.createPostStatusChild] unable to persist PostStatus"
					+ " not a valid label : " + label);
			return false;
		}
	}
	/**
	 * Creates and returns a PostStatus that extends an existing PostStatus
	 * @param label
	 * @param comment
	 * @param father
	 * @return
	 */
	public PostStatus createAndGetPostStatusChild(String label, String comment, PostStatus father)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			comment = StringOp.deleteBlanks(comment);
			PostStatus _as = new PostStatus();
			_as.setComment(comment);
			_as.setLabel(label);
			_as.setFather(father);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(father.getId() != null)
				{
		        	PostStatus _synchro_father = em.find(PostStatus.class, father.getId());
					if(_synchro_father != null) _as.setFather(_synchro_father);
				}
		        em.persist(_as);
		        tx.commit();
		        //em.close();
		        return _as ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePostStatus.createPostStatusChild] fails to create PostStatus"
	        			+ " label : " + label
	        			+ " comment : " + comment
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        	//em.close();
	        	return new PostStatus();
	        }
		}
		else
		{
			System.out.println("[CreatePostStatus.createPostStatusChild] unable to persist PostStatus"
					+ " not a valid label : " + label);
			return new PostStatus();
		}
	}
	/**
	 * Creates a PostStatus named Définition
	 * @return
	 */
	public boolean createPostStatusDefinition()
	{
		return this.createPostStatus("Définition", "Ce texte est une définition");
	}
	/**
	 * Creates and returns a PostStatus named Définition
	 * @return
	 */
	public PostStatus createAndGetPostStatusDefinition()
	{
		return this.createAndGetPostStatus("Définition", "Ce texte est une définition");
	}
	/**
	 * Creates a PostStatus "Définition de Tag" that extends the PostStatus Définition
	 * @return
	 */
	public boolean createPostStatusDefinitionTag()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinition();
		return this.createPostStatusChild("Définition de tag", "Ce texte est une définition pour un tag" ,  father);
	}
	/**
	 * Creates and return a PostStatus "Définition de Tag" that extends the PostStatus Définition  
	 * @return
	 */
	public PostStatus createAndGetPostStatusDefinitionTag()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinition();
		return this.createAndGetPostStatusChild("Définition de tag", "Ce texte est une définition pour un tag" , father);
	}
	/**
	 * Creates a PostStatus "Définition de tag agent" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public boolean createPostStatusDefinitionTagAgent()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinition();
		return this.createPostStatusChild("Définition de tag agent", "Ce texte est une définition pour un tag agent" , father);
	}
	/**
	 * Creates and return a PostStatus "Définition de tag agent" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public PostStatus createAndGetPostStatusDefinitionTagAgent()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinition();
		return this.createAndGetPostStatusChild("Définition de tag agent", "Ce texte est une définition pour un tag agent" , father);
	}
	/**
	 * Creates a PostStatus "Définition de jugement" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public boolean createPostStatusDefinitionJudgment()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinitionTag();
		return this.createPostStatusChild("Définition de jugement", "Ce texte est une définition pour un jugement" ,  father);
	}
	/**
	 * Creates and return a PostStatus "Définition de jugement" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public PostStatus createAndGetPostStatusDefinitionJudgment()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinitionTag();
		return this.createAndGetPostStatusChild("Définition de jugement", "Ce texte est une définition pour un jugement" , father);
	}
	/**
	 * Creates a PostStatus "Définition d'humeur" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public boolean createPostStatusDefinitionMood()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinitionTag();
		return this.createPostStatusChild("Définition d'humeur", "Ce texte est une définition pour une humeur" ,  father);
	}
	/**
	 * Creates and return a PostStatus "Définition d'humeur" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public PostStatus createAndGetPostStatusDefinitionMood()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinitionTag();
		return this.createAndGetPostStatusChild("Définition d'humeur", "Ce texte est une définition pour une humeur" ,  father);
	}
	/**
	 * Creates a PostStatus "Définition de domaine" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public boolean createPostStatusDefinitionDomain()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinitionTag();
		return this.createPostStatusChild("Définition de domaine", "Ce texte est une définition pour un domaine" ,  father);
	}
	/**
	 * Creates and return a PostStatus "Définition de domaine" that extends the PostStatus "Définition de tag"
	 * @return
	 */
	public PostStatus createAndGetPostStatusDefinitionDomain()
	{
		PostStatus father ;
		father = retrievePostStatus("Définition de tag");
		if(father.getId() == null) father = this.createAndGetPostStatusDefinitionTag();
		return this.createAndGetPostStatusChild("Définition de domaine", "Ce texte est une définition pour un domaine" , father);
	}
	
	//RetrievePostStatus
	/**
	 * Retrieves an AnnotationStatus in the database according to the specified label
	 * @param label
	 * @return an AnnotationStatus that may be a new UriStatus with no value.
	 */
	public PostStatus retrievePostStatus(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			PostStatus ststatus = (PostStatus) em.createQuery("from PostStatus where label = ?").setParameter(1, label).getSingleResult();
			tx.commit();
			return ststatus;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrievePostStatus.retrievePostStatus] unable to retrieve PostStatus"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new PostStatus();
		}
	}
	/**
	 * Retrieves all the PostStatus
	 * @return
	 */
	public List<PostStatus> retrieveAllPostStatus()
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<PostStatus> status = em.createQuery("from PostStatus").getResultList();
			tx.commit();
			return status;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrievePostStatus.retrieveAllPostStatus] unable to retrieve PostStatus"
					+ " cause : " + e.getMessage());
			return new ArrayList<PostStatus>();
		}
	}
	
	//CreatePost
	/**
	 * Creates a Post 
	 * @param context_creation
	 * @param label
	 * @param representsResource
	 * @param content
	 * @param status
	 * @param _author
	 */
	public boolean createPost(String context_creation, String label, URI representsResource, String content, PostStatus status, Agent _author)
	{
		label = StringOp.deleteBlanks(label);
		content = StringOp.deleteBlanks(content);
		if(!StringOp.isNull(label) && !StringOp.isNull(content))
		{
			Post _resource = new Post();
			_resource.setContextCreation(context_creation);
			_resource.setCreation(new Date());
			_resource.setLabel(label);
			_resource.setRepresentsResource(representsResource);
			_resource.setContent(content);
			_resource.setStatus(status);
			_resource.setAuthor(_author);
		//	EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _resource.setRepresentsResource(_synchro_represents_resource);
				}
		        if(status.getId() != null)
		        {
		        	PostStatus _synchro_status = em.find(status.getClass(), status.getId());
		        	if(_synchro_status != null) _resource.setStatus(_synchro_status);
		        }
		        if(_author != null && _author.getId() != null)
		        {
		        	Agent _synchro_agent = em.find(_author.getClass(), _author.getId());
		        	if(_synchro_agent != null) _resource.setAuthor(_synchro_agent);
		        }
		        em.persist(_resource);
		        tx.commit();
		       // em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePost.createPost] fails to create Post"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " content : " + content
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        	//em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreatePost.createPost] unable to persist Post"
					+ " not a valid label : " + label
					+ " or not a valid content : " + content);
			return false;
		}
	}
	/**
	 * Creates and returns a Post
	 * @param context_creation
	 * @param label
	 * @param representsResource
	 * @param content
	 * @param status
	 * @param _author
	 * @return
	 */
	public Post createAndGetPost(String context_creation, String label, URI representsResource, String content, PostStatus status, Agent _author)
	{
		label = StringOp.deleteBlanks(label);
		content = StringOp.deleteBlanks(content);
		if(!StringOp.isNull(label) && !StringOp.isNull(content))
		{
			Post _resource = new Post();
			_resource.setContextCreation(context_creation);
			_resource.setCreation(new Date());
			_resource.setLabel(label);
			_resource.setRepresentsResource(representsResource);
			_resource.setContent(content);
			_resource.setStatus(status);
			_resource.setAuthor(_author);
		//	EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _resource.setRepresentsResource(_synchro_represents_resource);
				}
		        if(status.getId() != null)
		        {
		        	PostStatus _synchro_status = em.find(status.getClass(), status.getId());
		        	if(_synchro_status != null) _resource.setStatus(_synchro_status);
		        }
		        if(_author != null && _author.getId() != null)
		        {
		        	Agent _synchro_agent = em.find(_author.getClass(), _author.getId());
		        	if(_synchro_agent != null) _resource.setAuthor(_synchro_agent);
		        }
		        em.persist(_resource);
		        tx.commit();
		   //     em.close();
		        return _resource ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePost.createAndGetPost] fails to create Post"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " content : " + content
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	       // 	em.close();
	        	return new Post();
	        }
		}
		else
		{
			System.out.println("[CreatePost.createAndGetPost] unable to persist Post"
					+ " not a valid label : " + label
					+ " or not a valid content : " + content);
			return new Post();
		}
	}
	/**
	 * Creates a Definition
	 * @param context_creation
	 * @param label
	 * @param representsResource
	 * @param content
	 * @param status
	 * @param _author
	 * @return
	 */
	public boolean createDefinition(String context_creation, String label, URI representsResource, String content, PostStatus status, Agent _author)
	{
		label = StringOp.deleteBlanks(label);
		content = StringOp.deleteBlanks(content);
		if(!StringOp.isNull(label) && !StringOp.isNull(content))
		{
			Definition _resource = new Definition();
			_resource.setContextCreation(context_creation);
			_resource.setCreation(new Date());
			_resource.setLabel(label);
			_resource.setRepresentsResource(representsResource);
			_resource.setContent(content);
			_resource.setStatus(status);
			_resource.setAuthor(_author);
		//	EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _resource.setRepresentsResource(_synchro_represents_resource);
				}
		        if(status.getId() != null)
		        {
		        	PostStatus _synchro_status = em.find(status.getClass(), status.getId());
		        	if(_synchro_status != null) _resource.setStatus(_synchro_status);
		        }
		        if(_author != null && _author.getId() != null)
		        {
		        	Agent _synchro_agent = em.find(_author.getClass(), _author.getId());
		        	if(_synchro_agent != null) _resource.setAuthor(_synchro_agent);
		        }
		        em.persist(_resource);
		        tx.commit();
		       // em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePost.createDefinition] fails to create Definition"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " content : " + content
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        	//em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreatePost.createDefinition] unable to persist Definition"
					+ " not a valid label : " + label
					+ " or not a valid content : " + content);
			return false;
		}
	}
	/**
	 * Creates and returns a Definition
	 * @param context_creation
	 * @param label
	 * @param representsResource
	 * @param content
	 * @param status
	 * @param _author
	 * @return
	 */
	public Definition createAndGetDefinition(String context_creation, String label, URI representsResource, String content, PostStatus status, Agent _author)
	{
		label = StringOp.deleteBlanks(label);
		content = StringOp.deleteBlanks(content);
		if(!StringOp.isNull(label) && !StringOp.isNull(content))
		{
			Definition _resource = new Definition();
			_resource.setContextCreation(context_creation);
			_resource.setCreation(new Date());
			_resource.setLabel(label);
			_resource.setRepresentsResource(representsResource);
			_resource.setContent(content);
			_resource.setStatus(status);
			_resource.setAuthor(_author);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(representsResource.getClass(), representsResource.getId());
					if(_synchro_represents_resource != null) _resource.setRepresentsResource(_synchro_represents_resource);
				}
		        if(status.getId() != null)
		        {
		        	PostStatus _synchro_status = em.find(status.getClass(), status.getId());
		        	if(_synchro_status != null) _resource.setStatus(_synchro_status);
		        }
		        if(_author != null && _author.getId() != null)
		        {
		        	Agent _synchro_agent = em.find(_author.getClass(), _author.getId());
		        	if(_synchro_agent != null) _resource.setAuthor(_synchro_agent);
		        }
		        em.persist(_resource);
		        tx.commit();
		      //  em.close();
		        return _resource ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreatePost.createAndGetDefinition] fails to create Definition"
	        			+ " context creation : " + context_creation
	        			+ " label : " + label
	        			+ " content : " + content
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        //	em.close();
	        	return new Definition();
	        }
		}
		else
		{
			System.out.println("[CreatePost.createAndGetDefinition] unable to persist Definition"
					+ " not a valid label : " + label
					+ " or not a valid content : " + content);
			return new Definition();
		}
	}
	
	//RetrievePost
	/**
	 * Retrieves all the Post in the database with the specified label.
	 * @param label
	 * @return a List of Post that may be empty
	 */
	public List<Post> retrievePost(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<Post> resources = em.createQuery("from Post where label = ?").setParameter(1, label).getResultList();
			tx.commit();
			//em.close();
			return resources;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrievePost.retrievePost] unable to retrieve Post"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Post>();
		}
	}
}