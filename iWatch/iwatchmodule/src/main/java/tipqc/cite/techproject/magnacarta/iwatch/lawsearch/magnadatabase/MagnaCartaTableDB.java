package tipqc.cite.techproject.magnacarta.iwatch.lawsearch.magnadatabase;

/**
 * Created by Benjoe on 2/23/14.
 */
public class MagnaCartaTableDB {


    String chapter = null;
    String lawdescription = null;
    String section = null;
    String subsection = null;
    String keywordtag = null;
    String keywordeng = null;



    public String getChapter(){
        return chapter;
    }

    public void setChapter(String chapter){
        this.chapter = chapter;
    }

    public String getLawdescription(){
        return lawdescription;
    }

    public void setLawdescription(String lawdescription){

        this.lawdescription = lawdescription;
    }

    public String getSection(){
        return section;
    }

    public void setSection(String section){
        this.section = section;
    }

    public String getSubsection(){
        return subsection;
    }

    public void setSubsection(String subsection){
        this.subsection = subsection;
    }

    public String getKeywordtag(){
        return  keywordtag;
    }

    public void setKeywordtag(String keywordtag){
        this.keywordtag = keywordtag;
    }

    public String getKeywordeng(){
        return keywordeng;
    }

    public void setKeywordeng(String keywordeng){
        this.keywordeng = keywordeng;
    }
}
