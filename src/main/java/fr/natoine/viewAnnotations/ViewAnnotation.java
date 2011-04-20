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
package fr.natoine.viewAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManagerFactory;

import fr.natoine.dao.annotation.DAOAnnotation;
import fr.natoine.model_annotation.Annotation;
import fr.natoine.model_annotation.Judgment;
import fr.natoine.model_annotation.Mood;
import fr.natoine.model_annotation.Post;
import fr.natoine.model_annotation.Tag;
import fr.natoine.model_annotation.TagAgent;
import fr.natoine.model_resource.Resource;
import fr.natoine.model_user.Agent;

public class ViewAnnotation 
{
	private static DAOAnnotation RETRIEVER_RESOURCES = null ;//new RetrieveAnnotation();
	
	public ViewAnnotation(EntityManagerFactory _emf)
	{
		RETRIEVER_RESOURCES = new DAOAnnotation(_emf);
	}
	
	public String annotationToHTML(Annotation _annotation)
	{
		String _username ;
		String _creator_name = "";
		boolean _author = false ;
		if(_annotation.getCreator() != null)
		{
			_author = true ;
			_username = _annotation.getCreator().getLabel() ;
			_creator_name = _annotation.getCreator().getLabel() ;
		}
		else _username = "Guest" ;
		String _judgment = null ;
		boolean _judgment_test = false ;
		String _mood = "";
		boolean _mood_test = false ;
		long _nb_replies = RETRIEVER_RESOURCES.computeNbAnnotations(_annotation.getRepresentsResource().getEffectiveURI() + "?id=" + _annotation.getId());
		Collection _annotated = RETRIEVER_RESOURCES.retrieveAnnotatedForAnnotation(_annotation);
		Collection _added = RETRIEVER_RESOURCES.retrieveAddedForAnnotation(_annotation);
		Collection<Resource> _to_delete = new ArrayList<Resource>();
		ArrayList<Resource> _to_print = new ArrayList<Resource>();
		if(_added != null)
		{
			for(Resource _add : (Collection<Resource>)_added)
			{
				//System.out.println("[ViewAnnotation.annotationToHTML] parcours de added : " + _add.getClass().getSimpleName());
				if(_add instanceof TagAgent)
				{
					//if(((TagAgent)_add).getLabel().equalsIgnoreCase("isAuthor"))
					if(((TagAgent)_add).getLabel().contains("isAuthor"))
					{
						if(!_author)
						{
							_username = ((TagAgent)_add).getAgent().getLabel();
							_author = true ;
						}
						else
						{
							String new_author_name = ((TagAgent)_add).getAgent().getLabel() ;
							if(! new_author_name.equalsIgnoreCase(_creator_name)) _username = _username.concat(", " + new_author_name);
						}
						//_to_delete.add(_add);
					}
					else _to_print.add(_add);
				}
				else if(_add instanceof Judgment)
				{
					if(!_judgment_test)
					{
						_judgment = "<a href=" + ((Judgment)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Judgment)_add).getId() + ">" + ((Judgment)_add).getLabel() + "</a>";
						_judgment_test = true ;
					}
					else _judgment = _judgment.concat(", " + "<a href=" + ((Judgment)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Judgment)_add).getId() + ">" + ((Judgment)_add).getLabel() + "</a>");
				//	_to_delete.add(_add);
				}
				else if(_add instanceof Mood)
				{
					String _mood_label = ((Mood)_add).getLabel();
					_mood = _mood.concat("<img href=" + ((Mood)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Mood)_add).getId() + " src=/PortletAnnotation-1.0.0/images/mood_"+ _mood_label.replaceAll("é", "e").replace("è", "e") +".png title=" + _mood_label + " />");
					_mood_test = true ;
					//_to_delete.add(_add);
				}
				else if(_add instanceof Post)
				{
					if(((Post)_add).getStatus().getLabel().equalsIgnoreCase("reformulation")) _to_print.add(0, _add);
					else _to_print.add(_add);
				}
				else _to_print.add(_add);
			}
			/*for(Resource _to_del : _to_delete)
			{
				_added.remove(_to_del);
			}*/
		}
		//header
		String _html = "<div class=resource_header>"
			+"<table><tr><td>"
			+"<div class=resource_head_left><span class=author>De " + _username + "</span><br/> <a href=" + _annotation.getRepresentsResource().getEffectiveURI() + "?id=" + _annotation.getId() + ">" + _annotation.getLabel() + "</a>" ;
		if(_judgment_test) _html = _html.concat("<br/><span class=judgment_label>Jugement : </span><span class=judgment>" + _judgment + "</span>");
		_html = _html.concat("</div></td>");
		if(_mood_test) _html = _html.concat("<td><div class=mood>" + _mood + "</div></td>");
		_html = _html.concat("<td><div class=nb_replies>" + _nb_replies + " réponses</div></td>");
		_html = _html.concat("</tr></table></div>");
		//content
		//if(!_added.isEmpty())
		if(!_to_print.isEmpty())
		{
			_html = _html.concat("<div class=content><div class=content_head>Le contenu de l'annotation : </div>");
			_html = _html.concat("<div class=annotation_added>");
			//for(Resource _add : (Collection<Resource>)_added)
			for(Resource _add : _to_print)
			{
				_html = _html.concat(_add.toHTML());
			}
			_html = _html.concat("</div>");
			_html = _html.concat("</div>");
		}
		if(!_annotated.isEmpty())
		{
			_html = _html.concat("<div class=annotation_annotated><div class=annotation_added_head>Les ressources annotées : </div>");
			for(Resource _annotate : (Collection<Resource>)_annotated)
			{
				_html = _html.concat(_annotate.toHTML());
			}
			_html = _html.concat("</div>");
		}
		return _html ;
	}
	public String annotationToHTMLCollapse(Annotation _annotation)
	{
		String _username ;
		String _creator_name = "";
		boolean _author = false ;
		if(_annotation.getCreator() != null)
		{
			_author = true ;
			_username = _annotation.getCreator().getLabel() ;
			_creator_name = _annotation.getCreator().getLabel() ;
		}
		else _username = "Guest" ;
		String _judgment = null ;
		boolean _judgment_test = false ;
		String _mood = "";
		boolean _mood_test = false ;
		long _nb_replies = RETRIEVER_RESOURCES.computeNbAnnotations(_annotation.getRepresentsResource().getEffectiveURI() + "?id=" + _annotation.getId());
		Collection _annotated = RETRIEVER_RESOURCES.retrieveAnnotatedForAnnotation(_annotation);
		Collection _added = RETRIEVER_RESOURCES.retrieveAddedForAnnotation(_annotation);
		Collection<Resource> _to_delete = new ArrayList<Resource>();
		ArrayList<Resource> _to_print = new ArrayList<Resource>();
		if(_added != null)
		{
			for(Resource _add : (Collection<Resource>)_added)
			{
				//System.out.println("[ViewAnnotation.annotationToHTML] parcours de added : " + _add.getClass().getSimpleName());
				if(_add instanceof TagAgent)
				{
					//if(((TagAgent)_add).getLabel().equalsIgnoreCase("isAuthor"))
					if(((TagAgent)_add).getLabel().contains("isAuthor"))
					{
						if(!_author)
						{
							_username = ((TagAgent)_add).getAgent().getLabel();
							_author = true ;
						}
						else
						{
							String new_author_name = ((TagAgent)_add).getAgent().getLabel() ;
							if(! new_author_name.equalsIgnoreCase(_creator_name)) _username = _username.concat(", " + new_author_name);
						}
						//_to_delete.add(_add);
					}
					else _to_print.add(_add);
				}
				else if(_add instanceof Judgment)
				{
					if(!_judgment_test)
					{
						//_judgment = "<a href=" + ((Judgment)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Judgment)_add).getId() + ">" + ((Judgment)_add).getLabel() + "</a>";
						_judgment = ((Judgment)_add).getLabel() ;
						_judgment_test = true ;
					}
					else _judgment = _judgment.concat(", " + ((Judgment)_add).getLabel());
					//else _judgment = _judgment.concat(", " + "<a href=" + ((Judgment)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Judgment)_add).getId() + ">" + ((Judgment)_add).getLabel() + "</a>");
				//	_to_delete.add(_add);
				}
				else if(_add instanceof Mood)
				{
					String _mood_label = ((Mood)_add).getLabel();
					_mood = _mood.concat("<img href=" + ((Mood)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Mood)_add).getId() + " src=/PortletAnnotation-1.0.0/images/mood_"+ _mood_label.replaceAll("é", "e").replace("è", "e") +".png title=" + _mood_label + " />");
					_mood_test = true ;
					//_to_delete.add(_add);
				}
				else if(_add instanceof Post)
				{
					if(((Post)_add).getStatus().getLabel().equalsIgnoreCase("reformulation")) _to_print.add(0, _add);
					else _to_print.add(_add);
				}
				else _to_print.add(_add);
			}
			/*for(Resource _to_del : _to_delete)
			{
				_added.remove(_to_del);
			}*/
		}
		
		//header
		String _html = "<div class=resource_header id=resource_header_" + _annotation.getId() + " onclick=\"switchMenu('annotation_content_" + _annotation.getId() + "');\" >"
			+"<table><tr><td>"
			+"<div class=resource_head_left><span class=author>De " + _username + "</span><br/><span class=resource_title>" + _annotation.getLabel() + "</span>" ;
		if(_judgment_test) _html = _html.concat("<br/><span class=judgment_label>Jugement : </span><span class=judgment>" + _judgment + "</span>");
		_html = _html.concat("</div></td>");
		if(_mood_test) _html = _html.concat("<td><div class=mood>" + _mood + "</div></td>");
		_html = _html.concat("<td><div class=nb_replies>" + _nb_replies + " réponses</div></td>");
		_html = _html.concat("</tr></table></div>");
		//content
		_html = _html.concat("<div class=content style=\"display : none ;\" id=annotation_content_" + _annotation.getId() + ">");
		//if(!_added.isEmpty())
		if(!_annotated.isEmpty())
		{
			_html = _html.concat("<div class=annotation_annotated><div class=annotated_head>Annotation sur les fragments suivants : </div>");
			for(Resource _annotate : (Collection<Resource>)_annotated)
			{
				_html = _html.concat(_annotate.toHTML());
			}
			_html = _html.concat("</div>");
		}
		if(!_to_print.isEmpty())
		{
			_html = _html.concat("<div class=annotation_added><div class=added_head>Le contenu de l'annotation : </div>");
			//for(Resource _add : (Collection<Resource>)_added)
			for(Resource _add : _to_print)
			{
				_html = _html.concat(_add.toHTML());
			}
			_html = _html.concat("</div>");
		}
		_html = _html.concat("</div>");
		return _html ;
	}
	
	/**
	 * Gets an html representation of only the added parts of an annotation
	 * @param _annotation
	 * @return
	 */
	public String annotationToHTMLShort(Annotation _annotation)
	{
		String _username ;
		String _creator_name = "";
		boolean _author = false ;
		if(_annotation.getCreator() != null)
		{
			_author = true ;
			_username = _annotation.getCreator().getLabel() ;
			_creator_name = _annotation.getCreator().getLabel() ;
		}
		else _username = "Guest" ;
		String _judgment = null ;
		boolean _judgment_test = false ;
		String _mood = "";
		boolean _mood_test = false ;
		long _nb_replies = RETRIEVER_RESOURCES.computeNbAnnotations(_annotation.getRepresentsResource().getEffectiveURI() + "?id=" + _annotation.getId());
	//	Collection _annotated = RETRIEVER_RESOURCES.retrieveAnnotatedForAnnotation(_annotation);
		Collection _added = RETRIEVER_RESOURCES.retrieveAddedForAnnotation(_annotation);
		ArrayList<Resource> _to_print = new ArrayList<Resource>();
		if(_added != null)
		{
			for(Resource _add : (Collection<Resource>)_added)
			{
				//System.out.println("[ViewAnnotation.annotationToHTML] parcours de added : " + _add.getClass().getSimpleName());
				if(_add instanceof TagAgent)
				{
					//if(((TagAgent)_add).getLabel().equalsIgnoreCase("isAuthor"))
					if(((TagAgent)_add).getLabel().contains("isAuthor"))
					{
						if(!_author)
						{
							_username = ((TagAgent)_add).getAgent().getLabel();
							_author = true ;
						}
						else
						{
							String new_author_name = ((TagAgent)_add).getAgent().getLabel() ;
							if(! new_author_name.equalsIgnoreCase(_creator_name)) _username = _username.concat(", " + new_author_name);
						}
					}
					else _to_print.add(_add);
				}
				else if(_add instanceof Judgment)
				{
					if(!_judgment_test)
					{
						_judgment = ((Judgment)_add).getLabel() ;
						_judgment_test = true ;
					}
					else _judgment = _judgment.concat(", " + ((Judgment)_add).getLabel());
				}
				else if(_add instanceof Mood)
				{
					String _mood_label = ((Mood)_add).getLabel();
					_mood = _mood.concat("<img href=" + ((Mood)_add).getRepresentsResource().getEffectiveURI() + "?id=" + ((Mood)_add).getId() + " src=/PortletAnnotation-1.0.0/images/mood_"+ _mood_label.replaceAll("é", "e").replace("è", "e") +".png title=" + _mood_label + " />");
					_mood_test = true ;
				}
				else if(_add instanceof Post)
				{
					if(((Post)_add).getStatus().getLabel().equalsIgnoreCase("reformulation")) _to_print.add(0, _add);
					else _to_print.add(_add);
				}
				else _to_print.add(_add);
			}
		}
		//header
		//String _html = "<div class=resource_header id=resource_header_" + _annotation.getId() + " onclick=\"switchMenu('annotation_content_" + _annotation.getId() + "');\" >"
		String _html = "<div class=resource_header id=resource_header_" + _annotation.getId() + " >"
			+"<table><tr><td>"
			+"<div class=resource_head_left><span class=author>De " + _username + "</span><br/><span class=resource_title>" + _annotation.getLabel() + "</span>" ;
		if(_judgment_test) _html = _html.concat("<br/><span class=judgment_label>Jugement : </span><span class=judgment>" + _judgment + "</span>");
		_html = _html.concat("</div></td>");
		if(_mood_test) _html = _html.concat("<td><div class=mood>" + _mood + "</div></td>");
		_html = _html.concat("<td><div class=nb_replies>" + _nb_replies + " réponses</div></td>");
		_html = _html.concat("</tr></table></div>");
		//content
		_html = _html.concat("<div class=content style=\"display : none ;\" id=annotation_content_" + _annotation.getId() + ">");
		if(!_to_print.isEmpty())
		{
			_html = _html.concat("<div class=annotation_added><div class=added_head>Le contenu de l'annotation : </div>");
			for(Resource _add : _to_print)
			{
				_html = _html.concat(_add.toHTML());
			}
			_html = _html.concat("</div>");
		}
		_html = _html.concat("</div>");
		return _html ;
	}
	
	/**
	 * Gets a short resume of the annotation (less than 100 chars)
	 * @param _annotation
	 * @return
	 */
	public String toShortResume(Annotation _annotation)
	{
		String resume = "";
		if(_annotation.getCreator() != null)
		{
			resume = _annotation.getCreator().getLabel();
			if(resume.length() + 3 < 100) resume = resume.concat(" : ");
		}
		int resume_length = resume.length() ;
		String jgts = "" ;
		String tags = "" ;
		String comment_resume = "" ;
		if(resume_length < 100)
		{
			Collection _added = RETRIEVER_RESOURCES.retrieveAddedForAnnotation(_annotation);
			if(_added != null)
			{
				for(Resource _add : (Collection<Resource>)_added)
				{
					if(! (_add instanceof TagAgent) && !(_add instanceof Mood))
					{
						if(_add instanceof Judgment)
						{
							jgts = ((Judgment)_add).getLabel().concat(", ").concat(jgts);
						}
						else if(_add instanceof Tag)
						{
							if(tags.length() >0 )tags = tags.concat(((Tag)_add).getLabel()).concat(" ,");
							else tags = tags.concat(((Tag)_add).getLabel()) ;
						}
						else if(_add instanceof Post)
						{
							int total_resume_length = resume_length + jgts.length() + tags.length() + 6 + comment_resume.length();
							int total_resume_comment_possible = 100 - total_resume_length ;
							if(total_resume_comment_possible > 0)
							{
								if(((Post)_add).getContent().length() < total_resume_comment_possible)
								{
									comment_resume = comment_resume.concat(", ").concat(((Post)_add).getContent());
								}
								else
								{
									comment_resume = comment_resume.concat(", ").concat(((Post)_add).getContent().substring(0, total_resume_comment_possible)).concat("...") ; 
									break ;
								}
							}
						}
					}
				}
			}
		}
		resume = resume.concat(jgts);
		if(tags.length() > 0) resume = resume.concat("TAGS : ").concat(tags);
		if(comment_resume.length() > 0) resume = resume.concat("Commentaire : ").concat(comment_resume);
		return resume ;
	}
}