package skm.android.ViewModle.ViewModles.base;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import gueei.binding.Command;
import gueei.binding.DependentObservable;
import gueei.binding.observables.IntegerObservable;
import gueei.binding.observables.StringObservable;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import skm.android.R;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 01/01/12
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class ViewModleQuestion extends YesNoViewModleBase {

    public ViewModleQuestion(Context context){
         super(context);

    }
    protected Vector<String> explanations = new Vector<String>();

    public StringObservable questionObservable = new StringObservable("test");
    public DependentObservable<String> question = new DependentObservable<String>(String.class, questionObservable) {
    @Override
       public String calculateValue(Object... objects) throws Exception {
           //return String.format(getString(R.string.messageFormatText),count.get());  //To change body of implemented methods use File | Settings | File Templates.
           return questionObservable.get();
       }
    };

    public IntegerObservable sliderPosObservable = new IntegerObservable(0);
    public DependentObservable<Integer> sliderPos = new DependentObservable<Integer>(Integer.class,sliderPosObservable) {
        @Override
        public Integer calculateValue(Object... objects) throws Exception {
            return sliderPosObservable.get();  //To change body of implemented methods use File | Settings | File Templates.
        }
    };
      public final Command SliderMoved = new Command(){     // possibly move to ui
        @Override
        public void Invoke(View view, Object... objects) {
           sliderMoved.Invoke(view,objects);
        }
    };
    protected Command sliderMoved = new Command(){     // possibly move to ui
        @Override
        public void Invoke(View view, Object... objects) {
            if(view instanceof android.widget.SeekBar){
                SeekBar bar = (SeekBar) view;
                sliderPosObservable.set(bar.getProgress());


            }
        }
    };
    public DependentObservable<String> explanation = new DependentObservable<String>(String.class,sliderPosObservable) {
       @Override
       public String calculateValue(Object... objects) throws Exception {
           //return String.format(getString(R.string.messageFormatText),count.get());  //To change body of implemented methods use File | Settings | File Templates.
           return explanations.size()>sliderPos.get()?explanations.elementAt(sliderPos.get()):getString(skm.android.ViewModle.R.string.ExplanationNotFound);  //To change body of implemented methods use File | Settings | File Templates.
       }
    };
    public void init(String path){
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (getAssets().open(path));
            this.questionObservable.set(doc.getElementsByTagName(getString(R.string.question)).item(0).getTextContent());
            NodeList list = doc.getElementsByTagName(getString(R.string.explanation));
            for(int i=0; i<list.getLength();i++)this.explanations.add(list.item(i).getTextContent());
        }catch (Exception e){
            String message = e.getMessage();
        }
    }



}
