package skm.android.ViewModle.ViewModles.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import gueei.binding.Command;
import gueei.binding.DependentObservable;
import gueei.binding.observables.IntegerObservable;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 02/01/12
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public abstract class ViewModelManagerBase extends ViewModleQuestion {


    protected ViewModelManagerBase(Context context) {
        super(context);

    }
    protected final Vector<ViewModleQuestion> questions = new Vector<ViewModleQuestion>();
    protected IntegerObservable current = new IntegerObservable(-1);

    public Vector<ViewModleQuestion> getResults(){
       Vector<ViewModleQuestion> result = new Vector<ViewModleQuestion>();
       for(ViewModleQuestion question : questions) result.add(question);
       return  result;
   }

    public boolean getResult(){
       boolean result = true;
       for(ViewModleQuestion viewMoldle : getResults()) result&=viewMoldle.result.get();
       return  result;
   }

    public  void clear(){

        current.set(questions.isEmpty()?-1:0);
    }

    protected abstract void advance();

    protected  void init(){
        yes = new Command(){
             @Override
             public void Invoke(View view, Object... objects) {
                 questions.get(current.get()).result.set(true);
                 advance();

             }
         };
         no = new Command(){
             @Override
             public void Invoke(View view, Object... objects) {
                  questions.get(current.get()).result.set(false);
                 advance();

             }
         };
         note= new Command(){

           @Override
           public void Invoke(View view, Object... objects) {
               if(current.get()>-1&&current.get()<questions.size()-1) questions.elementAt(current.get()).note.Invoke(view,objects);
           }
         };

        help = new Command(){
            public void Invoke(View view, Object... objects) {
               if(current.get()>-1&&current.get()<questions.size()){
                   Runnable runnable = new Runnable(){
                      String message =questions.elementAt(current.get()).explanations.get(0);
                      Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG);
                       public void run() {
                          try{
                          toast.setGravity(Gravity.CENTER,0,0);
                          toast.show();
                          }catch (Exception e){
                              String message = e.getMessage();
                          }

                       }
                   };
                   runnable.run();
                   view.postDelayed(runnable,1500);
               }
           }
         };


        this.questionObservable =questions.get(current.get()).questionObservable;
        this.question = new DependentObservable<String>(String.class,current,questions.get(current.get()).question) {
            @Override
            public String calculateValue(Object... objects) throws Exception {
                return questions.get(current.get()).question.get();
            }
        };

    }

    protected void initQuestions(String key){
        try{
           for(String path: getAssets().list(key)){
               ViewModleQuestion temp =new ViewModleQuestion(getApplicationContext());
               temp.init(key+"/"+path);
               questions.add(temp);
           }
            current.set(0);
        }catch(Exception e){}
    }
}
