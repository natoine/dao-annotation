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
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONException;

import fr.natoine.controler.resource.DAOResource;
import fr.natoine.model_annotation.Annotation;
import fr.natoine.model_annotation.AnnotationStatus;
import fr.natoine.model_annotation.Cardinalite;
import fr.natoine.model_annotation.DescripteurAnnotationStatus;
import fr.natoine.model_annotation.Judgment;
import fr.natoine.model_annotation.Mood;
import fr.natoine.model_annotation.Post;
import fr.natoine.model_annotation.Tag;
import fr.natoine.model_resource.Resource;
import fr.natoine.model_resource.URI;
import fr.natoine.model_user.Agent;
import fr.natoine.stringOp.StringOp;

public class DAOAnnotation 
{
	private EntityManagerFactory emf = null ;
	
	public DAOAnnotation(EntityManagerFactory _emf)
	{
		emf = _emf ;
	}
	
	//CreateAnnotationStatus
	/**
	 * Creates an AnnotationStatus named commentaire that describes the fact to let a simpleText named "commentaire" associated to an infinite set of selected resources.
	 * @return
	 */
	public boolean createSimpleCommentStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Post.class.getSimpleName());
		_instance_descripteur_2.setStatus("commentaire");
		_instance_descripteur_2.setPossibleValues(null);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		//if(createAnnotationStatus("commentaire", "ce status est celui d'un simple commentaire", "#F3F781", descripteur))
		if(createAnnotationStatus("commentaire", "ce status est celui d'un simple commentaire", _color, descripteur))
		{
			//CreatePostStatus _creator_stt = new CreatePostStatus();
			DAOPost _creator_stt = new DAOPost(emf);
			_creator_stt.createPostStatus("commentaire", "un simple commentaire");
			return true;
		}
		else return false ;
	}
	/**
	 * Creates an AnnotationStatus named tag that describes the fact to let a simpleTag associated to an infinite set of selected resources.
	 * @return
	 */
	public boolean createSimpleTagStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Tag.class.getSimpleName());
		_instance_descripteur_2.setStatus("tag");
		_instance_descripteur_2.setPossibleValues(null);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		//return this.createAnnotationStatus("tag", "ce status est celui d'un simple tag", "#A9A9F5", descripteur);
		return this.createAnnotationStatus("tag", "ce status est celui d'un simple tag", _color, descripteur);
	}
	/**
	 * Creates an AnnotationStatus named Spam that describes the fact to signal a Spam
	 * @return
	 */
	public boolean createSpamStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_2.setStatus("jugement");
		String[] _values = new String[1];
		_values[0] = "C'est déjà dit" ;
		_instance_descripteur_2.setPossibleValues(_values);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		//commentaire
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("justification");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//return this.createAnnotationStatus("Spam", "permet de signaler un contenu repris, déjà dit ailleurs", "#A4A4A4", descripteur);
		return this.createAnnotationStatus("Spam", "permet de signaler un contenu repris, déjà dit ailleurs", _color, descripteur);
	}
	/**
	 * Creates an AnnotationStatus named Troll that describes the fact to signal a Troll
	 * @return
	 */
	public boolean createTrollStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_2.setStatus("jugement");
		String[] _values = new String[1];
		_values[0] = "Troll" ;
		_instance_descripteur_2.setPossibleValues(_values);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		//commentaire
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("justification");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//return this.createAnnotationStatus("Troll", "permet de signaler un contenu provocateur", "#00FF80", descripteur);
		return this.createAnnotationStatus("Troll", "permet de signaler un contenu provocateur", _color, descripteur);
	}
	/**
	 * Creates an AnnotationStatus named Flame that describes the fact to signal a Flame
	 * @return
	 */
	public boolean createFlameStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_2.setStatus("jugement");
		String[] _values = new String[1];
		_values[0] = "Flame" ;
		_instance_descripteur_2.setPossibleValues(_values);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		//commentaire
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("justification");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//return this.createAnnotationStatus("Flame", "permet de signaler un contenu malveillant", "#FF8000",descripteur);
		return this.createAnnotationStatus("Flame", "permet de signaler un contenu malveillant", _color,descripteur);
	}
	/**
	 * Creates an AnnotationStatus named Accord that describes the fact to agree to the annotated resource
	 * @return
	 */
	public boolean createAgreeStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_2.setStatus("jugement");
		String[] _values = new String[1];
		_values[0] = "je suis d'accord" ;
		_instance_descripteur_2.setPossibleValues(_values);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		//commentaire
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("justification");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//return this.createAnnotationStatus("Accord", "ce status est celui d'un accord", "#D0F5A9", descripteur);
		return this.createAnnotationStatus("Accord", "ce status est celui d'un accord", _color, descripteur);
	}
	/**
	 * Creates an AnnotationStatus named Désaccord that describes the fact to disagree to the annotated resource
	 * @return
	 */
	public boolean createDisAgreeStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_2.setStatus("jugement");
		String[] _values = new String[1];
		_values[0] = "je ne suis pas d'accord" ;
		_instance_descripteur_2.setPossibleValues(_values);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		//commentaire
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("justification");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//return this.createAnnotationStatus("Désaccord", "ce status est celui d'un désaccord", "#FE2E2E",descripteur);
		return this.createAnnotationStatus("Désaccord", "ce status est celui d'un désaccord", _color, descripteur);
	}
	/**
	 * Creates an AnnotationStatus named A clarifier that describes the fact to don't understand the annotated resource
	 * @return
	 */
	public boolean createClarifyStatus(String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_2.setStatus("jugement");
		String[] _values = new String[1];
		_values[0] = "je ne comprends pas" ;
		_instance_descripteur_2.setPossibleValues(_values);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		//commentaire
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("justification");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//return this.createAnnotationStatus("A clarifier", "exprime le fait que je ne comprends pas, qu'il faut clarifier, reformuler", "#2E2EFE", descripteur);
		return this.createAnnotationStatus("A clarifier", "exprime le fait que je ne comprends pas, qu'il faut clarifier, reformuler", _color, descripteur);
	}
	
	/**
	 * Creates an AnnotationStatus named annotation that describes a composit annotation using Post for Reformulation, Judgments, Moods and another Post for a comment  
	 * @param uri4tags the URI to represent all the collection of tags in your system
	 * @return
	 */
	public boolean createComplexAnnotationSample(String uri4tags, String _color)
	{
		JSONArray descripteur = new JSONArray();
		DescripteurAnnotationStatus _instance_descripteur_1 = new DescripteurAnnotationStatus();
		DescripteurAnnotationStatus _instance_descripteur_2 = new DescripteurAnnotationStatus();
		DescripteurAnnotationStatus _instance_descripteur_3 = new DescripteurAnnotationStatus();
		DescripteurAnnotationStatus _instance_descripteur_4 = new DescripteurAnnotationStatus();
		DescripteurAnnotationStatus _instance_descripteur_5 = new DescripteurAnnotationStatus();
		
		//Ressources annotées
		_instance_descripteur_1.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ANNOTATED);
		_instance_descripteur_1.setClassName(Resource.class.getSimpleName());
		_instance_descripteur_1.setStatus("sélection");
		_instance_descripteur_1.setPossibleValues(null);
		Cardinalite cardinalite_selection = new Cardinalite();
		cardinalite_selection.setInfinite(true);
		_instance_descripteur_1.setCardinalite(cardinalite_selection);
		
		//Reformulation
		_instance_descripteur_2.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_2.setClassName(Post.class.getSimpleName());
		_instance_descripteur_2.setStatus("reformulation");
		_instance_descripteur_2.setPossibleValues(null);
		Cardinalite cardinalite_ajout = new Cardinalite();
		cardinalite_ajout.setInfinite(false);
		cardinalite_ajout.setMax(false);
		cardinalite_ajout.setValue(1);
		_instance_descripteur_2.setCardinalite(cardinalite_ajout);
		
		//Jugements
		_instance_descripteur_3.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_3.setClassName(Judgment.class.getSimpleName());
		_instance_descripteur_3.setStatus("jugement");
		String[] jugements = new String[3];
		jugements[0] = "je suis d'accord" ;
		jugements[1] = "je ne suis pas d'accord" ;
		jugements[2] = "je m'interroge" ;
		_instance_descripteur_3.setPossibleValues(jugements);
		Cardinalite cardinalite_jugement = new Cardinalite();
		cardinalite_jugement.setInfinite(false);
		cardinalite_jugement.setMax(false);
		cardinalite_jugement.setValue(1);
		_instance_descripteur_3.setCardinalite(cardinalite_jugement);
		//humeurs
		_instance_descripteur_4.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_4.setClassName(Mood.class.getSimpleName());
		_instance_descripteur_4.setStatus("humeur");
		String[] moods = new String[4];
		moods[0] = "sceptique" ;
		moods[1] = "indifférent" ;
		moods[2] = "furieux" ;
		moods[3] = "satisfait" ;
		_instance_descripteur_4.setPossibleValues(moods);
		Cardinalite cardinalite_moods = new Cardinalite();
		cardinalite_moods.setInfinite(false);
		cardinalite_moods.setMax(false);
		cardinalite_moods.setValue(1);
		_instance_descripteur_4.setCardinalite(cardinalite_moods);
		
		//commentaire
		_instance_descripteur_5.setType(DescripteurAnnotationStatus.AnnotatedOrAdded.ADDED);
		_instance_descripteur_5.setClassName(Post.class.getSimpleName());
		_instance_descripteur_5.setStatus("commentaire");
		_instance_descripteur_5.setPossibleValues(null);
		Cardinalite cardinalite_comment = new Cardinalite();
		cardinalite_comment.setInfinite(false);
		cardinalite_comment.setMax(false);
		cardinalite_comment.setValue(1);
		_instance_descripteur_5.setCardinalite(cardinalite_comment);
		
		descripteur.put(_instance_descripteur_1.toJSONObject());
		descripteur.put(_instance_descripteur_2.toJSONObject());
		descripteur.put(_instance_descripteur_3.toJSONObject());
		descripteur.put(_instance_descripteur_4.toJSONObject());
		descripteur.put(_instance_descripteur_5.toJSONObject());
		//si le status est bien créé, on va créer tous les autres éléments nécessaires (Jugements, moods, simpletextStatus)
		//if(this.createAnnotationStatus("annotation", "ce status est celui d'une annotation permettant de reformuler, d'exprimer son humeur et un jugement et d'ajouter un commentaire", "#DFB66E",descripteur))
		if(this.createAnnotationStatus("annotation", "ce status est celui d'une annotation permettant de reformuler, d'exprimer son humeur et un jugement et d'ajouter un commentaire", _color, descripteur))
		{
			//CreateTag _tag_creator = new CreateTag();
			DAOTag _tag_creator = new DAOTag(emf);
			//CreatePostStatus _creator_stt = new CreatePostStatus();
			DAOPost _creator_stt = new DAOPost(emf);
			//CreateUri _creator_uri = new CreateUri();
			DAOResource _creator_uri = new DAOResource(emf);
			URI representsResource = _creator_uri.createAndGetURI(uri4tags);
			_creator_stt.createPostStatus("reformulation", "une reformulation de ce que vous avez annoté");
			_creator_stt.createPostStatus("commentaire", "un simple commentaire");
			//PostStatus _status_definition_jugement = _creator_stt.createAndGetPostStatusDefinitionJudgment();
			//PostStatus _status_definition_mood = _creator_stt.createAndGetPostStatusDefinitionMood();
			//création des jugements
			_tag_creator.createJudgment(jugements[0], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			_tag_creator.createJudgment(jugements[1], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			_tag_creator.createJudgment(jugements[2], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			//création des humeurs
			_tag_creator.createMood(moods[0], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			_tag_creator.createMood(moods[1], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			_tag_creator.createMood(moods[2], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			_tag_creator.createMood(moods[3], "CreateAnnotationStatus.createComplexAnnotationSample", representsResource);
			return true ;
		}
		//sinon on retourne false
		else return false ;
	}
	
	public boolean createAnnotationStatus(String label, String comment, String color, String descripteur)
	{
		JSONArray json_descripteur;
		try 
		{
			json_descripteur = new JSONArray(descripteur);
			return createAnnotationStatus(label, comment, color, json_descripteur);
		}
		catch (JSONException e) 
		{
			System.out.println("[DAOAnnotation.createAnnotatioStatus] descripteur is not a JSONArray : " + descripteur) ;
			e.printStackTrace();
			return false ;
		}
	}
	
	/**
	 * Creates an AnnotationStatus
	 * @param label
	 * @param comment
	 * @return
	 */
	public boolean createAnnotationStatus(String label, String comment , String color , JSONArray descripteur)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			comment = StringOp.deleteBlanks(comment);
			AnnotationStatus _as = new AnnotationStatus();
			_as.setComment(comment);
			_as.setLabel(label);
			_as.setDescripteur(descripteur);
			_as.setColor(color);
		//	EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try
	        {
	        	tx.begin();
	        	AnnotationStatus annotationstatus = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, label).getSingleResult();
	        	tx.commit();
	        	if(annotationstatus != null)
	        	{
	        		System.out.println("[CreateAnnotationStatus.createAnnotationStatus] this status already exists. Status : " + label) ;
	        		return false;
	        	}
	        }
	        catch(Exception e)
	        {
	        	tx.rollback();
	        	System.out.println("[CreateAnnotationStatus.createAnnotationStatus] this status doesn't already exist. Status : " + label + " is gonna be created.");
	        }
	        try
	        {
		        tx.begin();
		        em.persist(_as);
		        tx.commit();
		    //    em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreateAnnotationStatus.createAnnotationStatus] fails to create AnnotationStatus"
	        			+ " label : " + label
	        			+ " comment : " + comment
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        //	em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreateAnnotationStatus.createAnnotationStatus] unable to persist AnnotationStatus"
					+ " not a valid label : " + label);
			return false;
		}
	}
	
	public boolean createAnnotationStatusChild(String label, String comment, String color, long father_id , String descripteur)
	{
		JSONArray json_array_descripteur = null;
		try {
			json_array_descripteur = new JSONArray(descripteur);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createAnnotationStatusChild(label, comment, color, father_id, json_array_descripteur);
	}
	
	public boolean createAnnotationStatusChild(String label, String comment, String color, long father_id , JSONArray descripteur)
	{
		AnnotationStatus father = retrieveAnnotationStatus(father_id);
		return createAnnotationStatusChild(label, comment, color, father, descripteur);
	}
	
	/**
	 * Creates an AnnotationStatus that specifies another AnnotationStatus
	 * @param label
	 * @param comment
	 * @param father
	 * @return
	 */
	public boolean createAnnotationStatusChild(String label, String comment, String color, AnnotationStatus father , JSONArray descripteur)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			comment = StringOp.deleteBlanks(comment);
			AnnotationStatus _as = new AnnotationStatus();
			_as.setComment(comment);
			_as.setLabel(label);
			_as.setFather(father);
			_as.setDescripteur(descripteur);
			_as.setColor(color);
		//	EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try
	        {
		        tx.begin();
		        if(father.getId() != null)
				{
		        	AnnotationStatus _synchro_father = em.find(AnnotationStatus.class, father.getId());
					if(_synchro_father != null) _as.setFather(_synchro_father);
				}
		        em.persist(_as);
		        tx.commit();
		  //      em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreateAnnotationStatus.createAnnotationStatus] fails to create AnnotationStatus"
	        			+ " label : " + label
	        			+ " comment : " + comment
	        			+ " cause : " + e.getMessage());
	        	tx.rollback();
	        //	em.close();
	        	return false;
	        }
		}
		else
		{
			System.out.println("[CreateAnnotationStatus.createAnnotationStatus] unable to persist AnnotationStatus"
					+ " not a valid label : " + label);
			return false;
		}
	}
	
	//RetrieveAnnotationStatus
	/**
	 * Retrieves an AnnotationStatus in the database according to the specified label
	 * @param label
	 * @return an AnnotationStatus that may be a new UriStatus with no value.
	 */
	public AnnotationStatus retrieveAnnotationStatus(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			AnnotationStatus annotationstatus = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, label).getSingleResult();
			tx.commit();
			return annotationstatus;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotationStatus.retrieveAnnotationStatus] unable to retrieve AnnotationStatus"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new AnnotationStatus();
		}
	}
	/**
	 * Retrieves all the existing AnnotationStatus
	 * @return
	 */
	public List<AnnotationStatus> retrieveAnnotationStatus()
	{
	//	EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			List<AnnotationStatus> annotationstatus = em.createQuery("from AnnotationStatus").getResultList();
			tx.commit();
			return annotationstatus;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotationStatus.retrieveAnnotationStatus] unable to retrieve AnnotationStatus"
					+ " cause : " + e.getMessage());
			return new ArrayList<AnnotationStatus>();
		}
	}
	/**
	 * Retrieves an annotationStatus
	 * @param id
	 * @return
	 */
	public AnnotationStatus retrieveAnnotationStatus(long id)
	{
	//	EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			AnnotationStatus _synchro= em.find(AnnotationStatus.class, id);
			tx.commit();
			if(_synchro != null) return _synchro ;
			System.out.println("[RetrieveAnnotationStatus.retrieveAnnotationStatus] unable to retrieve AnnotationStatus"
					+ " id : " + id );
			return new AnnotationStatus();
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotationStatus.retrieveAnnotationStatus] unable to retrieve AnnotationStatus"
					+ " id : " + id
					+ " cause : " + e.getMessage());
			return new AnnotationStatus();
		}
	}
	
	//CreateAnnotation
	/**
	 * Creates an Annotation
	 * @param label
	 * @param context_creation
	 * @param hTMLContent
	 * @param access
	 * @param representsResource
	 * @param status
	 * @param added
	 * @param annotated
	 * @return
	 */
	public boolean createAnnotation(String label, String context_creation, URI access, URI representsResource, AnnotationStatus status, Collection<Resource> added, Collection<Resource> annotated, Collection<URI> annotatedURIs, Agent _creator)
	{
		label = StringOp.deleteBlanks(label);
		if(!StringOp.isNull(label))
		{
			Annotation _annotation = new Annotation();
			_annotation.setContextCreation(context_creation);
			_annotation.setCreation(new Date());
			_annotation.setLabel(label);
			_annotation.setAccess(access);
			_annotation.setRepresentsResource(representsResource);
			_annotation.setStatus(status);
			_annotation.setCreator(_creator);
			//_annotation.setAdded(added);
			//_annotation.setAnnotated(annotated);
			//_annotation.setAnnotatedURIs(annotatedURIs);
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try{
		        tx.begin();
		        if(representsResource.getId() != null)
				{
					URI _synchro_represents_resource = em.find(URI.class, representsResource.getId());
					if(_synchro_represents_resource != null) _annotation.setRepresentsResource(_synchro_represents_resource);
				}
		        if(access.getId() != null)
				{
					URI _synchro_access = em.find(URI.class, access.getId());
					if(_synchro_access != null) _annotation.setAccess(_synchro_access);
				}
		        if(status.getId() != null)
				{
		        	AnnotationStatus _synchro_status = em.find(AnnotationStatus.class, status.getId());
					if(_synchro_status != null) _annotation.setStatus(_synchro_status);
				}
		        if(_creator != null && _creator.getId() != null)
		        {
		        	Agent _synchro_agent = em.find(_creator.getClass(), _creator.getId());
		        	if(_synchro_agent != null) _annotation.setCreator(_synchro_agent);
		        }
		        Collection<Resource> _synchro_added = new ArrayList<Resource>();
		        for(Resource _to_add : added)
		        {
		        	if(_to_add.getId() != null)
		        	{
		        		Resource _synchro_to_add = em.find(_to_add.getClass(), _to_add.getId());
		        		if(_synchro_to_add != null) _synchro_added.add(_synchro_to_add);
		        	}
		        	else _synchro_added.add(_to_add);
		        }
		        _annotation.setAdded(_synchro_added);
		        Collection<Resource> _synchro_annotated = new ArrayList<Resource>();
		        for(Resource _to_annotate : annotated)
		        {
		        	if(_to_annotate.getId() != null)
		        	{
		        		Resource _synchro_to_annotate = em.find(_to_annotate.getClass(), _to_annotate.getId());
		        		if(_synchro_to_annotate != null) _synchro_annotated.add(_synchro_to_annotate);
		        	}
		        	else _synchro_annotated.add(_to_annotate);
		        }
		        _annotation.setAnnotated(_synchro_annotated);
		        Collection<URI> synchro_annotatedURIs = new ArrayList<URI>();
		        for(URI _to_annotate : annotatedURIs)
		        {
		        	if(_to_annotate.getId() != null)
		        	{
		        		URI _synchro_to_annotate = em.find(_to_annotate.getClass(), _to_annotate.getId());
		        		if(_synchro_to_annotate != null) 
		        		{
		        			//empêcher qu'une même URI soit ajoutée plusieurs fois à une même annotation
		        			if(! synchro_annotatedURIs.contains(_synchro_to_annotate)) synchro_annotatedURIs.add(_synchro_to_annotate);
		        		}
		        	}
		        	else synchro_annotatedURIs.add(_to_annotate);
		        }
		        _annotation.setAnnotatedURIs(synchro_annotatedURIs);
		        em.persist(_annotation);
		        tx.commit();
		        em.close();
		        return true ;
	        }
	        catch(Exception e)
	        {
	        	System.out.println("[CreateAnnotation.createAnnotation] fails to create annotation"
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
			System.out.println("[CreateAnnotation.createAnnotation] unable to persist annotation"
					+ " not a valid label : " + label
					);
			return false;
		}
	}
	
	//RetrieveAnnotation
	/**
	 * Retrieves a list of Annotation according to the specified label
	 * @param label
	 * @return
	 */
	public List<Annotation> retrieveAnnotation(String label)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			//List docs = em.createQuery("from Annotation where label = '" + label + "'").getResultList();
			List<Annotation> docs = ((List<Annotation>) em.createQuery("from Annotation where label = ?").setParameter(1, label).getResultList());
			tx.commit();
			return docs;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotation] unable to retrieve Annotation"
					+ " label : " + label
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	
	/**
	 * Retrieves all the annotation about a specified Resource, through its URI
	 * @param _url
	 * @return
	 */
	public List<Annotation> retrieveAnnotations(String _url)
	{
		return retrieveAnnotations(_url , true);
	}
	/**
	 * Retrieves all the annotations about a specified Resource, by ascendant or descendant chronological order
	 * @param _url
	 * @param asc
	 * @return
	 */
	public List<Annotation> retrieveAnnotations(String _url , boolean asc)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
			//	//em.close();
				System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return new ArrayList<Annotation>();
			}
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotated as annotated inner join annotated.representsResource as uri where uri=?").setParameter(1, _uri).getResultList();
			String order_by_clause = " order by annotation.id desc" ;
			if(asc) order_by_clause = " order by annotation.id asc" ;
			List<Annotation> annotations = ((List<Annotation>) em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=?" + order_by_clause).setParameter(1, _uri).getResultList());
			tx.commit();
			//em.close();
			return annotations;
		}
		catch(Exception e)
		{
			//tx.commit();
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	/**
	 * Retrieves a specified quantity of Annotations associated to a Resource specified by its URL
	 * @param _url
	 * @param asc
	 * @param first_indice
	 * @param max_results
	 * @return
	 */
	public List<Annotation> retrieveAnnotations(String _url , boolean asc, int first_indice, int max_results)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
			//	//em.close();
				System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return new ArrayList<Annotation>();
			}
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotated as annotated inner join annotated.representsResource as uri where uri=?").setParameter(1, _uri).getResultList();
			String order_by_clause = " order by annotation.id desc" ;
			if(asc) order_by_clause = " order by annotation.id asc" ;
			Query _query = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=?" + order_by_clause).setParameter(1, _uri) ;
 			_query.setFirstResult(first_indice);
 			_query.setMaxResults(max_results);
			List<Annotation> annotations = _query.getResultList();
			tx.commit();
			//em.close();
			return annotations;
		}
		catch(Exception e)
		{
			//tx.commit();
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	/**
	 * Retrieves all the annotations associated to a Resource specified by its URL, grouped by annotationStatus
	 * @param _url
	 * @param asc
	 * @return
	 */
	public List<Annotation> retrieveAnnotationsGroupByStatus(String _url , boolean asc)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
			//	//em.close();
				System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return new ArrayList<Annotation>();
			}
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotated as annotated inner join annotated.representsResource as uri where uri=?").setParameter(1, _uri).getResultList();
			String order_by_clause = " annotation.id desc" ;
			if(asc) order_by_clause = " annotation.id asc" ;
			List<Annotation> annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? order by annotation.status," + order_by_clause ).setParameter(1, _uri).getResultList();
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? group by annotation.status" ).setParameter(1, _uri).getResultList();
			tx.commit();
			//em.close();
			return annotations;
		}
		catch(Exception e)
		{
			//tx.commit();
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	/**
	 * Retrieves a specified quantity of annotations associated to a Resource specified by its URL and ordered by AnnotationStatus
	 * @param _url
	 * @param asc
	 * @param first_indice
	 * @param max_results
	 * @return
	 */
	public List<Annotation> retrieveAnnotationsGroupByStatus(String _url , boolean asc, int first_indice, int max_results)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
			//	//em.close();
				System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return new ArrayList<Annotation>();
			}
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotated as annotated inner join annotated.representsResource as uri where uri=?").setParameter(1, _uri).getResultList();
			String order_by_clause = " annotation.id desc" ;
			if(asc) order_by_clause = " annotation.id asc" ;
			Query _query = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? order by annotation.status," + order_by_clause ).setParameter(1, _uri) ;
			_query.setFirstResult(first_indice);
 			_query.setMaxResults(max_results);
			List<Annotation> annotations = _query.getResultList();
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? group by annotation.status" ).setParameter(1, _uri).getResultList();
			tx.commit();
			//em.close();
			return annotations;
		}
		catch(Exception e)
		{
			//tx.commit();
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	/**
	 * Retrieves all the annotations associated to a specified Resource through its URL, ordered by FirstAuthor
	 * @param _url
	 * @param asc
	 * @return
	 */
	public List<Annotation> retrieveAnnotationsGroupByFirstAuthor(String _url , boolean asc)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
			//	//em.close();
				System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return new ArrayList<Annotation>();
			}
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotated as annotated inner join annotated.representsResource as uri where uri=?").setParameter(1, _uri).getResultList();
			String order_by_clause = " annotation.id desc" ;
			if(asc) order_by_clause = " annotation.id asc" ;
			List<Annotation> annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? order by annotation.creator," + order_by_clause ).setParameter(1, _uri).getResultList();
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? group by annotation.status" ).setParameter(1, _uri).getResultList();
			tx.commit();
			//em.close();
			return annotations;
		}
		catch(Exception e)
		{
			//tx.commit();
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	/**
	 * Retrieves a specified quantity of annotations associated to a specified Resource through its URL, ordered by FirstAuthor
	 * @param _url
	 * @param asc
	 * @param first_indice
	 * @param max_results
	 * @return
	 */
	public List<Annotation> retrieveAnnotationsGroupByFirstAuthor(String _url , boolean asc, int first_indice, int max_results)
	{
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
			//	//em.close();
				System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return new ArrayList<Annotation>();
			}
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotated as annotated inner join annotated.representsResource as uri where uri=?").setParameter(1, _uri).getResultList();
			String order_by_clause = " annotation.id desc" ;
			if(asc) order_by_clause = " annotation.id asc" ;
			Query _query = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? order by annotation.creator," + order_by_clause ).setParameter(1, _uri) ;
			_query.setFirstResult(first_indice);
 			_query.setMaxResults(max_results);
			List<Annotation> annotations = _query.getResultList();
			//List annotations = em.createQuery("select distinct annotation from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? group by annotation.status" ).setParameter(1, _uri).getResultList();
			tx.commit();
			//em.close();
			return annotations;
		}
		catch(Exception e)
		{
			//tx.commit();
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.retrieveAnnotations] unable to retrieve Annotations"
					+ " cause : " + e.getMessage());
			return new ArrayList<Annotation>();
		}
	}
	/**
	 * Retrieves all the annotated Resources of an Annotation
	 * @param _annotation
	 * @return
	 */
	public Collection<Resource> retrieveAnnotatedForAnnotation(Annotation _annotation)
	{
		if(_annotation.getId() == null) 
		{
			System.out.println("[RetrieveAnnotation.retrieveAnnotatedForAnnotation] unable to retrieve annotated"
					+ " label : " + _annotation.getLabel()
					+ " cause : _annotation doesn't exist" );
			return new ArrayList<Resource>();
		}
		else
		{	
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			//EntityTransaction tx = em.getTransaction();
			_annotation = (Annotation)em.find(_annotation.getClass(), _annotation.getId());
			Collection<Resource> _annotated = _annotation.getAnnotated();
			return _annotated;
		}
	}
	/**
	 * Retrieves all the added Resources of an Annotation
	 * @param _annotation
	 * @return
	 */
	public Collection<Resource> retrieveAddedForAnnotation(Annotation _annotation)
	{
		if(_annotation.getId() == null) 
		{
			System.out.println("[RetrieveAnnotation.retrieveddedForAnnotation] unable to retrieve added"
					+ " label : " + _annotation.getLabel()
					+ " cause : _annotation doesn't exist" );
			return new ArrayList<Resource>();
		}
		else
		{	
			//EntityManagerFactory emf = this.setEMF();
			EntityManager em = emf.createEntityManager();
			//EntityTransaction tx = em.getTransaction();
			_annotation = (Annotation)em.find(_annotation.getClass(), _annotation.getId());
			Collection<Resource> _added = _annotation.getAdded();
			return _added;
		}
	}
	/**
	 * Retrieves the Resource in the database with the specified id.
	 * @param id
	 * @return a Resource that may be empty
	 */
	public Resource retrieveResource(long id)
	{
		//TODO move this method elsewhere, it's here because extension of Resource Class are not queried if it were in controler-resource package
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			Resource resource = (Resource)em.createQuery("from Resource where id = ?").setParameter(1, id).getSingleResult();
			tx.commit();
			////em.close();
			return resource;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveResource.retrieveResource] unable to retrieve Resource"
					+ " id : " + id
					+ " cause : " + e.getMessage());
			return new Resource();
		}
	}
	/**
	 * Computes the number of annotations for associated to a specified Resource through its url
	 * @param _url
	 * @return
	 */
	public long computeNbAnnotations(String _url)
	{
		long nb = 0 ;
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
				System.out.println("[RetrieveAnnotation.computeNbAnnotations] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url);
				return nb;
			}
			Object nb_annotations = em.createQuery(" select count(distinct annotation.id) from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=?").setParameter(1, _uri).getSingleResult();
			//System.out.println("[RetrieveAnnotation.computeNbAnnotations] nb_annotations : " + nb_annotations + " classe : " + nb_annotations.getClass().getSimpleName());
			tx.commit();
			if(nb_annotations instanceof Long)
			{
				nb = ((Long)nb_annotations).longValue() ;
			}
			return nb;
		}
		catch(Exception e)
		{
			tx.rollback();
			return nb;
		}
	}
	/**
	 * Tests if an agent has expressed an opinion (agree or disagree) on a resource
	 * @param _creator
	 * @param _url_to_test
	 * @return
	 */
	public boolean agreementExpressed(Agent _creator, String _url_to_test)
	{
		long nb = 0 ;
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url_to_test).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
				System.out.println("[RetrieveAnnotation.agreementExpressed] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url_to_test);
				return false;
			}
			AnnotationStatus status_agree = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, "Accord").getSingleResult();
			AnnotationStatus status_disagree = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, "Désaccord").getSingleResult();
			AnnotationStatus status_clarify = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, "A clarifier").getSingleResult();
			//if(status_agree != null)
			if(status_agree != null || status_disagree!=null || status_clarify!=null)
			{	
				Object nb_annotations_agree = em.createQuery(" select count(distinct annotation.id) from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? and annotation.creator=? and (annotation.status=? or annotation.status=? or annotation.status=?)")
					.setParameter(1, _uri)
					.setParameter(2, _creator)
					.setParameter(3, status_agree)
					.setParameter(4, status_disagree)
					.setParameter(5, status_clarify)
					.getSingleResult();
				if(nb_annotations_agree instanceof Long)
				{
					nb = ((Long)nb_annotations_agree).longValue() ;
				}
			}
			tx.commit();
			if(nb > 0) return true ;
			else return false ;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.agreementExpressed] unable to retrieve Annotations"
					+ " on url : " + _url_to_test
					+ " cause : " + e.getMessage());
			return false; //to prevent to express its opinion if the system fails
		}
	}
	
	/**
	 * Tests if an agent has expressed a Flame on a resource
	 * @param _creator
	 * @param _url_to_test
	 * @return
	 */
	public boolean flameExpressed(Agent _creator, String _url_to_test)
	{
		long nb = 0 ;
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url_to_test).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
				System.out.println("[RetrieveAnnotation.flameExpressed] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url_to_test);
				return false;
			}
			AnnotationStatus status_flame = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, "Flame").getSingleResult();
			if(status_flame != null)
			{	
				Object nb_annotations_flame = em.createQuery(" select count(distinct annotation.id) from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? and annotation.creator=? and annotation.status=?")
					.setParameter(1, _uri)
					.setParameter(2, _creator)
					.setParameter(3, status_flame)
					.getSingleResult();
				if(nb_annotations_flame instanceof Long)
				{
					nb = ((Long)nb_annotations_flame).longValue() ;
				}
			}
			tx.commit();
			if(nb > 0) return true ;
			else return false ;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.flameExpressed] unable to retrieve Annotations"
					+ " on url : " + _url_to_test
					+ " cause : " + e.getMessage());
			return false; //to prevent to express its opinion if the system fails
		}
	}
	
	/**
	 * Tests if an agent has expressed a Trolling on a resource
	 * @param _creator
	 * @param _url_to_test
	 * @return
	 */
	public boolean trollExpressed(Agent _creator, String _url_to_test)
	{
		long nb = 0 ;
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url_to_test).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
				System.out.println("[RetrieveAnnotation.trollExpressed] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url_to_test);
				return false;
			}
			AnnotationStatus status_troll = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, "Troll").getSingleResult();
			if(status_troll != null)
			{	
				Object nb_annotations_troll = em.createQuery(" select count(distinct annotation.id) from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? and annotation.creator=? and annotation.status=?")
					.setParameter(1, _uri)
					.setParameter(2, _creator)
					.setParameter(3, status_troll)
					.getSingleResult();
				if(nb_annotations_troll instanceof Long)
				{
					nb = ((Long)nb_annotations_troll).longValue() ;
				}
			}
			tx.commit();
			if(nb > 0) return true ;
			else return false ;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.trollExpressed] unable to retrieve Annotations"
					+ " on url : " + _url_to_test
					+ " cause : " + e.getMessage());
			return false; //to prevent to express its opinion if the system fails
		}
	}
	
	/**
	 * Tests if an agent has expressed a Spaming on a resource
	 * @param _creator
	 * @param _url_to_test
	 * @return
	 */
	public boolean spamExpressed(Agent _creator, String _url_to_test)
	{
		long nb = 0 ;
		//EntityManagerFactory emf = this.setEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			URI _uri = (URI) em.createQuery("from URI where effectiveURI = ?").setParameter(1, _url_to_test).getSingleResult();
			if(_uri == null) 
			{
				tx.commit();
				System.out.println("[RetrieveAnnotation.spamExpressed] unable to retrieve Annotations"
						+ " cause : there is no uri " + _url_to_test);
				return false;
			}
			AnnotationStatus status_spam = (AnnotationStatus) em.createQuery("from AnnotationStatus where label = ?").setParameter(1, "Spam").getSingleResult();
			if(status_spam != null)
			{	
				Object nb_annotations_troll = em.createQuery(" select count(distinct annotation.id) from Annotation as annotation inner join annotation.annotatedURIs as uri where uri=? and annotation.creator=? and annotation.status=?")
					.setParameter(1, _uri)
					.setParameter(2, _creator)
					.setParameter(3, status_spam)
					.getSingleResult();
				if(nb_annotations_troll instanceof Long)
				{
					nb = ((Long)nb_annotations_troll).longValue() ;
				}
			}
			tx.commit();
			if(nb > 0) return true ;
			else return false ;
		}
		catch(Exception e)
		{
			tx.rollback();
			//em.close();
			System.out.println("[RetrieveAnnotation.spamExpressed] unable to retrieve Annotations"
					+ " on url : " + _url_to_test
					+ " cause : " + e.getMessage());
			return false; //to prevent to express its opinion if the system fails
		}
	}
}