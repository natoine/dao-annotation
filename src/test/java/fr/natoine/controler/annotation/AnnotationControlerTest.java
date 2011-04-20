package fr.natoine.controler.annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONArray;

import fr.natoine.dao.annotation.DAOAnnotation;
import fr.natoine.dao.annotation.DAOPost;
import fr.natoine.dao.annotation.DAOTag;
import fr.natoine.dao.annotation.DAOTopic;
import fr.natoine.model_annotation.Annotation;
import fr.natoine.model_annotation.AnnotationStatus;
import fr.natoine.model_annotation.Definition;
import fr.natoine.model_annotation.PostStatus;
import fr.natoine.model_annotation.TagAgent;
import fr.natoine.model_annotation.Topic;
import fr.natoine.model_htmlDocs.DocumentHTML;
import fr.natoine.model_htmlDocs.SelectionHTML;
import fr.natoine.model_resource.URI;
import fr.natoine.model_resource.Resource;
import fr.natoine.model_annotation.Tag;
import fr.natoine.model_user.Agent;
import fr.natoine.model_user.AgentStatus;
import fr.natoine.model_user.Application;
import junit.framework.TestCase;

public class AnnotationControlerTest extends TestCase 
{
	
	private EntityManagerFactory emf_annotation = Persistence.createEntityManagerFactory("annotation");
	
	public AnnotationControlerTest(String name) 
	{		    
		super(name);
	}

	public void testCreateAnnotationStatus()
	{
		//CreateAnnotationStatus _controlerAnnotationStatus = new CreateAnnotationStatus();
		DAOAnnotation _controlerAnnotationStatus = new DAOAnnotation(emf_annotation);
		_controlerAnnotationStatus.createAnnotationStatus("test annotation status", "commentaire de test", "red",new JSONArray());
		AnnotationStatus father = new AnnotationStatus();
		father.setLabel("père");
		father.setComment("commentaire du père");
		_controlerAnnotationStatus.createAnnotationStatusChild("test annotation avec père", "le status avec un père", "red", father, new JSONArray());
	}
	
	public void testRetrieveAnnotationStatus()
	{
		//RetrieveAnnotationStatus _controlerAnnotationStatus = new RetrieveAnnotationStatus();
		DAOAnnotation _controlerAnnotationStatus = new DAOAnnotation(emf_annotation);
		AnnotationStatus _as = _controlerAnnotationStatus.retrieveAnnotationStatus("test annotation status");
		System.out.println("[AnnotationControlerTest.testRetrieveAnnotationStatus] retrieves an AnnotationStatus id : " + _as.getId()
				+ " label : " + _as.getLabel()
				+ " comment : " + _as.getComment());
	}
	
	public void testRetrieveAllAnnotationStatus()
	{
		//RetrieveAnnotationStatus _controlerAnnotationStatus = new RetrieveAnnotationStatus();
		DAOAnnotation _controlerAnnotationStatus = new DAOAnnotation(emf_annotation);
		List as = _controlerAnnotationStatus.retrieveAnnotationStatus();
		for(int i = 0 ; i< as.size() ; i++)
		{
			System.out.println("[AnnotationControlerTest.testRetrieveAnnotationStatus] retrieves an AnnotationStatus id : " + ((AnnotationStatus)as.get(i)).getId()
					+ " label : " + ((AnnotationStatus)as.get(i)).getLabel()
					+ " comment : " + ((AnnotationStatus)as.get(i)).getComment());
		}
	}
	
	public void testCreateSimpleCommentStatus()
	{
		//CreateAnnotationStatus _controlerAnnotationStatus = new CreateAnnotationStatus();
		DAOAnnotation _controlerAnnotationStatus = new DAOAnnotation(emf_annotation);
		_controlerAnnotationStatus.createSimpleCommentStatus("");
	}
	
