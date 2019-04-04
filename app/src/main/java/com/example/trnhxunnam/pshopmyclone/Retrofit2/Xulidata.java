package com.example.trnhxunnam.pshopmyclone.Retrofit2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Model.Infor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Xulidata extends AsyncTask<String, Integer, String> {

    String hinhanh = "";
    String title = "";
    String link = "";
    Element element;
    ArrayList<Infor> arrayList;
    Context context;

    public Xulidata(Context context, String rss) {
        this.execute(rss);
        this.getValue(element,"title");

        this.context = context;

    }

    @Override
    protected String doInBackground(String... strings) {
        String abc = readURL(strings[0]);
        return abc;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        ArrayList<Infor> inforArrayList = new ArrayList<>();
        if (s.equals("")) {
            Toast.makeText(context, "Internet is not connected", Toast.LENGTH_LONG).show();
            inforArrayList.clear();
        } else {
            //Log.e("getDocumentDDSSSSSSS", "---" + s);
            Document document = getDocument(s);
            //Log.e("getDocumentDDDOCUMENT","---"+getDocument(s));

            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescrip = document.getElementsByTagName("description");

            for (int i = 0; i < nodeList.getLength(); i++) {

                String ccdata = nodeListDescrip.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(ccdata);

                if (matcher.find()) {
                    hinhanh = matcher.group(1);
                    Log.e("hinhanh", hinhanh + "........." + i);
                }

                element = (Element) nodeList.item(i);

                title = getValue(element, "title");
                link = getValue(element, "link");

                //Log.e("link",link);
                //Log.e("titlexml",title+"--"+i);
//                    inforArrayList = new ArrayList<>();
                inforArrayList.add(new Infor(title, link, hinhanh));
                //this.arrayList = inforArrayList;
                setArrayList1(inforArrayList);
            }

        }
    }

    public ArrayList<Infor> back() {

        return arrayList;
    }

    public void setArrayList1(ArrayList<Infor> arrayList1) {
        back();
    }

    public String readURL(String theUrl) {

        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //Log.d("readURLLL",""+ bufferedReader);
            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public Document getDocument(String xml) {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            is.setEncoding("UTF-8");
            document = db.parse(is);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }
        return document;
    }

    public String getValue(Element item, String name) {
        NodeList nodes = item.getElementsByTagName(name);
        return this.getTextNodeValue(nodes.item(0));
    }

    private final String getTextNodeValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
