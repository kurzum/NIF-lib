package org.nlp2rdf.bean;


import org.nlp2rdf.nif20.NIF20Format;
import org.nlp2rdf.nif21.NIF21Format;

public class NIFContext {

    private String baseURI;

    private int beginIndex;

    private int endIndex;

    public NIFContext(String baseURI, Integer beginIndex, Integer endIndex) {
        this.baseURI = baseURI;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public String context(String format) {
        return String.format(format, baseURI, beginIndex, endIndex);
    }

    public String getCollection() {
        return String.format("%s#collection", baseURI);
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public String getNIF20() {
        return context(NIF20Format.CONTEXT_FORMAT);
    }

    public String getNIF21() {
        return context(NIF21Format.CONTEXT_FORMAT);
    }
}