	public void testCreateAccordDesaccordStatus()
	{
		//CreateAnnotationStatus _controlerAnnotationStatus = new CreateAnnotationStatus();
		DAOAnnotation _controlerAnnotationStatus = new DAOAnnotation(emf_annotation);
		_controlerAnnotationStatus.createAgreeStatus("");
		_controlerAnnotationStatus.createDisAgreeStatus("");
	}
	
	public void testCreateComplexAnnotationSample()
	{
		//CreateAnnotationStatus _controlerAnnotationStatus = new CreateAnnotationStatus();
		DAOAnnotation _controlerAnnotationStatus = new DAOAnnotation(emf_annotation);
		_controlerAnnotationStatus.createComplexAnnotationSample("http://uri4tags.fr" , "");
	}
	public void testCreateAnnotation()
	{
		//CreateAnnotation _controler_annotation = new CreateAnnotation();
		DAOAnnotation _controler_annotation = new DAOAnnotation(emf_annotation);
		URI access = new URI();
		access.setEffectiveURI("http://access.test.annotation");
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://represents.test.annotation");
		AnnotationStatus status = new AnnotationStatus();
		status.setComment("commentaire de status pour annotation");
		status.setLabel("status de l'annotation de test");
		
		Collection<URI> annotatedURIs = new ArrayList<URI>();
		
		Collection<Resource> added = new ArrayList<Resource>();
		Resource toadd = new Resource();
		toadd.setContextCreation("test d'annotation");
		toadd.setCreation(new Date());
		toadd.setLabel("resource to add");
		URI representsToAdd = new URI();
		representsToAdd.setEffectiveURI("http://effectiveURI.resourceToADD.annotation");
		toadd.setRepresentsResource(representsToAdd);
		added.add(toadd);
		
		Collection<Resource> annotated = new ArrayList<Resource>();
		Resource toannotate = new Resource();
		toannotate.setContextCreation("test d'annotation");
		toannotate.setCreation(new Date());
		toannotate.setLabel("resource to annotate");
		URI representsToAnnotate = new URI();
		representsToAnnotate.setEffectiveURI("http://effectiveURI.resourceToAnnotate.annotation");
		toannotate.setRepresentsResource(representsToAnnotate);
		annotated.add(toannotate);
		annotatedURIs.add(representsToAnnotate);
		
		_controler_annotation.createAnnotation("test d'annotation", "testCreateAnnotation", access, representsResource, status, added, annotated, annotatedURIs, null);
	}

	public void testCreateAnnotationHTML()
	{
		//CreateAnnotation _controler_annotation = new CreateAnnotation();
		DAOAnnotation _controler_annotation = new DAOAnnotation(emf_annotation);
		URI access = new URI();
		access.setEffectiveURI("http://access.test.annotationhtml");
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://represents.test.annotationhtml");
		AnnotationStatus status = new AnnotationStatus();
		status.setComment("commentaire de status pour annotation");
		status.setLabel("status de l'annotationHTML de test");
		
		Collection<URI>  annotatedURIs = new ArrayList<URI>();
		
		Collection<Resource> added = new ArrayList<Resource>();
		DocumentHTML toadd = new DocumentHTML();
		toadd.setContextCreation("test d'annotationHTML");
		toadd.setCreation(new Date());
		toadd.setLabel("resource to add");
		URI representsToAdd = new URI();
		representsToAdd.setEffectiveURI("http://effectiveURI.resourceToADD.annotationhtml");
		toadd.setRepresentsResource(representsToAdd);
		toadd.setAccess(representsToAdd);
		toadd.setHTMLContent("html content");
		added.add(toadd);
		
		Collection<Resource> annotated = new ArrayList<Resource>();
		SelectionHTML toannotate = new SelectionHTML();
		toannotate.setContextCreation("test d'annotation");
		toannotate.setCreation(new Date());
		toannotate.setLabel("resource to annotate");
		URI representsToAnnotate = new URI();
		representsToAnnotate.setEffectiveURI("http://effectiveURI.resourceToAnnotate.annotationhtml");
		toannotate.setRepresentsResource(representsToAnnotate);
		toannotate.setHTMLContent("html content");
		toannotate.setSelectionOrigin(toadd);
		toannotate.setXpointerBegin("xpointerBegin");
		toannotate.setXpointerEnd("xpointerEnd");
		annotated.add(toannotate);
		
		annotatedURIs.add(toadd.getRepresentsResource());
		
		_controler_annotation.createAnnotation("test annotationHTML", "testCreateAnnotationHTML", access, representsResource, status, added, annotated, annotatedURIs, null);
	}
	public void testRetrieveAnnotation()
	{
		//RetrieveAnnotation _controler_annotation = new RetrieveAnnotation();
		DAOAnnotation _controler_annotation = new DAOAnnotation(emf_annotation);
		List _annotations = _controler_annotation.retrieveAnnotation("test d'annotation");
		for(int i= 0 ; i<_annotations.size() ; i++)
		{
			System.out.println("[AnnotationControlerTest.testRetrieveAnnotation] retrieves annotation id : " + ((Annotation)_annotations.get(i)).getId()
					+ " contexte : " + ((Annotation)_annotations.get(i)).getContextCreation()
					+ " label : " + ((Annotation)_annotations.get(i)).getLabel()
					+ " date : " + ((Annotation)_annotations.get(i)).getCreation());
		}
	}
	
