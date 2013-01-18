package skm.android.ViewModle.ViewModles;

import android.content.Context;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import skm.android.R;
import skm.android.ViewModle.ViewModles.base.ViewModleBase;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Sean
 * Date: 01/01/12
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class Shared extends ViewModleBase {
    public static final String RESULT_MESSAGES = "Result Message Flag";
     public static final int SUBSAMPLE = 2;

    public Shared(Context context) {
        super(context);
    }

    public static void setOptionAtribute(String key, String atrib, String value, Context context) {
        try{
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse ( context.openFileInput(context.getString(R.string.user)));
            //value =(T)doc.getElementsByTagName(option).item(0).getTextContent();
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile(key);
            ((NodeList)expr.evaluate(doc, XPathConstants.NODESET)).item(0).getAttributes().getNamedItem(atrib).setTextContent(value);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(context.openFileOutput(context.getString(R.string.user), Context.MODE_PRIVATE)));
        }catch (Exception e){}
    }

    public static enum ViewModleType {}
    public final static String MAIN_MENU_LAUNCHED ="mainMenu";


    public static Node getOption(String option, Context context){
       return getOptions(option,context).item(0);
    }
    public static NodeList getOptions(String option, Context context){
       NodeList result = null;
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse ( context.openFileInput(context.getString(R.string.user)));
            //value =(T)doc.getElementsByTagName(option).item(0).getTextContent();
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile(option);
            result = ((NodeList)expr.evaluate(doc, XPathConstants.NODESET));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static List<String> getOptionStates(String option, Context context){
        NodeList list = getOptions(option,context).item(0).getChildNodes();
        List<String> result = new ArrayList();
        for(int i=0; i<list.getLength();i++)if(list.item(i).getNodeType()==1) result.add(list.item(i).getNodeName());
        return result;
    }
     public static String getOptionAtribute(String option,String attribute, Context context){
        String result = null;
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse ( context.openFileInput(context.getString(R.string.user)));

            //value =(T)doc.getElementsByTagName(option).item(0).getTextContent();
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile(option);
            result = ((NodeList)expr.evaluate(doc, XPathConstants.NODESET)).item(0).getAttributes().getNamedItem(attribute).getTextContent();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return result;
    }
    public static void setOption(String option,String value,Context context){
             try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse ( context.openFileInput(context.getString(R.string.user)));
            //value =(T)doc.getElementsByTagName(option).item(0).getTextContent();
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile(option);
            ((NodeList)expr.evaluate(doc, XPathConstants.NODESET)).item(0).setTextContent(value);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(context.openFileOutput(context.getString(R.string.user), Context.MODE_PRIVATE)));
        }catch (Exception e){}
    }
    public static void resetOptions(Context context){
        try {
            InputStream defaultOptions = context.getAssets().open(context.getString(R.string.defaultConfig));
            String defaults ="";
            Scanner reader =new Scanner( new InputStreamReader(defaultOptions));
            while (reader.hasNextLine()) defaults+= reader.nextLine();
            FileOutputStream fos = null;
            fos = context.openFileOutput(context.getString(R.string.user), Context.MODE_PRIVATE);
            fos.write(defaults.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}

