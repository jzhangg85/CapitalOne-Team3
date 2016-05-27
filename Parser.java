package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;
import org.json.simple.parser.*;

public class Parser {
    
    public static final double AVG_TRANS = 4.37; 
    public static final double RES_AVG = 4.418181818181818;
    public static final double ENT_AVG = 3.4705882352941178;
    public static final double RET_AVG = 4.626666666666667;
    public static final double SER_AVG = 4.192513368983957;
    
    public Parser() {}     
    
    public static void main(String args[]) throws IOException, JSONException, ParseException {
        ArrayList<Merchant> merchLists = getMerchants();
        updateDollarAmt(merchLists);
        HashMap<String, ArrayList<Merchant>> ML = machineLearning(merchLists);
    }
    
    @SuppressWarnings("unchecked")
    public static HashMap<String, ArrayList<Merchant>> machineLearning(ArrayList<Merchant> list) {
        ArrayList<Merchant> restaurants = new ArrayList<Merchant>();
        ArrayList<Merchant> entertainment = new ArrayList<Merchant>();
        ArrayList<Merchant> retail = new ArrayList<Merchant>();
        ArrayList<Merchant> services = new ArrayList<Merchant>();
        
        for (int idx = 0 ; idx< list.size(); idx++){
            switch(list.get(idx).getCat()){
            case "Ser":
                services.add(list.get(idx));
            break;
            case "Ent":
                entertainment.add(list.get(idx));
            break;
            case "Ret":
                retail.add(list.get(idx));
            break; 
            case "Bar":
            case "Res":
                restaurants.add(list.get(idx));
            break;
            }
        }
        ArrayList<Merchant> lowRestaurants = new ArrayList<Merchant>();
        ArrayList<Merchant> highRestaurants = new ArrayList<Merchant>();
        for (int i = 0; i < restaurants.size(); i++) {
            if ((restaurants.get(i).mTotalDollar / ((double) restaurants.get(i).mTotalTrans)) > RES_AVG) {
                highRestaurants.add(restaurants.get(i));
            } else {
                lowRestaurants.add(restaurants.get(i));
            }
        }
        
        ArrayList<Merchant> lowEntertainment = new ArrayList<Merchant>();
        ArrayList<Merchant> highEntertainment = new ArrayList<Merchant>();
        for (int i = 0; i < entertainment.size(); i++) {
            if ((entertainment.get(i).mTotalDollar / ((double) entertainment.get(i).mTotalTrans)) > ENT_AVG) {
                highEntertainment.add(entertainment.get(i));
            } else {
                lowEntertainment.add(entertainment.get(i));
            }
        }
        
        ArrayList<Merchant> lowRetail = new ArrayList<Merchant>();
        ArrayList<Merchant> highRetail = new ArrayList<Merchant>();
        for (int i = 0; i < retail.size(); i++) {
            if ((retail.get(i).mTotalDollar / ((double) retail.get(i).mTotalTrans)) > RET_AVG) {
                highRetail.add(retail.get(i));
            } else {
                lowRetail.add(retail.get(i));
            }
        }
        ArrayList<Merchant> lowServices = new ArrayList<Merchant>();
        ArrayList<Merchant> highServices = new ArrayList<Merchant>();
        for (int i = 0; i < services.size(); i++) {
            if ((services.get(i).mTotalDollar / ((double) services.get(i).mTotalTrans)) > SER_AVG) {
                highServices.add(services.get(i));
            } else {
                lowServices.add(services.get(i));
            }
        }
        Collections.sort(lowRestaurants);
        Collections.sort(highRestaurants);
        Collections.sort(lowEntertainment);
        Collections.sort(highEntertainment);
        Collections.sort(lowRetail);
        Collections.sort(highRetail);
        Collections.sort(lowServices);
        Collections.sort(highServices);
        HashMap<String, ArrayList<Merchant>> toReturn = new HashMap<String, ArrayList<Merchant>>();
        
        restaurants.clear();
        restaurants.addAll(trimmer(lowRestaurants));
        restaurants.addAll(trimmer(highRestaurants));
        toReturn.put("Restaurants", restaurants);

        entertainment.clear();
        entertainment.addAll(trimmer(lowEntertainment));
        entertainment.addAll(trimmer(highEntertainment));
        toReturn.put("Entertainment", entertainment);
        
        retail.clear();
        retail.addAll(trimmer(lowRetail));
        retail.addAll(trimmer(highRetail));
        toReturn.put("Retail", retail);
        
        services.clear();
        services.addAll(trimmer(lowServices));
        services.addAll(trimmer(highServices));
        toReturn.put("Services", services);
        return toReturn;
    }
    
    
    public static ArrayList<Merchant> trimmer(ArrayList<Merchant> list) {
        int cap = list.size() / 2;
        ArrayList<Merchant> toReturn = new ArrayList<Merchant>();
        for (int i = cap; i < list.size(); i++) {
            toReturn.add(list.get(i));
        }
        return toReturn;
    }
    
    
    public static void updateDollarAmt(ArrayList<Merchant> merchLists) throws JSONException, IOException {
        for (int i = 0; i < merchLists.size(); i++) {
            ArrayList<Double> values = totalToAdd(merchLists.get(i).mID, "574704c48a710f8e12324ee1");
            merchLists.get(i).addTo(values.get(0));  
            merchLists.get(i).mTotalTrans = values.get(1).intValue();
        }
    }
    
    public static ArrayList<Double> totalToAdd(String merchantID, String customerID) throws IOException, JSONException {
        ArrayList<Double> toReturn = new ArrayList<Double>();
        toReturn.add(0.0);
        toReturn.add(0.0);
        URL newUrl = new URL("http://api.reimaginebanking.com/merchants/" + merchantID + "/accounts/" + customerID + "/purchases?key=482cef6d93c206eb7e5c9fefc8941ccb");
        URLConnection con = newUrl.openConnection();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));        
        String rawText = readURL(br);    
        JSONArray testy = new JSONArray(rawText);
        for (int i = 0; i < testy.length(); i++) {
            toReturn.set(0, toReturn.get(0) + testy.getJSONObject(i).getDouble("amount"));
            toReturn.set(1, toReturn.get(1) + 1);
        }
        return toReturn;
    }
    
    public static ArrayList<Merchant> getMerchants() throws IOException, JSONException, ParseException{
        String rawText = readFile("merchants.json");
        JSONObject json = new JSONObject(rawText);
        JSONArray jray = json.getJSONArray("results");
        ArrayList<Merchant> x  = new ArrayList<Merchant>();
        for (int i = 0; i < jray.length(); i++) {
            if (jray.getJSONObject(i).has("address") &&
                    jray.getJSONObject(i).getJSONObject("address").has("city") 
                    && jray.getJSONObject(i).getJSONObject("address").getString("city").equals("Ann Arbor")) {
                String name = jray.getJSONObject(i).getString("name");
                Double lon = jray.getJSONObject(i).getJSONObject("geocode").getDouble("lng");
                Double lat = jray.getJSONObject(i).getJSONObject("geocode").getDouble("lat");
                String ID = jray.getJSONObject(i).getString("_id");
                String category = null;
                if (jray.getJSONObject(i).has("category")) {
                    category = jray.getJSONObject(i).getString("category");
                }
                Merchant temp = new Merchant(name, lon, lat, category, ID);
                x.add(temp);
            }
        } 
        return x;
    }
    
    private static String readURL(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        int toAdd;
        while ((toAdd = reader.read()) != -1) {
            builder.append((char) toAdd);
        }
        return builder.toString();
    }
    
    public static String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    
}
