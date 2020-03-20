import java.util.ArrayList;
import java.util.List;

public class Document implements DocElement{
    private String p_title;
    private ArrayList<DocElement> p_list  = new ArrayList<>();

    public Document(String title){
        p_title = title;
        p_list = null;
    }
    public ArrayList<DocElement> getElements(){
        return p_list;
    }
    public void setElements(ArrayList<DocElement> arr){
        p_list = arr;
    }
    public void add(DocElement de){
        p_list.add(de);
    }
    void setTitle(String title){
        p_title = title;
    }
    String getTitle(){
        return p_title;
    }

    @Override
    public <T> T accept(TextVisitor<T> visitor) {
        return visitor.visit(this);
    }
}