package skm.android.ViewModle.ViewModles;

import skm.android.ViewModle.ViewModles.base.ViewModleQuestion;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: 0884588
 * Date: 07/02/12
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class ResultBase {

    public String message;
    public Vector<ViewModleQuestion> questions;
    public Vector<ViewModleQuestion> getResults(){
       Vector<ViewModleQuestion> result = new Vector<ViewModleQuestion>();
       for(ViewModleQuestion question : questions) result.add(question);
       return  result;
   }

    public abstract boolean getResult();

}
