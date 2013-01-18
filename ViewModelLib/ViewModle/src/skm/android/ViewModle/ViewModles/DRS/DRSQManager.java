package skm.android.ViewModle.ViewModles.DRS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.SeekBar;
import gueei.binding.Command;
import gueei.binding.DependentObservable;
import skm.android.*;
import skm.android.ViewModle.ViewModles.Shared;
import skm.android.ViewModle.ViewModles.base.ViewModelManagerBase;
import skm.android.ViewModle.ViewModles.main.ResultModle;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 04/11/11
 * Time: 07:09
 * To change this template use File | Settings | File Templates.
 */
public class DRSQManager extends ViewModelManagerBase {
    private static DRSQManager instance;
    protected DRSQManager(Context c){super(c);initQuestions(getString(R.string.drsqPath));init();}

    public static DRSQManager getInstance(Context c, Intent intent){
        instance =instance!=null?instance:(instance=new DRSQManager(c));
        if(intent!=null&&intent.hasExtra(Shared.MAIN_MENU_LAUNCHED)){
            instance.mainMenuLaunched = intent.getExtras().getBoolean(Shared.MAIN_MENU_LAUNCHED);
            instance.launcher=intent;
        }
        return instance;
    }
    public void clear(){
        super.clear();
       instance=null;

    }
   @Override
    protected void advance(){
       ResultModle.getInstance(getApplicationContext(),null).clear();
        DRSQuestion.getCurrentInstance().finish();
        Activity caller;
        Intent intent;
        if(this.mainMenuLaunched){
           caller = MainMenu.getCurrentInstance();
        }
        else {
           caller = Menu.getCurrentInstance();
        }
        intent = new Intent(caller, Result.class);
        intent.putExtra(Shared.MAIN_MENU_LAUNCHED,this.mainMenuLaunched);
        caller.startActivity(intent);
   }


    public Command next;
    public Command previous;
    @Override
    protected void init(){
          next= new Command(){
              @Override
              public void Invoke(View view, Object... objects) {
                  if(current.get()>-1&&current.get()<questions.size()-1){
                    current.set(current.get()+1);
                    ((SeekBar)view.getRootView().findViewById(R.id.seekbar)).setProgress(sliderPos.get());
                    explanation.onPropertyChanged(null, new ArrayList<Object>());
                  } else advance();
              }
          };
          previous= new Command(){
              @Override
              public void Invoke(View view, Object... objects) {
                  if(current.get()>0&&current.get()<questions.size()){
                      current.set(current.get()-1);
                  ((SeekBar)view.getRootView().findViewById(R.id.seekbar)).setProgress(sliderPos.get());
                  explanation.onPropertyChanged(null, new ArrayList<Object>());
                  }else advance();
              }
          };
          note= new Command(){

            @Override
            public void Invoke(View view, Object... objects) {
                if(current.get()>-1&&current.get()<questions.size()-1) questions.elementAt(current.get()).note.Invoke(view,objects);
            }
          };
          sliderMoved = new Command(){
            @Override
            public void Invoke(View view, Object... objects) {
               questions.get(current.get()).SliderMoved.Invoke(view, objects);
               explanation.onPropertyChanged(null, new ArrayList<Object>());
            }
          };


          this.questionObservable =questions.get(current.get()).questionObservable;
          this.question = new DependentObservable<String>(String.class,current,questions.get(current.get()).question) {
              @Override
              public String calculateValue(Object... objects) throws Exception {
                  return questions.get(current.get()).question.get();
              }
          };
          this.explanation=new DependentObservable<String>(String.class,current,questions.get(current.get()).explanation,questions.get(current.get()).sliderPosObservable) {
               @Override
               public String calculateValue(Object... objects) throws Exception {
                   //return String.format(getString(R.string.messageFormatText),count.get());  //To change body of implemented methods use File | Settings | File Templates.
                   return  questions.get(current.get()).explanation.get();
               }
            };
          this.sliderPos = new DependentObservable<Integer>(Integer.class,current,questions.get(current.get()).sliderPos,questions.get(current.get()).sliderPosObservable) {
              @Override
              public Integer calculateValue(Object... objects) throws Exception {
                  return questions.get(current.get()).sliderPos.get();  //To change body of implemented methods use File | Settings | File Templates.
              }
          };

        }





    }

