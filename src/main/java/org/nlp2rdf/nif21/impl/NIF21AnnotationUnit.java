package org.nlp2rdf.nif21.impl;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.nif21.NIF21Format;
import org.nlp2rdf.NIFAnnotationUnit;
import org.nlp2rdf.NIFVisitor;

public class NIF21AnnotationUnit implements NIFAnnotationUnit, NIF21Format {

    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }


    public void add(Model model, NIFBean entity) {

        if (model != null && entity != null && entity.isMention()) {
            Resource contextRes = model.getResource(entity.getContext().getNIF21());

            Resource unitRes = model.createResource().addProperty(RDF.type, ResourceFactory.createResource(NIF_PROPERTY_ENTITY_OCCURRENCE));

            unitRes.addProperty(model.createProperty(RDF_PROPERTY_IDENTREF), model.createResource(entity.getTaIdentRef()));

            if (entity.hasTypes()) {
                for (String ref : entity.getTypes()) {
                    unitRes.addProperty(
                            model.createProperty(RDF_PROPERTY_CLASS_REF),
                            model.createResource(ref));
                }

            }
            if (entity.getScore() != null) {
                unitRes.addProperty(model.createProperty(RDF_PROPERTY_CONFIDENCE), model.createTypedLiteral(entity.getScore()));
            }
            unitRes.addProperty(model.createProperty(RDF_PROPERTY_ANNOTATOR), model.createProperty(entity.getAnnotator()));

            contextRes.addProperty(model.createProperty(NIF_PROPERTY_ANNOTATION_UNIT), unitRes);


        }
    }
}