	public void testRetrieveAnnotations()
	{
		//RetrieveAnnotation _controler_annotation = new RetrieveAnnotation();
		DAOAnnotation _controler_annotation = new DAOAnnotation(emf_annotation);
		List _annotations = _controler_annotation.retrieveAnnotations("http://effectiveURI.resourceToAnnotate.annotation");
		System.out.println("[AnnotationControlerTest.testRetrieveAnnotations] " + _annotations.size() + " annotations trouvées");
		for(int i= 0 ; i<_annotations.size() ; i++)
		{
			System.out.println("[AnnotationControlerTest.testRetrieveAnnotations] retrieves annotation id : " + ((Annotation)_annotations.get(i)).getId()
					+ " contexte : " + ((Annotation)_annotations.get(i)).getContextCreation()
					+ " label : " + ((Annotation)_annotations.get(i)).getLabel()
					+ " date : " + ((Annotation)_annotations.get(i)).getCreation());
		}
	}
	
	public void testCreateTag()
	{
		//CreateTag _tagcontroler = new CreateTag();
		DAOTag _tagcontroler = new DAOTag(emf_annotation);
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://testTag.represent.tag");
		_tagcontroler.createTag("tag label", "TagControlerTest.createTag", representsResource);
	}
	
	public void testRetrieveTags()
	{
		//RetrieveTag _tagcontroler = new RetrieveTag();
		DAOTag _tagcontroler = new DAOTag(emf_annotation);
		List _tags = _tagcontroler.retrieveTag("tag label");
		System.out.println("[TagControlerTest.testRetrieveTags] nb tags : " + _tags.size());
		for(int i=0; i<_tags.size();i++)
		{
			System.out.println("[TagControlerTest.testRetrieveTags] tag " + i
					+ " context creation : " + ((Tag)_tags.get(i)).getContextCreation()
					+ " label : " + ((Tag)_tags.get(i)).getLabel()
					+ " id : " + ((Tag)_tags.get(i)).getId()
					+ " date : " + ((Tag)_tags.get(i)).getCreation());
		}
	}
	
