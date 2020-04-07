import java.util.ArrayList;

public class Document implements DocElement{
    private String title;
    private ArrayList<DocElement> list;// = new ArrayList<DocElement>();

    public Document(String title){
        this.title = title;
        this.list = new ArrayList<DocElement>();
    }

    public ArrayList<DocElement> getElements(){
        return this.list;
    }
    public void setElements(ArrayList<DocElement> arr){
        this.list = arr;//(ArrayList<DocElement>) arr.clone();
    }
    public void add(DocElement de){
        this.list.add(de);
    }
    void setTitle(String title){

        this.title = title;
    }
    String getTitle(){

        return this.title;
    }

    @Override
    public <T> T accept(TextVisitor<T> visitor) {
        return visitor.visit(this);
    }
}