	public void testCreateTagAgent()
	{
		//CreateTag _tagcontroler = new CreateTag();
		DAOTag _tagcontroler = new DAOTag(emf_annotation);
		URI representsResource = new URI();
		representsResource.setEffectiveURI("http://testTag.represent.tagagent");
		Agent agent = new Agent();
		Application _app = new Application();
		_app.setDescription("description app test tag");
		_app.setInscription(new Date());
		_app.setLabel("TagControlerTest.createTagAgent");
		Resource represents_app = new Resource();
		represents_app.setContextCreation("TagControlerTest.createTagAgent");
		represents_app.setCreation(new Date());
		represents_app.setLabel("represents App test tag");
		URI URIrepresentsResource = new URI();
		URIrepresentsResource.setEffectiveURI("http://test.tag.app");
		represents_app.setRepresentsResource(URIrepresentsResource);
		_app.setRepresents(represents_app);
		agent.setContextInscription(_app);
		agent.setDescription("agent pour TagAgent");
		agent.setInscription(new Date());
		agent.setLabel("agent label");
		AgentStatus status = new AgentStatus();
		status.setComment("comment AgentStatus");
		status.setLabel("label AgentStatus");
		
		_tagcontroler.createTagAgent("tag label", "TagControlerTest.createTagAgent", representsResource, agent, status);
		//RetrieveTag _tagretriever = new RetrieveTag();
		TagAgent _tag = _tagcontroler.retrieveTagAgent("tag label", agent, status);
		if(_tag.getId() != null)
		{
			System.out.println("[TagControlerTest.testRetrieveTagAgent] tagAgent "
					+ " context creation : " + _tag.getContextCreation()
					+ " label : " + _tag.getLabel()
					+ " id : " + _tag.getId()
					+ " date : " + _tag.getCreation());
		}
	}
	
	public void testRetrieveTagAgents()
	{
		//RetrieveTag _tagcontroler = new RetrieveTag();
		DAOTag _tagcontroler = new DAOTag(emf_annotation);
		Tag _tag = _tagcontroler.retrieveTagAgent("tag label");
		System.out.println("[TagControlerTest.testRetrieveTagAgents] tagAgent "
				+ " context creation : " + _tag.getContextCreation()
				+ " label : " + _tag.getLabel()
				+ " id : " + _tag.getId()
				+ " date : " + _tag.getCreation());
	}
	
	public void testNBAnnotations()
	{
		//RetrieveAnnotation _retriever = new RetrieveAnnotation();
		DAOAnnotation _retriever = new DAOAnnotation(emf_annotation);
		long _nb = _retriever.computeNbAnnotations("http://effectiveURI.resourceToAnnotate.annotation");
		System.out.println("[testNBAnnotations] a retrouvé : " + _nb + " annotations");
	}
	
	public void testCreateTopic()
	{
		//CreateTopic _creator = new CreateTopic();
		DAOTopic _creator = new DAOTopic(emf_annotation);
		URI URIrepresentsResource = new URI();
		URIrepresentsResource.setEffectiveURI("http://test.create.topic");
		_creator.createTopic("topic test", "[testCreateTopic]", URIrepresentsResource, "topic de test create");
		Topic father = _creator.createAndGetTopic("topic test father", "[testCreateTopic]", URIrepresentsResource, "topic de test createAndGet");
		_creator.createTopicChild("child topic", "[testCreateTopic]", URIrepresentsResource, "topic de test createChild", father);
		Topic child = _creator.createAndGetTopicChild("child topic 2", "[testCreateTopic]", URIrepresentsResource, "topic de test createAndGetChild", father);
		//System.out.println("[testCreateTopic] child : " + child.toHTMLMax());
	}
	
	public void testRetrieveTopic()
	{
		//RetrieveTopic _retriever = new RetrieveTopic();
		DAOTopic _retriever = new DAOTopic(emf_annotation);
		List _topics = _retriever.retrieveAllTopTopics();
		for(Object _topic : _topics)
		{
			System.out.println("Top Topic : " + ((Topic)_topic).toHTML());
			List _childs = _retriever.retrieveChildTopics((Topic)_topic);
			if(_childs.size() > 0)
			{
				for (Object _child : _childs)
					System.out.println("Child Topic : " + ((Topic)_child).toHTML());
			}
		}
	}
	
	public void testRetrieveAllSimpleTextStatus()
	{
		//RetrievePostStatus _retriver = new RetrievePostStatus();
		DAOPost _retriver = new DAOPost(emf_annotation); 
		List _status = _retriver.retrieveAllPostStatus();
		System.out.println("status nb : " + _status.size());
		for(Object _statu : _status)
		{
			System.out.println(((PostStatus)_statu).toString());
		}
	}
}